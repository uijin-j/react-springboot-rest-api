package org.devcourse.shop_gamza.dto;

import org.devcourse.shop_gamza.domain.category.Category;

public record CategoryDTO (Long id, String name) {
    public static CategoryDTO of(Category category) {
        return new CategoryDTO(category.getId(), category.getName());
    }
}
