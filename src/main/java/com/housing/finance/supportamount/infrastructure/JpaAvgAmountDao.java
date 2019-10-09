package com.housing.finance.supportamount.infrastructure;

import com.housing.finance.supportamount.dao.AvgAmountDao;
import com.housing.finance.supportamount.dto.ResDetailAvgAmountDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class JpaAvgAmountDao implements AvgAmountDao {

    private final EntityManager em;

    public JpaAvgAmountDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public ResDetailAvgAmountDto selectMaxGroupByYear() {

        String selectQuery =
                "SELECT new com.housing.finance.supportamount.dto.ResDetailAvgAmountDto(" +
                        "sa.bankName, sa.year, AVG(sa.amount) AS average)" +
                        "FROM SupportAmount sa " +
                        "GROUP BY sa.bankName, sa.year " +
                        "HAVING sa.bankName = '외환은행' AND sa.year >= 2005 AND sa.year <= 2016 And COUNT(sa.year) = 12" +
                        "Order by average DESC";

        TypedQuery<ResDetailAvgAmountDto> query =
                em.createQuery(selectQuery, ResDetailAvgAmountDto.class).setMaxResults(1);
        return query.getSingleResult();
    }

    @Override
    public ResDetailAvgAmountDto selectMinGroupByYear() {

        String selectQuery =
                "SELECT new com.housing.finance.supportamount.dto.ResDetailAvgAmountDto(" +
                        "sa.bankName, sa.year, AVG(sa.amount) AS average)" +
                        "FROM SupportAmount sa " +
                        "GROUP BY sa.bankName, sa.year " +
                        "HAVING sa.bankName = '외환은행' AND sa.year >= 2005 AND sa.year <= 2016 And COUNT(sa.year) = 12" +
                        "Order by average";

        TypedQuery<ResDetailAvgAmountDto> query =
                em.createQuery(selectQuery, ResDetailAvgAmountDto.class).setMaxResults(1);
        return query.getSingleResult();
    }

}
