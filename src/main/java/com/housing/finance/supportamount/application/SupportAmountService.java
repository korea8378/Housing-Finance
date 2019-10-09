package com.housing.finance.supportamount.application;

import com.housing.finance.supportamount.dto.ResBanksDto;
import com.housing.finance.supportamount.dto.ResMaxMinAvgAMountDto;
import com.housing.finance.supportamount.dto.ResMaxAmountDto;
import com.housing.finance.supportamount.dto.ResTotalAmountsDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SupportAmountService {

    private final CSVParsingService csvParsingService;
    private final SupportAmountTotalService supportAmountTotalService;
    private final SupportAmountMaxService supportAmountMaxService;
    private final SupportAmountAvgService supportAmountAvgService;
    private final BankService bankService;

    public SupportAmountService(CSVParsingService csvParsingService,
                                SupportAmountTotalService supportAmountTotalService,
                                SupportAmountMaxService supportAmountMaxService,
                                SupportAmountAvgService supportAmountAvgService,
                                BankService bankService) {
        this.csvParsingService = csvParsingService;
        this.supportAmountTotalService = supportAmountTotalService;
        this.supportAmountMaxService = supportAmountMaxService;
        this.supportAmountAvgService = supportAmountAvgService;
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

    public ResMaxAmountDto getMaxOfBank() {
        return supportAmountMaxService.getOfAllBank();
    }

    public ResMaxMinAvgAMountDto getMaxMinAvgOfKoreaExchangeBank() {
        return supportAmountAvgService.getMaxMinOfKoreaExchangeBank();
    }


}
