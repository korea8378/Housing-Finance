package com.housing.finance.housingfinance.controller;

import com.housing.finance.housingfinance.dto.ResBanksDto;
import com.housing.finance.housingfinance.service.HousingFinanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HousingFinanceController {

    private final HousingFinanceService housingFinanceService;

    public HousingFinanceController(HousingFinanceService housingFinanceService) {
        this.housingFinanceService = housingFinanceService;
    }

    @GetMapping("/banks")
    public ResponseEntity<ResBanksDto> getBanks() {
        return ResponseEntity.status(HttpStatus.OK).body(housingFinanceService.getBanks());
    }
}
