package com.housing.finance.supportamount.domain.supportamount;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "support_amount")
@Getter
@NoArgsConstructor
public class SupportAmount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long year;

    private Long month;

    private String bankName;

    private Long amount;

    public SupportAmount(Long year, Long month, String bankName, Long amount) {
        this.year = year;
        this.month = month;
        this.bankName = bankName;
        this.amount = amount;
    }
}
