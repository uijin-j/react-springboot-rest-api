package org.devcourse.shop_gamza.domain.product;

import jakarta.persistence.*;
import lombok.*;
import org.devcourse.shop_gamza.domain.image.Image;

import static java.lang.Boolean.FALSE;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_image_id")
    private Long id;

    @Builder.Default
    private Boolean isCoverImage = FALSE;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Image image;
}
