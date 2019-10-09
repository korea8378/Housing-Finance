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

    private String name;

    private Long amount;

    public SupportAmount(Long year, Long month, String name, Long amount) {
        this.year = year;
        this.month = month;
        this.name = name;
        this.amount = amount;
    }
}
