package com.housing.finance.supportamount.application;

import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.housing.finance.supportamount.domain.supportamount.SupportAmountRepository;
import com.housing.finance.supportamount.util.CSVParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;


@Slf4j
@Service
public class CSVParsingService {

    private final SupportAmountRepository supportAmountRepository;
    private final SupportAmountMapper supportAmountMapper;
    private final CSVParser csvParser;

    public CSVParsingService(SupportAmountRepository supportAmountRepository,
                             SupportAmountMapper supportAmountMapper, CSVParser csvParser) {
        this.supportAmountRepository = supportAmountRepository;
        this.supportAmountMapper = supportAmountMapper;
        this.csvParser = csvParser;
    }

    @Transactional
    public void upload(Class<SupportAmountVO> supportAmountVOClass, MultipartFile file) {
        CsvSchema csvSchema = createBankSchema();

        List<SupportAmountVO> supportAmountVOs = csvParser.read(supportAmountVOClass, file, csvSchema);

        save(supportAmountVOs);
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

    private void save(List<SupportAmountVO> supportAmountVOs) {
        supportAmountVOs.stream()
                .map(supportAmountMapper::toSupportAmounts)
                .flatMap(Collection::stream)
                .forEach(supportAmountRepository::save);
    }


}
