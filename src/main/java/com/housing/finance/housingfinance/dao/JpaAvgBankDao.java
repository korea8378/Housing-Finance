package com.housing.finance.housingfinance.dao;

import com.housing.finance.housingfinance.dto.ResDetailAvgAmountDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
public class JpaAvgBankDao implements AvgBankDao{

    private final EntityManager em;

    public JpaAvgBankDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public ResDetailAvgAmountDto selectMaxAvgAmountGroupByYear() {

        String selectQuery =
                "SELECT new com.housing.finance.housingfinance.dto.ResDetailAvgAmountDto(" +
                        "hf.name, hf.year, AVG(hf.amount) AS average)" +
                        "FROM HousingFinance hf " +
                        "GROUP BY hf.name, hf.year " +
                        "HAVING hf.name = '외환은행' AND hf.year >= 2005 AND hf.year <= 2016" +
                        "Order by average DESC";

        TypedQuery<ResDetailAvgAmountDto> query =
                em.createQuery(selectQuery, ResDetailAvgAmountDto.class).setMaxResults(1);
        return query.getSingleResult();
    }

    @Override
    public ResDetailAvgAmountDto selectMinAvgAmountGroupByYear() {

        String selectQuery =
                "SELECT new com.housing.finance.housingfinance.dto.ResDetailAvgAmountDto(" +
                        "hf.name, hf.year, AVG(hf.amount) AS average)" +
                        "FROM HousingFinance hf " +
                        "GROUP BY hf.name, hf.year " +
                        "HAVING hf.name = '외환은행' AND hf.year >= 2005 AND hf.year <= 2016" +
                        "Order by average";

        TypedQuery<ResDetailAvgAmountDto> query =
                em.createQuery(selectQuery, ResDetailAvgAmountDto.class).setMaxResults(1);
        return query.getSingleResult();
    }
}
