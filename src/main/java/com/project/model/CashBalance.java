package com.project.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CashBalance {
    private String name;
    private BigDecimal balanceBgn;
    private BigDecimal balanceEur;
    private List<Denomination> depositDenominationsBgn;
    private List<Denomination> withdrawalDenominationsBgn;
    private List<Denomination> depositDenominationsEur;
    private List<Denomination> withdrawalDenominationsEur;
}
