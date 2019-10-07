package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.dto.ResMaximumOfBankDto;
import com.housing.finance.housingfinance.dto.ResTotalHousingFinanceDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class HousingFinanceService {

    private final CSVParsingService csvParsingService;
    private final HousingFinanceTotalService housingFinanceTotalService;
    private final MaximumOfBankService maximumOfBankService;

    public HousingFinanceService(CSVParsingService csvParsingService,
                                 HousingFinanceTotalService housingFinanceTotalService,
                                 MaximumOfBankService maximumOfBankService) {
        this.csvParsingService = csvParsingService;
        this.housingFinanceTotalService = housingFinanceTotalService;
        this.maximumOfBankService = maximumOfBankService;
    }

    public void upload(Class<HousingFinanceVO> housingFinanceDtoClass, MultipartFile file) {
        csvParsingService.upload(housingFinanceDtoClass, file);
    }

    public ResTotalHousingFinanceDto getTotalAmountByYear() {
        return housingFinanceTotalService.getTotalAmountByYear();
    }

    public ResMaximumOfBankDto getMaximumOfAllBank() {
        return maximumOfBankService.getMaximumOfAllBank();
    }
}
