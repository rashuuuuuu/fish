package com.website.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDetailsModel extends ModelBase {
    private String name;
    private String description;
    private LocalDate eventDate;
    private String code;
    private ProductCategoryModel productCategory;
}
