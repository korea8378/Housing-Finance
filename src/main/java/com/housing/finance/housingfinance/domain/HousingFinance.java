package com.housing.finance.housingfinance.domain;

import javax.persistence.*;

@Entity
@Table(name = "housing_finance")
public class HousingFinance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long year;

    private Integer month;

    private String bankName;

    private Long bankId;

    private Long amount;
}
