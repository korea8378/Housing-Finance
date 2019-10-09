package com.housing.finance.supportamount.infrastructure;

import com.housing.finance.supportamount.dao.TotalAmountDao;
import com.housing.finance.supportamount.dto.ResDetailTotalAmountDto;
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
    public List<ResDetailTotalAmountDto> selectGroupByYear() {
        String selectQuery =
                "SELECT new com.housing.finance.supportamount.dto.ResDetailTotalAmountDto(" +
                        "sa.year," +
                        "SUM(sa.amount)," +
                        "SUM(CASE WHEN sa.bankName = '주택도시기금' THEN sa.amount ELSE 0 END)," +
                        "SUM(CASE WHEN sa.bankName = '국민은행' THEN sa.amount ELSE 0 END)," +
                        "SUM(CASE WHEN sa.bankName = '우리은행' THEN sa.amount ELSE 0 END)," +
                        "SUM(CASE WHEN sa.bankName = '신한은행' THEN sa.amount ELSE 0 END)," +
                        "SUM(CASE WHEN sa.bankName = '한국시티은행' THEN sa.amount ELSE 0 END)," +
                        "SUM(CASE WHEN sa.bankName = '하나은행' THEN sa.amount ELSE 0 END)," +
                        "SUM(CASE WHEN sa.bankName = '농협은행/수협은행' THEN sa.amount ELSE 0 END)," +
                        "SUM(CASE WHEN sa.bankName = '외환은행' THEN sa.amount ELSE 0 END)," +
                        "SUM(CASE WHEN sa.bankName = '기타은행' THEN sa.amount ELSE 0 END)) " +
                        "FROM SupportAmount sa GROUP BY sa.year order by sa.year";
        TypedQuery<ResDetailTotalAmountDto> query =
                em.createQuery(selectQuery, ResDetailTotalAmountDto.class);
        return query.getResultList();
    }
}
