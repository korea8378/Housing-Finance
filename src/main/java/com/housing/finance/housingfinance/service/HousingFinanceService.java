package com.housing.finance.housingfinance.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class HousingFinanceService {

    private final CSVParsingService csvParsingService;

    public HousingFinanceService(CSVParsingService csvParsingService) {
        this.csvParsingService = csvParsingService;
    }

    public void upload(Class<HousingFinanceVO> housingFinanceDtoClass, MultipartFile file) {
        csvParsingService.upload(housingFinanceDtoClass, file);
    }
}
