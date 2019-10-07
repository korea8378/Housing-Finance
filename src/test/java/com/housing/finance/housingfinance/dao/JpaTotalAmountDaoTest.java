package com.housing.finance.housingfinance.dao;

import com.housing.finance.housingfinance.domain.HousingFinance;
import com.housing.finance.housingfinance.domain.HousingFinanceRepository;
import com.housing.finance.housingfinance.dto.ResTotalAmountDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class JpaTotalAmountDaoTest {

    private JpaTotalAmountDao jpaTotalAmountDao;

    @Autowired
    private HousingFinanceRepository housingFinanceRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void testTotalAmountJpql() {
        jpaTotalAmountDao = new JpaTotalAmountDao(entityManager);
        HousingFinance housingFinanceOne = new HousingFinance(2005L, 1L,"주택도시기금", 1019L);
        HousingFinance housingFinanceTwo = new HousingFinance(2005L, 2L,"주택도시기금", 1022L);
        HousingFinance housingFinanceThree = new HousingFinance(2006L, 1L,"국민은행", 846L);
        HousingFinance housingFinanceFour = new HousingFinance(2006L, 2L,"국민은행", 222L);
        housingFinanceRepository.save(housingFinanceOne);
        housingFinanceRepository.save(housingFinanceTwo);
        housingFinanceRepository.save(housingFinanceThree);
        housingFinanceRepository.save(housingFinanceFour);

        List<ResTotalAmountDto> resTotalAmountDtoList = jpaTotalAmountDao.selectGroupByYear();

        assertThat(resTotalAmountDtoList.get(0).getYear()).isEqualTo("2005년");
        assertThat(resTotalAmountDtoList.get(0).getTotalAmount())
                .isEqualTo(housingFinanceOne.getAmount() + housingFinanceTwo.getAmount());

        assertThat(resTotalAmountDtoList.get(1).getYear()).isEqualTo("2006년");
        assertThat(resTotalAmountDtoList.get(1).getTotalAmount())
                .isEqualTo(housingFinanceThree.getAmount() + housingFinanceFour.getAmount());
    }
}