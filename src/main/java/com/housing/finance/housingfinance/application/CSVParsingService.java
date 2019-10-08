package com.housing.finance.housingfinance.application;

import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.housing.finance.housingfinance.domain.HousingFinanceRepository;
import com.housing.finance.housingfinance.util.CSVParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;


@Slf4j
@Service
public class CSVParsingService {

    private final HousingFinanceRepository housingFinanceRepository;
    private final HousingFinanceMapper housingFinanceMapper;

    public CSVParsingService(HousingFinanceRepository housingFinanceRepository,
                             HousingFinanceMapper housingFinanceMapper) {
        this.housingFinanceRepository = housingFinanceRepository;
        this.housingFinanceMapper = housingFinanceMapper;
    }

    @Transactional
    public void upload(Class<HousingFinanceVO> housingFinanceVOClass, MultipartFile file) {
        CsvSchema csvSchema = createBankSchema();

        List<HousingFinanceVO> housingFinanceVOs = CSVParser.read(housingFinanceVOClass, file, csvSchema);

        save(housingFinanceVOs);
    }

    private CsvSchema createBankSchema() {
        return CsvSchema.builder()
                .addColumn(CSVHeader.YEAR.getValue())
                .addColumn(CSVHeader.MONTH.getValue())
                .addColumn(CSVHeader.HOUSING_CITY_FUND.getValue())
                .addColumn(CSVHeader.KOOKMIN_BANK.getValue())
                .addColumn(CSVHeader.WOORI_BANK.getValue())
                .addColumn(CSVHeader.SHINHAN_BANK.getValue())
                .addColumn(CSVHeader.KOREA_CITY_BANK.getValue())
                .addColumn(CSVHeader.HANA_BANK.getValue())
                .addColumn(CSVHeader.NONGHYUP_SUHYUP_BANK.getValue())
                .addColumn(CSVHeader.KOREA_EXCHANGE_BANK.getValue())
                .addColumn(CSVHeader.ETC_BANK.getValue())
                .build().withHeader();
    }

    private void save(List<HousingFinanceVO> housingFinanceVOs) {
        housingFinanceVOs.stream()
                .map(housingFinanceMapper::toHousingFinance)
                .flatMap(Collection::stream)
                .forEach(housingFinanceRepository::save);
    }


}
