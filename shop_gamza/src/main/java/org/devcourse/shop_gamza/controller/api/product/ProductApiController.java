package org.devcourse.shop_gamza.controller.api.product;

import lombok.RequiredArgsConstructor;
import org.devcourse.shop_gamza.controller.api.ApiResponse;
import org.devcourse.shop_gamza.controller.api.product.request.ProductCreateRequest;
import org.devcourse.shop_gamza.controller.api.product.response.ProductDetailResponse;
import org.devcourse.shop_gamza.controller.api.product.response.ProductListResponse;
import org.devcourse.shop_gamza.domain.image.Image;
import org.devcourse.shop_gamza.domain.product.Product;
import org.devcourse.shop_gamza.domain.product.vo.Money;
import org.devcourse.shop_gamza.domain.product.vo.Stock;
import org.devcourse.shop_gamza.service.FileService;
import org.devcourse.shop_gamza.service.product.ProductService;
import org.devcourse.shop_gamza.service.product.request.ProductCreateServiceRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;
    private final FileService fileService;

    @PostMapping
    public ApiResponse<ProductDetailResponse> createProduct(@Validated @ModelAttribute ProductCreateRequest request) {
        Image coverImage = fileService.storeFile(request.coverImage());
        List<Image> images = fileService.storeFiles(request.images());

        ProductCreateServiceRequest serviceRequest = ProductCreateServiceRequest.builder()
                .name(request.name())
                .price(Money.create(request.price()))
                .description(request.description())
                .sellingType(request.sellingType())
                .stock(Stock.create(request.stock()))
                .categoryId(request.categoryId())
                .coverImage(coverImage)
                .images(images)
                .build();

        Product saved = productService.save(serviceRequest);
        ProductDetailResponse data = ProductDetailResponse.of(saved);

        return ApiResponse.of(CREATED, data);
    }

    @GetMapping
    public ApiResponse<List<ProductListResponse>> findAllProducts() {
        List<Product> products = productService.findAll();
        List<ProductListResponse> data = products.stream()
                .map(ProductListResponse::of)
                .toList();

        return ApiResponse.ok(data);
    }
}
