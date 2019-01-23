package org.learning.domain.model;

import static org.assertj.core.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.math.BigDecimal;

public class MoneyTest {

    @Test
    public void creation() {

        BigDecimal ten = BigDecimal.TEN;
        Money money = Money.of(ten);

        assertThat(money).returns(ten, Money::getAmount);
    }

    @Test
    public void throwsExceptionWhenAmountIsLessThenZero() {
        assertThatIllegalArgumentException()
                        .isThrownBy(() -> Money.of(BigDecimal.valueOf(-1)))
                        .withMessage("Amount cannot be less than 0.");
    }

    @Test
    public void equalsContract() {

        EqualsVerifier.forClass(Money.class)
                      .verify();
    }
}
