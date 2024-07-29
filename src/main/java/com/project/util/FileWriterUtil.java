package com.project.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.CashBalance;
import com.project.model.dto.CashOperationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
@AllArgsConstructor
public class FileWriterUtil {
    public static final String TRANSACTION_HISTORY_PATH = "src/main/resources/files/transaction_history.txt";
    public static final String CARD_BALANCES_AND_DENOMINATIONS_PATH = "src/main/resources/files/card_balances_and_denominations.txt";
    private ObjectMapper objectMapper;

    public void writeTransactionHistory(CashOperationRequest cashOperationRequest) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTION_HISTORY_PATH, true));
            String formattedFileInput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cashOperationRequest) + "\n";
            writer.write(formattedFileInput);
            writer.close();
        }
        catch (IOException ioe) {
            throw new IOException("Error writing to file.");
        }
    }

    public void writeCardBalancesAndDenominations(CashBalance cashBalance) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(CARD_BALANCES_AND_DENOMINATIONS_PATH));
            String formattedFileInput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cashBalance);
            writer.write(formattedFileInput);
            writer.close();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void initialWriteCardBalancesAndDenominations() {

    }

}
