package com.project.model.dto;

import com.project.model.CashOperation;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CashOperationResponse {
    private Long id;
    private List<CashOperation> operation;
}
