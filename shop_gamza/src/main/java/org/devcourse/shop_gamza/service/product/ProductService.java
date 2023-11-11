package org.devcourse.shop_gamza.service.product;

import lombok.RequiredArgsConstructor;
import org.devcourse.shop_gamza.domain.category.Category;
import org.devcourse.shop_gamza.domain.image.Image;
import org.devcourse.shop_gamza.domain.product.Product;
import org.devcourse.shop_gamza.repositoy.product.ProductRepository;
import org.devcourse.shop_gamza.service.category.CategoryService;
import org.devcourse.shop_gamza.service.product.request.ProductCreateServiceRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    @Transactional
    public Product save(ProductCreateServiceRequest request) {
        Category category = categoryService.findById(request.categoryId());

        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .sellingType(request.sellingType())
                .stock(request.stock())
                .category(category)
                .coverImage(request.coverImage())
                .build();

        for(Image image : request.images()) {
            product.addImage(image);
        }

        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAllWithCoverImage();
    }
}
