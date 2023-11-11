package org.devcourse.shop_gamza.repositoy.product;

import org.devcourse.shop_gamza.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.coverImage")
    List<Product> findAllWithCoverImage();
}
