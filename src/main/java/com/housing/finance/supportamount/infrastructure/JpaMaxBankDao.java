package com.housing.finance.supportamount.infrastructure;

import com.housing.finance.supportamount.dao.MaxBankDao;
import com.housing.finance.supportamount.dto.ResMaxAmountDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class JpaMaxBankDao implements MaxBankDao {

    private final EntityManager em;

    public JpaMaxBankDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public ResMaxAmountDto selectMaxAmountGroupByYear() {

        String selectQuery =
                "SELECT new com.housing.finance.supportamount.dto.ResMaxAmountDto(" +
                        "sa.year, sa.name, SUM(sa.amount) AS total)" +
                        "FROM SupportAmount sa " +
                        "GROUP BY sa.name, sa.year HAVING COUNT(sa.year) = 12 " +
                        "Order by total DESC";

        TypedQuery<ResMaxAmountDto> query =
                em.createQuery(selectQuery, ResMaxAmountDto.class).setMaxResults(1);
        return query.getSingleResult();
    }
}
