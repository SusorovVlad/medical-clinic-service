package org.learning.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import java.math.BigDecimal;

public class MoneyConverterTest {

    @Test
    public void conversionReturnsExpectedResult() {

        Money convert = new MoneyConverter().convert(BigDecimal.TEN);

        assertThat(convert).returns(BigDecimal.TEN, Money::getAmount);
    }
}
