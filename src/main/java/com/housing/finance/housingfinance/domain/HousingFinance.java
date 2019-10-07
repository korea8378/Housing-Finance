package com.housing.finance.housingfinance.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "housing_finance")
@Getter
@NoArgsConstructor
public class HousingFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long year;

    private Long month;

    private String name;

    private Long amount;

    public HousingFinance(Long year, Long month, String name, Long amount) {
        this.year = year;
        this.month = month;
        this.name = name;
        this.amount = amount;
    }
}
