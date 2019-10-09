package com.housing.finance.supportamount.infrastructure;

import com.housing.finance.supportamount.domain.SupportAmount;
import com.housing.finance.supportamount.domain.SupportAmountRepository;
import com.housing.finance.supportamount.dto.ResMaxAmountDto;
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
public class JpaMaxBankDaoTest {

    private JpaMaxBankDao jpaMaxBankDao;

    @Autowired
    private SupportAmountRepository supportAmountRepository;

    @Autowired
    private EntityManager entityManager;

    @Before
    public void setUp() {
        jpaMaxBankDao = new JpaMaxBankDao(entityManager);
        SupportAmount supportAmountOne = new SupportAmount(2011L, 1L, "신한은행", 1019L);
        SupportAmount supportAmountTwo = new SupportAmount(2011L, 2L, "신한은행", 1022L);
        SupportAmount supportAmountThree = new SupportAmount(2011L, 3L, "신한은행", 1234L);
        SupportAmount supportAmountFour = new SupportAmount(2011L, 4L, "신한은행", 1532L);
        SupportAmount supportAmountFive = new SupportAmount(2011L, 5L, "신한은행", 6419L);
        SupportAmount supportAmountSix = new SupportAmount(2011L, 6L, "신한은행", 7522L);
        SupportAmount supportAmountSeven = new SupportAmount(2011L, 7L, "신한은행", 56019L);
        SupportAmount supportAmountEight = new SupportAmount(2011L, 8L, "신한은행", 9922L);
        SupportAmount supportAmountNine = new SupportAmount(2011L, 9L, "신한은행", 6619L);
        SupportAmount supportAmountTen = new SupportAmount(2011L, 10L, "신한은행", 8672L);
        SupportAmount supportAmountEleven = new SupportAmount(2011L, 11L, "신한은행", 9879L);
        SupportAmount supportAmountTwelve = new SupportAmount(2011L, 12L, "신한은행", 8236L);
        SupportAmount supportAmountThirteen = new SupportAmount(2006L, 1L, "국민은행", 846L);
        SupportAmount supportAmountFourteen = new SupportAmount(2006L, 2L, "국민은행", 222L);
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
        supportAmountRepository.save(supportAmountThirteen);
        supportAmountRepository.save(supportAmountFourteen);
    }

    @Test
    public void testSelectMaxAmountGroupByYear() {

        ResMaxAmountDto resMaxAmountDto = jpaMaxBankDao.selectMaxAmountGroupByYear();

        assertThat(resMaxAmountDto.getYear()).isEqualTo(2011L);
        assertThat(resMaxAmountDto.getBank()).isEqualTo("신한은행");

    }
}