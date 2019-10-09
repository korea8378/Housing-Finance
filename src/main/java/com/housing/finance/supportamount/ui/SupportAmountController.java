package com.housing.finance.supportamount.ui;

import com.housing.finance.supportamount.dto.ResBanksDto;
import com.housing.finance.supportamount.dto.ResMaxMinAvgAMountDto;
import com.housing.finance.supportamount.dto.ResMaxAmountDto;
import com.housing.finance.supportamount.application.SupportAmountVO;
import com.housing.finance.supportamount.application.SupportAmountService;
import com.housing.finance.supportamount.dto.ResTotalAmountsDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SupportAmountController {

    private final SupportAmountService supportAmountService;

    public SupportAmountController(SupportAmountService supportAmountService) {
        this.supportAmountService = supportAmountService;
    }

    @GetMapping("/banks")
    public ResponseEntity<ResBanksDto> getBanks() {
        return ResponseEntity.status(HttpStatus.OK).body(supportAmountService.getBanks());
    }

    @PostMapping(value = "/support-amount")
    public ResponseEntity<Void> uploadHousingFinanceCSV(@RequestParam MultipartFile file) {
        supportAmountService.upload(SupportAmountVO.class, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "/support-amount/total")
    public ResponseEntity<ResTotalAmountsDto> getTotalAmount() {
        return ResponseEntity.status(HttpStatus.OK).body(supportAmountService.getTotalByYear());
    }

    @GetMapping(value = "/support-amount/max")
    public ResponseEntity<ResMaxAmountDto> getMaxOfAllBank() {
        return ResponseEntity.status(HttpStatus.OK).body(supportAmountService.getMaxOfBank());
    }

    @GetMapping(value = "/banks/korea-exchange-bank/support-amount/avg")
    public ResponseEntity<ResMaxMinAvgAMountDto> getAvgOfKoreaExchangeBank() {
        return ResponseEntity.status(HttpStatus.OK).body(supportAmountService.getMaxMinAvgOfKoreaExchangeBank());
    }
}
