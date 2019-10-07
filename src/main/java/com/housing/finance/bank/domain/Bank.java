package com.housing.finance.bank.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "bank")
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;
}
