package org.devcourse.shop_gamza.controller.api.product.request;

import lombok.Builder;
import org.devcourse.shop_gamza.domain.category.Category;
import org.devcourse.shop_gamza.domain.image.Image;
import org.devcourse.shop_gamza.domain.product.Product;
import org.devcourse.shop_gamza.domain.product.ProductImage;
import org.devcourse.shop_gamza.domain.product.SellingType;

import java.util.List;

@Builder
public record ProductDetailResponse(
        Long id,
        String name,
        Integer price,
        String description,
        SellingType sellingType,
        Integer stock,
        Category category,
        Image coverImage,
        List<Image> images) {

    public static ProductDetailResponse of(Product product) {
        List<Image> imageList = product.getImages().stream()
                .map(ProductImage::getImage)
                .toList();

        return ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice().amount())
                .description(product.getDescription())
                .sellingType(product.getSellingType())
                .stock(product.getStock().quantity())
                .category(product.getCategory())
                .coverImage(product.getCoverImage())
                .images(imageList)
                .build();
    }
}
