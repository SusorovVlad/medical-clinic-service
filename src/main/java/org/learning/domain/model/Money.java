package org.learning.domain.model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PRIVATE) // for JPA
public class Money {

    private BigDecimal amount;

    public static Money of(BigDecimal amount) {
        Assert.isTrue(amount.intValue() >= 0, "Amount cannot be less than 0.");
        return new Money(amount);
    }

    private Money(BigDecimal amount) {
        this.amount = amount;
    }
}
