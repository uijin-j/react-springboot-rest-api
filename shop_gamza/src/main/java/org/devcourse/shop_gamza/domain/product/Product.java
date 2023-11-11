package org.devcourse.shop_gamza.domain.product;

import jakarta.persistence.*;
import lombok.*;
import org.devcourse.shop_gamza.domain.*;
import org.devcourse.shop_gamza.domain.category.Category;
import org.devcourse.shop_gamza.domain.product.vo.Money;
import org.devcourse.shop_gamza.domain.product.vo.Stock;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "price"))
    })
    private Money price;

    private String description;

    @Enumerated(EnumType.STRING)
    private SellingType sellingType;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "quantity", column = @Column(name = "stock"))
    })
    private Stock stock;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;
}
