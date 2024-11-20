package com.website.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCategoryModel extends ModelBase{
    @NotBlank(message = "Please select event category name")
    private String name;
    private String description;
    private StatusDto status;
}

