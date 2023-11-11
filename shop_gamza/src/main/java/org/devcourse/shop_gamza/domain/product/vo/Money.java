package org.devcourse.shop_gamza.domain.product.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.AssertTrue;

import java.util.Objects;

@Embeddable
public record Money (Integer amount) {
    public static Money create(Integer amount) {
        return new Money(amount);
    }
    public Money() {
        this(0);
    }

    @AssertTrue
    public boolean isValidAmount() {
        return amount != null && amount >= 0;
    }
}
