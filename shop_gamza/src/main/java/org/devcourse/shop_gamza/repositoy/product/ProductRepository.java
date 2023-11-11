package org.devcourse.shop_gamza.repositoy.product;

import org.devcourse.shop_gamza.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
