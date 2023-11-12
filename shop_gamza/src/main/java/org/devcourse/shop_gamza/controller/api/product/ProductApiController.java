package org.devcourse.shop_gamza.controller.api.product;

import lombok.RequiredArgsConstructor;
import org.devcourse.shop_gamza.controller.api.ApiResponse;
import org.devcourse.shop_gamza.controller.api.product.request.ProductCreateRequest;
import org.devcourse.shop_gamza.controller.api.product.request.ProductUpdateRequest;
import org.devcourse.shop_gamza.controller.api.product.response.ProductDetailResponse;
import org.devcourse.shop_gamza.controller.api.product.response.ProductListResponse;
import org.devcourse.shop_gamza.domain.image.Image;
import org.devcourse.shop_gamza.domain.product.Product;
import org.devcourse.shop_gamza.domain.product.vo.Money;
import org.devcourse.shop_gamza.domain.product.vo.Stock;
import org.devcourse.shop_gamza.service.FileService;
import org.devcourse.shop_gamza.service.product.ProductService;
import org.devcourse.shop_gamza.service.product.request.ProductCreateServiceRequest;
import org.devcourse.shop_gamza.service.product.request.ProductUpdateServiceRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductApiController {
    private final ProductService productService;
    private final FileService fileService;

    @ResponseStatus(CREATED)
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

    @GetMapping("/{id}")
    public ApiResponse<ProductDetailResponse> findProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        ProductDetailResponse data = ProductDetailResponse.of(product);

        return ApiResponse.ok(data);
    }

    @PatchMapping("/{id}")
    public ApiResponse<Long> updateProduct(@PathVariable Long id, @Validated @ModelAttribute ProductUpdateRequest request) {
        Optional<Image> image = Optional.empty();
        if(request.coverImage() != null) {
            image = Optional.ofNullable(fileService.storeFile(request.coverImage()));
        }

        ProductUpdateServiceRequest serviceRequest = ProductUpdateServiceRequest.builder()
                .name(Optional.ofNullable(request.name()))
                .price((request.price() == null)? Optional.empty() : Optional.of(Money.create(request.price())))
                .description(Optional.ofNullable(request.description()))
                .sellingType(Optional.ofNullable(request.sellingType()))
                .stock((request.stock() == null)? Optional.empty() : Optional.of(Stock.create(request.stock())))
                .categoryId(Optional.ofNullable(request.categoryId()))
                .coverImage(image)
                .build();
        Long productId = productService.updateProduct(id, serviceRequest);

        return ApiResponse.ok(productId);
    }
}
