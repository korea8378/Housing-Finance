package com.housing.finance.housingfinance.service;

import com.housing.finance.housingfinance.dto.ResAvgOfBankDto;
import com.housing.finance.housingfinance.dto.ResMaximumOfBankDto;
import com.housing.finance.housingfinance.dto.ResTotalHousingFinanceDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class HousingFinanceService {

    private final CSVParsingService csvParsingService;
    private final HousingFinanceTotalService housingFinanceTotalService;
    private final MaximumOfBankService maximumOfBankService;
    private final AvgOfBankService avgOfBankService;

    public HousingFinanceService(CSVParsingService csvParsingService,
                                 HousingFinanceTotalService housingFinanceTotalService,
                                 MaximumOfBankService maximumOfBankService,
                                 AvgOfBankService avgOfBankService) {
        this.csvParsingService = csvParsingService;
        this.housingFinanceTotalService = housingFinanceTotalService;
        this.maximumOfBankService = maximumOfBankService;
        this.avgOfBankService = avgOfBankService;
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

    public ResAvgOfBankDto getMaxMinAvgOfBank() {
        return avgOfBankService.getMaxMin();
    }
}
