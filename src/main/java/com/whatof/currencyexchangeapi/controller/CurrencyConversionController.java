package com.whatof.currencyexchangeapi.controller;

import com.whatof.currencyexchangeapi.model.CurrencyConversionRequest;
import com.whatof.currencyexchangeapi.model.CurrencyConversionResponse;
import com.whatof.currencyexchangeapi.service.CurrencyConversionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("conversion")
public class CurrencyConversionController {

    private final CurrencyConversionService currencyConversionService;

    @GetMapping
    public ResponseEntity<CurrencyConversionResponse> convertCurrency(
            @Valid @RequestBody CurrencyConversionRequest conversionRequest) {
        BigDecimal convertedAmount = currencyConversionService.convertCurrency(conversionRequest)
                .orElse(null);

        if (convertedAmount == null) {
            return ResponseEntity.notFound().build();
        }

        CurrencyConversionResponse conversionResponse = new CurrencyConversionResponse();
        conversionResponse.setOriginalAmount(conversionRequest.getAmount());
        conversionResponse.setBaseCurrency(conversionRequest.getBaseCurrency());
        conversionResponse.setConvertedAmount(convertedAmount);
        conversionResponse.setTargetCurrency(conversionRequest.getTargetCurrency());

        return ResponseEntity.ok(conversionResponse);
    }
}
