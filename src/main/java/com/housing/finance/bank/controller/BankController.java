package com.housing.finance.bank.controller;

import com.housing.finance.bank.dto.ResBanksDto;
import com.housing.finance.bank.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BankController {

    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/banks")
    public ResponseEntity<ResBanksDto> getBanks() {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.getBanks());
    }
}
