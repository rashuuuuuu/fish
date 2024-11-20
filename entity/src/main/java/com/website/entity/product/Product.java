package com.website.entity.product;

import com.website.entity.AbstractEntity;
import com.website.entity.StatusProduct;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "product")
public class  Product extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "details", nullable = false)
    private String details;

    @Column(name = "recorded_date", nullable = false)
    private Date recordedDate;

    @Column(name = "image", nullable = false)
    private String image;

    @Column(name = "species", nullable = false)
    private String species;

    @Column(name = "code", nullable = false)
    private String code;

    @JoinColumn(name = "status_product", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private StatusProduct statusProduct;

    @Column(name = "is_present")
    private boolean isPresent;
}
