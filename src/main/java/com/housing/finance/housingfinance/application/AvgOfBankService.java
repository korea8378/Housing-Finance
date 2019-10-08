package com.housing.finance.housingfinance.application;

import com.housing.finance.housingfinance.dao.AvgBankDao;
import com.housing.finance.housingfinance.dto.ResAvgOfBankDto;
import com.housing.finance.housingfinance.dto.ResDetailAvgAmountDto;
import org.springframework.stereotype.Service;

@Service
public class AvgOfBankService {

    private final HousingFinanceMapper housingFinanceMapper;
    private final AvgBankDao avgBankDao;

    public AvgOfBankService(HousingFinanceMapper housingFinanceMapper, AvgBankDao avgBankDao) {
        this.housingFinanceMapper = housingFinanceMapper;
        this.avgBankDao = avgBankDao;
    }

    public ResAvgOfBankDto getMaxMin() {

        ResDetailAvgAmountDto max = avgBankDao.selectMaxAvgAmountGroupByYear();
        ResDetailAvgAmountDto min = avgBankDao.selectMinAvgAmountGroupByYear();

        return housingFinanceMapper.toResAvgOfBankDto(max, min);
    }
}
