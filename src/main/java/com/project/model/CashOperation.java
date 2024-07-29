package com.project.model;

import com.project.model.enums.Operation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CashOperation {
    @NotBlank(message = "Name is required.")
    @Size(min = 2, max = 30)
    private String name;
    private Operation operation;
    @DecimalMin(value = "10")
    private BigDecimal amountBgn;
    @DecimalMin(value = "10")
    private BigDecimal amountEur;
    private List<@Valid Denomination> denominationBgn;
    private List<@Valid Denomination> denominationEur;
}
