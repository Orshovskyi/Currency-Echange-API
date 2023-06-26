package com.whatof.currencyexchangeapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConversionResponse {

    private BigDecimal originalAmount;
    private String baseCurrency;
    private BigDecimal convertedAmount;
    private String targetCurrency;

}
