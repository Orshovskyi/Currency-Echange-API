package com.whatof.currencyexchangeapi.service;

import com.whatof.currencyexchangeapi.model.CurrencyConversionRequest;
import com.whatof.currencyexchangeapi.model.CurrencyConversionResponse;
import com.whatof.currencyexchangeapi.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CurrencyConversionService {

    private final CurrencyRateService currencyRateService;


    public Optional<BigDecimal> convertCurrency(CurrencyConversionRequest conversionRequest) {
        Optional<BigDecimal> currencyRate = currencyRateService.getCurrencyRateByPair(conversionRequest.getBaseCurrency(), conversionRequest.getTargetCurrency());
        return currencyRate.map(rate -> rate.multiply(conversionRequest.getAmount()));
    }
}
