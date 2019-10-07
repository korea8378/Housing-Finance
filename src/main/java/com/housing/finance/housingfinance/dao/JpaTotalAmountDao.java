package com.housing.finance.housingfinance.dao;

import com.housing.finance.housingfinance.dto.ResTotalAmountDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class JpaTotalAmountDao implements TotalAmountDao {

    private final EntityManager em;

    public JpaTotalAmountDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<ResTotalAmountDto> selectGroupByYear() {
        String selectQuery =
                "SELECT new com.housing.finance.housingfinance.dto.ResTotalAmountDto(" +
                        "hf.year," +
                        "SUM(hf.amount)," +
                        "SUM(CASE WHEN hf.name = '주택도시기금' THEN hf.amount ELSE 0 END)," +
                        "SUM(CASE WHEN hf.name = '국민은행' THEN hf.amount ELSE 0 END)," +
                        "SUM(CASE WHEN hf.name = '우리은행' THEN hf.amount ELSE 0 END)," +
                        "SUM(CASE WHEN hf.name = '신한은행' THEN hf.amount ELSE 0 END)," +
                        "SUM(CASE WHEN hf.name = '한국시티은행' THEN hf.amount ELSE 0 END)," +
                        "SUM(CASE WHEN hf.name = '하나은행' THEN hf.amount ELSE 0 END)," +
                        "SUM(CASE WHEN hf.name = '농협은행/수협은행' THEN hf.amount ELSE 0 END)," +
                        "SUM(CASE WHEN hf.name = '외환은행' THEN hf.amount ELSE 0 END)," +
                        "SUM(CASE WHEN hf.name = '기타은행' THEN hf.amount ELSE 0 END)) " +
                        "FROM HousingFinance hf GROUP BY hf.year order by hf.year";
        TypedQuery<ResTotalAmountDto> query =
                em.createQuery(selectQuery, ResTotalAmountDto.class);
        return query.getResultList();
    }
}
