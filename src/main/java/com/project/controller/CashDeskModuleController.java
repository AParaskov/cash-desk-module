package com.project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.hash.Hashing;
import com.project.model.dto.CashBalanceResponse;
import com.project.model.dto.CashOperationRequest;
import com.project.service.CashDeskModuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CashDeskModuleController {

    private final CashDeskModuleService cashDeskModuleService;
    @Value("${custom.request.header}")
    private String customRequestHeaderValue;

    @PostMapping(value = "/cash-operation", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> cashOperation(@RequestHeader("FIB-X-AUTH") String customRequestHeader,
                                                               @Valid @RequestBody CashOperationRequest cashOperationRequest) throws IOException {

        if (!Hashing.sha256().hashString(customRequestHeader, StandardCharsets.UTF_8).toString().equals(customRequestHeaderValue)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().body(cashDeskModuleService.cashOperation(cashOperationRequest));
    }

    @GetMapping(value = "/cash-balance", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CashBalanceResponse> cashBalance(@RequestHeader("FIB-X-AUTH") String customRequestHeader) throws IOException {
        if (!Hashing.sha256().hashString(customRequestHeader, StandardCharsets.UTF_8).toString().equals(customRequestHeaderValue)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().body(cashDeskModuleService.checkBalanceAndDenominations());
    }
}
