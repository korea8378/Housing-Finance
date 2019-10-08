package com.housing.finance.housingfinance.infrastructure;

import com.housing.finance.housingfinance.dao.MaximumBankDao;
import com.housing.finance.housingfinance.dto.ResMaximumOfBankDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class JpaMaximumBankDao implements MaximumBankDao {

    private final EntityManager em;

    public JpaMaximumBankDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public ResMaximumOfBankDto selectMaximumAmountGroupByYear() {

        String selectQuery =
                "SELECT new com.housing.finance.housingfinance.dto.ResMaximumOfBankDto(" +
                        "hf.year, hf.name, SUM(hf.amount) AS total)" +
                        "FROM HousingFinance hf " +
                        "GROUP BY hf.name, hf.year HAVING COUNT(hf.year) = 12 " +
                        "Order by total DESC";

        TypedQuery<ResMaximumOfBankDto> query =
                em.createQuery(selectQuery, ResMaximumOfBankDto.class).setMaxResults(1);
        return query.getSingleResult();
    }
}
