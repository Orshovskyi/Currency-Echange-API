package com.whatof.currencyexchangeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionRequest {

    private String baseCurrency;
    private String targetCurrency;
    private BigDecimal amount;

}
