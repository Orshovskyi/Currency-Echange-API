package com.whatof.currencyexchangeapi.service;

import com.whatof.currencyexchangeapi.model.CurrencyRate;
import com.whatof.currencyexchangeapi.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CurrencyRateService {
    private final CurrencyRateRepository currencyRateRepository;

    public List<CurrencyRate> getAllCurrencyRates() {
        return currencyRateRepository.findAll();
    }

    public Optional<CurrencyRate> getCurrencyRateById(Long id) {
        return currencyRateRepository.findById(id);
    }

    public CurrencyRate saveCurrencyRate(CurrencyRate currencyRate) {
        return currencyRateRepository.save(currencyRate);
    }

    public void deleteCurrencyRate(Long id) {
        currencyRateRepository.deleteById(id);
    }

    public Optional<BigDecimal> getCurrencyRateByPair(String baseCurrency, String targetCurrency) {
        Optional<CurrencyRate> optionalRate = currencyRateRepository.findByBaseCurrencyAndTargetCurrency(baseCurrency, targetCurrency);
        return optionalRate.map(CurrencyRate::getRate);
    }
}
