package com.website.entity.product.productType;

import com.website.entity.AbstractEntity;
import com.website.entity.StatusProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Entity
@Table(name="fresh_salmon")
public class FreshSalmon extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "recorded_date", nullable = false)
    private Date recordedDate;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "quantity", nullable = false)
    private String quantity;

    @JoinColumn(name = "status_product", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StatusProduct statusproduct;
}
