package org.learning.domain.model;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.math.BigDecimal;

public class MoneyConverter extends StdConverter<BigDecimal, Money> {

    @Override
    public Money convert(BigDecimal bigDecimal) {
        return Money.of(bigDecimal);
    }
}
