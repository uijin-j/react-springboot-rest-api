package org.devcourse.shop_gamza.domain.product.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class StockTest {
    @DisplayName("재고 객체를 생성할 수 있다.")
    @Test
    void create() {
        // given
        Integer quantity = 0;

        // when
        Stock created = Stock.create(quantity);

        // then
        assertThat(created).isNotNull();
        assertThat(created.quantity()).isEqualTo(quantity);
    }

    @DisplayName("재고 수량이 null인 재고 객체는 생성할 수 없다.")
    @Test
    void createWithNull() {
        // given
        Integer nullQuantity = null;

        // when then
        assertThatThrownBy(() -> Stock.create(nullQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고 수량은 null일 수 없습니다.");
    }

    @DisplayName("재고 수량이 음수인 재고 객체는 생성할 수 없다.")
    @Test
    void createWithNegativeQuantity() {
        // given
        Integer negativeQuantity = -100;

        // when then
        assertThatThrownBy(() -> Stock.create(negativeQuantity))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("재고 수량은 음수일 수 없습니다.");
    }
}