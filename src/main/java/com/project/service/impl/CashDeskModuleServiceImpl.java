package com.project.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.model.CashBalance;
import com.project.model.dto.CashBalanceResponse;
import com.project.model.dto.CashOperationRequest;
import com.project.model.dto.CashOperationResponse;
import com.project.service.CashDeskModuleService;
import com.project.util.FileReaderUtil;
import com.project.util.FileWriterUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class CashDeskModuleServiceImpl implements CashDeskModuleService {
    private final FileReaderUtil fileReader;
    private final FileWriterUtil fileWriter;

    @Override
    public CashOperationResponse cashOperation(CashOperationRequest cashOperationRequest) throws IOException {
        CashBalance currentCashBalance = fileReader.readCardBalancesAndDenominations();
        cashOperationRequest.getOperation().forEach(cashOperation -> {
            if (cashOperation.getOperation().name().equals("DEPOSIT")) {
                cashOperation.getDenominationBgn().forEach(denomination -> {
                    currentCashBalance.getDepositDenominationsBgn().add(denomination);
                });
                cashOperation.getDenominationEur().forEach(denomination -> {
                    currentCashBalance.getDepositDenominationsEur().add(denomination);
                });

                fileWriter.writeCardBalancesAndDenominations(CashBalance.builder()
                        .name(cashOperation.getName())
                        .balanceBgn(currentCashBalance.getBalanceBgn().add(cashOperation.getAmountBgn()))
                        .balanceEur(currentCashBalance.getBalanceEur().add(cashOperation.getAmountEur()))
                        .depositDenominationsBgn(currentCashBalance.getDepositDenominationsBgn())
                        .depositDenominationsEur(currentCashBalance.getDepositDenominationsEur())
                        .withdrawalDenominationsBgn(currentCashBalance.getWithdrawalDenominationsBgn())
                        .withdrawalDenominationsEur(currentCashBalance.getWithdrawalDenominationsEur())
                        .build());
            } else {
                cashOperation.getDenominationBgn().forEach(denomination -> {
                    currentCashBalance.getWithdrawalDenominationsBgn().add(denomination);
                });
                cashOperation.getDenominationEur().forEach(denomination -> {
                    currentCashBalance.getWithdrawalDenominationsEur().add(denomination);
                });

                fileWriter.writeCardBalancesAndDenominations(CashBalance.builder()
                        .name(cashOperation.getName())
                        .balanceBgn(currentCashBalance.getBalanceBgn().subtract(cashOperation.getAmountBgn()))
                        .balanceEur(currentCashBalance.getBalanceEur().subtract(cashOperation.getAmountEur()))
                        .depositDenominationsBgn(currentCashBalance.getDepositDenominationsBgn())
                        .depositDenominationsEur(currentCashBalance.getDepositDenominationsEur())
                        .withdrawalDenominationsBgn(currentCashBalance.getWithdrawalDenominationsBgn())
                        .withdrawalDenominationsEur(currentCashBalance.getWithdrawalDenominationsEur())
                        .build());
            }
        });

        fileWriter.writeTransactionHistory(cashOperationRequest);
        return CashOperationResponse.builder()
                .id(cashOperationRequest.getId())
                .operation(cashOperationRequest.getOperation())
                .build();
    }

    @Override
    public CashBalanceResponse checkBalanceAndDenominations() throws IOException {
        CashBalance currentCashBalance = fileReader.readCardBalancesAndDenominations();

        return CashBalanceResponse.builder()
                .name(currentCashBalance.getName())
                .balanceBgn(currentCashBalance.getBalanceBgn())
                .balanceEur(currentCashBalance.getBalanceEur())
                .depositDenominationsBgn(currentCashBalance.getDepositDenominationsBgn())
                .depositDenominationsEur(currentCashBalance.getDepositDenominationsEur())
                .withdrawalDenominationsBgn(currentCashBalance.getWithdrawalDenominationsBgn())
                .withdrawalDenominationsEur(currentCashBalance.getWithdrawalDenominationsEur())
                .build();
    }
}
