package com.housing.finance.supportamount.domain.supportamount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupportAmountRepository extends JpaRepository<SupportAmount, Long> {
    List<SupportAmount> findByBankName(String bank);
}
