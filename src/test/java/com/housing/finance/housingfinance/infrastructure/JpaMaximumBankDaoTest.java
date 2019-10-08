package com.housing.finance.housingfinance.infrastructure;

import com.housing.finance.housingfinance.domain.HousingFinance;
import com.housing.finance.housingfinance.domain.HousingFinanceRepository;
import com.housing.finance.housingfinance.dto.ResMaximumOfBankDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class JpaMaximumBankDaoTest {

    private JpaMaximumBankDao jpaMaximumBankDao;

    @Autowired
    private HousingFinanceRepository housingFinanceRepository;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void setUp() {
        jpaMaximumBankDao = new JpaMaximumBankDao(entityManager);
        HousingFinance housingFinanceOne = new HousingFinance(2011L, 1L, "신한은행", 1019L);
        HousingFinance housingFinanceTwo = new HousingFinance(2011L, 2L, "신한은행", 1022L);
        HousingFinance housingFinanceThree = new HousingFinance(2011L, 3L, "신한은행", 1234L);
        HousingFinance housingFinanceFour = new HousingFinance(2011L, 4L, "신한은행", 1532L);
        HousingFinance housingFinanceFive = new HousingFinance(2011L, 5L, "신한은행", 6419L);
        HousingFinance housingFinanceSix = new HousingFinance(2011L, 6L, "신한은행", 7522L);
        HousingFinance housingFinanceSeven = new HousingFinance(2011L, 7L, "신한은행", 56019L);
        HousingFinance housingFinanceEight = new HousingFinance(2011L, 8L, "신한은행", 9922L);
        HousingFinance housingFinanceNine = new HousingFinance(2011L, 9L, "신한은행", 6619L);
        HousingFinance housingFinanceTen = new HousingFinance(2011L, 10L, "신한은행", 8672L);
        HousingFinance housingFinanceEleven = new HousingFinance(2011L, 11L, "신한은행", 9879L);
        HousingFinance housingFinanceTwelve = new HousingFinance(2011L, 12L, "신한은행", 8236L);
        HousingFinance housingFinanceThirteen = new HousingFinance(2006L, 1L, "국민은행", 846L);
        HousingFinance housingFinanceFourteen = new HousingFinance(2006L, 2L, "국민은행", 222L);
        housingFinanceRepository.save(housingFinanceOne);
        housingFinanceRepository.save(housingFinanceTwo);
        housingFinanceRepository.save(housingFinanceThree);
        housingFinanceRepository.save(housingFinanceFour);
        housingFinanceRepository.save(housingFinanceFive);
        housingFinanceRepository.save(housingFinanceSix);
        housingFinanceRepository.save(housingFinanceSeven);
        housingFinanceRepository.save(housingFinanceEight);
        housingFinanceRepository.save(housingFinanceNine);
        housingFinanceRepository.save(housingFinanceTen);
        housingFinanceRepository.save(housingFinanceEleven);
        housingFinanceRepository.save(housingFinanceTwelve);
        housingFinanceRepository.save(housingFinanceThirteen);
        housingFinanceRepository.save(housingFinanceFourteen);
    }

    @Test
    public void testSelectMaximumAmountGroupByYear() {

        ResMaximumOfBankDto resMaximumOfBankDto = jpaMaximumBankDao.selectMaximumAmountGroupByYear();

        assertThat(resMaximumOfBankDto.getYear()).isEqualTo(2011L);
        assertThat(resMaximumOfBankDto.getBank()).isEqualTo("신한은행");

    }
}