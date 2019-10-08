package com.housing.finance.housingfinance.application;

public enum CSVHeader {

    YEAR("연도", "연도"),
    MONTH("월", "월"),
    HOUSING_CITY_FUND("주택도시기금1)(억원)", "주택도시기금"),
    KOOKMIN_BANK("국민은행(억원)", "국민은행"),
    WOORI_BANK("우리은행(억원)", "우리은행"),
    SHINHAN_BANK("신한은행(억원)", "신한은행"),
    KOREA_CITY_BANK("한국시티은행(억원)", "한국시티은행"),
    HANA_BANK("하나은행(억원)", "하나은행"),
    NONGHYUP_SUHYUP_BANK("농협은행/수협은행(억원)", "농협은행/수협은행"),
    KOREA_EXCHANGE_BANK("외환은행(억원)", "외환은행"),
    ETC_BANK("기타은행(억원)", "기타은행");

    private String value;
    private String name;

    CSVHeader(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
