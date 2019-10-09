package com.housing.finance.supportamount.infrastructure;

import com.housing.finance.supportamount.domain.supportamount.SupportAmount;
import com.housing.finance.supportamount.domain.supportamount.SupportAmountRepository;
import com.housing.finance.supportamount.dto.ResDetailTotalAmountDto;
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
    private SupportAmountRepository supportAmountRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    public void testSelectGroupByYear() {
        jpaTotalAmountDao = new JpaTotalAmountDao(entityManager);
        SupportAmount supportAmountOne = new SupportAmount(2005L, 1L, "주택도시기금", 1019L);
        SupportAmount supportAmountTwo = new SupportAmount(2005L, 2L, "주택도시기금", 1022L);
        SupportAmount supportAmountThree = new SupportAmount(2006L, 1L, "국민은행", 846L);
        SupportAmount supportAmountFour = new SupportAmount(2006L, 2L, "국민은행", 222L);
        supportAmountRepository.save(supportAmountOne);
        supportAmountRepository.save(supportAmountTwo);
        supportAmountRepository.save(supportAmountThree);
        supportAmountRepository.save(supportAmountFour);

        List<ResDetailTotalAmountDto> resDetailTotalAmountDtos = jpaTotalAmountDao.selectGroupByYear();

        assertThat(resDetailTotalAmountDtos.get(0).getYear()).isEqualTo("2005년");
        assertThat(resDetailTotalAmountDtos.get(0).getTotalAmount())
                .isEqualTo(supportAmountOne.getAmount() + supportAmountTwo.getAmount());

        assertThat(resDetailTotalAmountDtos.get(1).getYear()).isEqualTo("2006년");
        assertThat(resDetailTotalAmountDtos.get(1).getTotalAmount())
                .isEqualTo(supportAmountThree.getAmount() + supportAmountFour.getAmount());
    }
}