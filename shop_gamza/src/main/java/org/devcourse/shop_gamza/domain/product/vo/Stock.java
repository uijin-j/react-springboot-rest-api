package org.devcourse.shop_gamza.domain.product.vo;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.AssertTrue;

@Embeddable
public record Stock(Integer quantity) {
    public static Stock create(Integer quantity) {
        return new Stock(quantity);
    }

    // @Embeddable을 위해 기본 생성자 필요
    protected Stock() {
        this(0);
    }

    @AssertTrue
    public boolean isValidQuantity() {
        return quantity != null && quantity >= 0;
    }
}
