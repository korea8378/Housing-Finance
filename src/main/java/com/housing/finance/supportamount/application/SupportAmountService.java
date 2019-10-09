package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dto.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SupportAmountService {

    private final CSVParsingService csvParsingService;
    private final SupportAmountTotalService supportAmountTotalService;
    private final SupportAmountMaxService supportAmountMaxService;
    private final SupportAmountAvgService supportAmountAvgService;
    private final SupportAmountPredictService supportAmountPredictService;
    private final BankService bankService;

    public SupportAmountService(CSVParsingService csvParsingService,
                                SupportAmountTotalService supportAmountTotalService,
                                SupportAmountMaxService supportAmountMaxService,
                                SupportAmountAvgService supportAmountAvgService,
                                SupportAmountPredictService supportAmountPredictService,
                                BankService bankService) {
        this.csvParsingService = csvParsingService;
        this.supportAmountTotalService = supportAmountTotalService;
        this.supportAmountMaxService = supportAmountMaxService;
        this.supportAmountAvgService = supportAmountAvgService;
        this.supportAmountPredictService = supportAmountPredictService;
        this.bankService = bankService;
    }

    public ResBanksDto getBanks() {
        return bankService.getBanks();
    }

    public void upload(Class<SupportAmountVO> supportAmountVOClass, MultipartFile file) {
        csvParsingService.upload(supportAmountVOClass, file);
    }

    public ResTotalAmountsDto getTotalByYear() {
        return supportAmountTotalService.getByYear();
    }

    public ResMaxAmountDto getMaxOfBanks() {
        return supportAmountMaxService.getOfBanks();
    }

    public ResMaxMinAvgAMountDto getMaxMinAvgOfKoreaExchangeBank() {
        return supportAmountAvgService.getMaxMinOfKoreaExchangeBank();
    }

    public ResPredictionAmountDto getPredictionOfBank(ReqPredictionAmountDto reqPredictionAmountDto) {
        return supportAmountPredictService.getByBankAndMonth(reqPredictionAmountDto);
    }
}
