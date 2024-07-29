package com.project.util;

import com.project.model.CashBalance;
import com.project.model.Denomination;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static com.project.util.FileWriterUtil.CARD_BALANCES_AND_DENOMINATIONS_PATH;

@Component
@AllArgsConstructor
public class InitialDataWriteUtil {
    private FileWriterUtil fileWriterUtil;

    @Scheduled(initialDelay = 1000, fixedDelay=Long.MAX_VALUE)
    public void initialDataWrite() {
        if (!new File(CARD_BALANCES_AND_DENOMINATIONS_PATH).isFile()) {
            fileWriterUtil.writeCardBalancesAndDenominations(CashBalance.builder()
                    .name("MARTINA")
                    .balanceBgn(BigDecimal.valueOf(1000))
                    .balanceEur(BigDecimal.valueOf(2000))
                    .depositDenominationsBgn(Arrays.asList(new Denomination(50, "10 BGN"), new Denomination(10, "50 BGN")))
                    .depositDenominationsEur(Arrays.asList(new Denomination(100, "10 EUR"), new Denomination(20, "50 EUR")))
                    .withdrawalDenominationsBgn(Collections.emptyList())
                    .withdrawalDenominationsEur(Collections.emptyList())
                    .build());
        }
    }
}
