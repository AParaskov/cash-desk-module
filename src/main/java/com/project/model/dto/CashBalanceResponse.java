package com.project.model.dto;

import com.project.model.Denomination;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class CashBalanceResponse {
    private String name;
    private BigDecimal balanceBgn;
    private BigDecimal balanceEur;
    private List<Denomination> depositDenominationsBgn;
    private List<Denomination> withdrawalDenominationsBgn;
    private List<Denomination> depositDenominationsEur;
    private List<Denomination> withdrawalDenominationsEur;
}
