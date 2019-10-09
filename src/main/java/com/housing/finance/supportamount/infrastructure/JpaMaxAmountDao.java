package com.housing.finance.supportamount.infrastructure;

import com.housing.finance.supportamount.dao.MaxAmountDao;
import com.housing.finance.supportamount.dto.ResMaxAmountDto;
import com.housing.finance.supportamount.infrastructure.exception.NotFoundAmountException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class JpaMaxAmountDao implements MaxAmountDao {

    private final EntityManager em;

    public JpaMaxAmountDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public ResMaxAmountDto selectGroupByYear() {
        ResMaxAmountDto resMaxAmountDto;

        String selectQuery =
                "SELECT new com.housing.finance.supportamount.dto.ResMaxAmountDto(" +
                        "sa.year, sa.bankName, SUM(sa.amount) AS total)" +
                        "FROM SupportAmount sa " +
                        "GROUP BY sa.bankName, sa.year HAVING COUNT(sa.year) = 12 " +
                        "Order by total DESC";

        TypedQuery<ResMaxAmountDto> query =
                em.createQuery(selectQuery, ResMaxAmountDto.class).setMaxResults(1);
        try {
            resMaxAmountDto = query.getSingleResult();
        } catch (NoResultException e) {
            throw new NotFoundAmountException();
        }

        return resMaxAmountDto;
    }
}
