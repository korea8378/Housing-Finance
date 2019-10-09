package com.housing.finance.supportamount.infrastructure;

import com.housing.finance.supportamount.domain.supportamount.SupportAmount;
import com.housing.finance.supportamount.domain.supportamount.SupportAmountRepository;
import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;
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
public class JpaAvgBankDaoTest {

    private JpaAvgBankDao jpaAvgBankDao;
    private Long tempAvg;

    @Autowired
    private SupportAmountRepository supportAmountRepository;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void setUp() {
        jpaAvgBankDao = new JpaAvgBankDao(entityManager);
        SupportAmount supportAmountOne = new SupportAmount(2011L, 1L, "외환은행", 1019L);
        SupportAmount supportAmountTwo = new SupportAmount(2011L, 2L, "외환은행", 1022L);
        SupportAmount supportAmountThree = new SupportAmount(2011L, 3L, "외환은행", 1234L);
        SupportAmount supportAmountFour = new SupportAmount(2011L, 4L, "외환은행", 1532L);
        SupportAmount supportAmountFive = new SupportAmount(2011L, 5L, "외환은행", 6419L);
        SupportAmount supportAmountSix = new SupportAmount(2011L, 6L, "외환은행", 7522L);
        SupportAmount supportAmountSeven = new SupportAmount(2011L, 7L, "외환은행", 56019L);
        SupportAmount supportAmountEight = new SupportAmount(2011L, 8L, "외환은행", 9922L);
        SupportAmount supportAmountNine = new SupportAmount(2011L, 9L, "외환은행", 6619L);
        SupportAmount supportAmountTen = new SupportAmount(2011L, 10L, "외환은행", 8672L);
        SupportAmount supportAmountEleven = new SupportAmount(2011L, 11L, "외환은행", 9879L);
        SupportAmount supportAmountTwelve = new SupportAmount(2011L, 12L, "외환은행", 8236L);
        supportAmountRepository.save(supportAmountOne);
        supportAmountRepository.save(supportAmountTwo);
        supportAmountRepository.save(supportAmountThree);
        supportAmountRepository.save(supportAmountFour);
        supportAmountRepository.save(supportAmountFive);
        supportAmountRepository.save(supportAmountSix);
        supportAmountRepository.save(supportAmountSeven);
        supportAmountRepository.save(supportAmountEight);
        supportAmountRepository.save(supportAmountNine);
        supportAmountRepository.save(supportAmountTen);
        supportAmountRepository.save(supportAmountEleven);
        supportAmountRepository.save(supportAmountTwelve);

        Long totalAmount = supportAmountEight.getAmount() + supportAmountEleven.getAmount() + supportAmountFive.getAmount()
                + supportAmountFour.getAmount() + supportAmountNine.getAmount() + supportAmountOne.getAmount()
                + supportAmountSeven.getAmount() + supportAmountSix.getAmount() + supportAmountTen.getAmount()
                + supportAmountThree.getAmount() + supportAmountTwelve.getAmount() + supportAmountTwo.getAmount();

        tempAvg = Math.round(totalAmount/12.0);
    }

    @Test
    public void testSelectMaxAvgAmountGroupByYear() {
        ResDetailAvgAmountDto resDetailAvgAmountDto = jpaAvgBankDao.selectMaxAvgAmountGroupByYear();

        assertThat(resDetailAvgAmountDto.getYear()).isEqualTo(2011L);
        assertThat(resDetailAvgAmountDto.getBank()).isEqualTo("외환은행");
        assertThat(resDetailAvgAmountDto.getAmount()).isEqualTo(tempAvg);
    }



}