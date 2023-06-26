package com.whatof.currencyexchangeapi.controller;

import com.whatof.currencyexchangeapi.model.CurrencyRate;
import com.whatof.currencyexchangeapi.service.CurrencyRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rates")
@RequiredArgsConstructor
public class CurrencyRateController {
    private final CurrencyRateService currencyRateService;

    @GetMapping
    public ResponseEntity<List<CurrencyRate>> getAllCurrencyRates() {
        List<CurrencyRate> currencyRates = currencyRateService.getAllCurrencyRates();
        return ResponseEntity.ok(currencyRates);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurrencyRate> getCurrencyRateById(@PathVariable("id") Long id) {
        return currencyRateService.getCurrencyRateById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CurrencyRate> createCurrencyRate(@Valid @RequestBody CurrencyRate currencyRate) {
        CurrencyRate savedCurrencyRate = currencyRateService.saveCurrencyRate(currencyRate);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCurrencyRate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurrencyRate> updateCurrencyRate(
            @PathVariable("id") Long id,
            @Valid @RequestBody CurrencyRate currencyRate) {
        return currencyRateService.getCurrencyRateById(id)
                .map(existingRate -> {
                    existingRate.setBaseCurrency(currencyRate.getBaseCurrency());
                    existingRate.setTargetCurrency(currencyRate.getTargetCurrency());
                    existingRate.setRate(currencyRate.getRate());
                    CurrencyRate updatedRate = currencyRateService.saveCurrencyRate(existingRate);
                    return ResponseEntity.ok(updatedRate);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrencyRate(@PathVariable("id") Long id) {
        currencyRateService.deleteCurrencyRate(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{baseCurrency}/{targetCurrency}")
    public ResponseEntity<BigDecimal> getCurrencyRateByPair(
            @PathVariable String baseCurrency,
            @PathVariable String targetCurrency) {
        Optional<BigDecimal> optionalRate = currencyRateService.getCurrencyRateByPair(baseCurrency, targetCurrency);
        if (optionalRate.isPresent()) {
            BigDecimal rate = optionalRate.get();
            return ResponseEntity.ok(rate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

