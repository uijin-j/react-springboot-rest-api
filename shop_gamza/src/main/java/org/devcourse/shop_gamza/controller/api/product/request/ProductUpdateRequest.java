package org.devcourse.shop_gamza.controller.api.product.request;

import jakarta.validation.constraints.PositiveOrZero;
import org.devcourse.shop_gamza.domain.product.SellingType;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

public record ProductUpdateRequest (
        @Length(max = 20)
        String name,
        @PositiveOrZero
        Integer price,
        String description,
        SellingType sellingType,
        @PositiveOrZero
        Integer stock,
        Long categoryId,
        MultipartFile coverImage) {
}
