package com.project.model.dto;

import com.project.model.CashOperation;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Getter
public class CashOperationRequest {
    @Min(1)
    private Long id;
    private List<@Valid CashOperation> operation;
}
