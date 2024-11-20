package com.website.common.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductModel extends ModelBase{

    @NotBlank(message = "Event name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull
    @Future(message = "Date must be in future")
    private LocalDate eventDate;

    @Valid
    private ProductCategoryModel productCategory;
}
