package com.housing.finance.housingfinance.controller;

import com.housing.finance.housingfinance.service.HousingFinanceVO;
import com.housing.finance.housingfinance.service.HousingFinanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class HousingFinanceController {

    private final HousingFinanceService housingFinanceService;

    public HousingFinanceController(HousingFinanceService housingFinanceService) {
        this.housingFinanceService = housingFinanceService;
    }

    @PostMapping(value = "/housing-finance")
    public ResponseEntity<Void> uploadHousingFinanceCSV(@RequestParam MultipartFile file) {
        housingFinanceService.upload(HousingFinanceVO.class, file);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
