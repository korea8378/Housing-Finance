package com.housing.finance.supportamount.infrastructure;

import com.housing.finance.supportamount.dao.AvgBankDao;
import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class JpaAvgBankDao implements AvgBankDao {

    private final EntityManager em;

    public JpaAvgBankDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public ResDetailAvgAmountDto selectMaxAvgAmountGroupByYear() {

        String selectQuery =
                "SELECT new com.housing.finance.supportamount.dto.ResDetailAvgAmountDto(" +
                        "sa.name, sa.year, AVG(sa.amount) AS average)" +
                        "FROM SupportAmount sa " +
                        "GROUP BY sa.name, sa.year " +
                        "HAVING sa.name = '외환은행' AND sa.year >= 2005 AND sa.year <= 2016 And COUNT(sa.year) = 12" +
                        "Order by average DESC";

        TypedQuery<ResDetailAvgAmountDto> query =
                em.createQuery(selectQuery, ResDetailAvgAmountDto.class).setMaxResults(1);
        return query.getSingleResult();
    }

    @Override
    public ResDetailAvgAmountDto selectMinAvgAmountGroupByYear() {

        String selectQuery =
                "SELECT new com.housing.finance.supportamount.dto.ResDetailAvgAmountDto(" +
                        "sa.name, sa.year, AVG(sa.amount) AS average)" +
                        "FROM SupportAmount sa " +
                        "GROUP BY sa.name, sa.year " +
                        "HAVING sa.name = '외환은행' AND sa.year >= 2005 AND sa.year <= 2016 And COUNT(sa.year) = 12" +
                        "Order by average";

        TypedQuery<ResDetailAvgAmountDto> query =
                em.createQuery(selectQuery, ResDetailAvgAmountDto.class).setMaxResults(1);
        return query.getSingleResult();
    }
}
