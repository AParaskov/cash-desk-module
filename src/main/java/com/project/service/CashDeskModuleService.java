package com.project.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.model.dto.CashBalanceResponse;
import com.project.model.dto.CashOperationRequest;
import com.project.model.dto.CashOperationResponse;

import java.io.IOException;

public interface CashDeskModuleService {
    CashOperationResponse cashOperation(CashOperationRequest cashOperationRequest) throws IOException;

    CashBalanceResponse checkBalanceAndDenominations() throws IOException;
}
