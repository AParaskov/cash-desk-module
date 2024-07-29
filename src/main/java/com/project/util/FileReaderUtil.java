package com.project.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.model.CashBalance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.*;

import static com.project.util.FileWriterUtil.CARD_BALANCES_AND_DENOMINATIONS_PATH;

@Component
@AllArgsConstructor
public class FileReaderUtil {
    private ObjectMapper objectMapper;

    public CashBalance readCardBalancesAndDenominations() throws IOException {
        new CashBalance();
        CashBalance cashBalance;
        try {
            cashBalance = objectMapper.readValue(new File(CARD_BALANCES_AND_DENOMINATIONS_PATH), CashBalance.class);
        }
        catch (IOException ioe) {
            throw new IOException("Error reading from file.");
        }
        return cashBalance;
    }
}
