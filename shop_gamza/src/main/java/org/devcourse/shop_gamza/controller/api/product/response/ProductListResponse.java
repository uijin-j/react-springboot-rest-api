package org.devcourse.shop_gamza.controller.api.product.response;

import lombok.Builder;
import org.devcourse.shop_gamza.domain.image.Image;
import org.devcourse.shop_gamza.domain.product.Product;
import org.devcourse.shop_gamza.domain.product.SellingType;

@Builder
public record ProductListResponse (
        Long id,
        String name,
        Integer price,
        SellingType sellingType,
        Image coverImage
) {
    public static ProductListResponse of(Product product) {
        return ProductListResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice().amount())
                .sellingType(product.getSellingType())
                .coverImage(product.getCoverImage())
                .build();
    }
}
