package com.website.admin.product.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductService extends ModelBase {

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String details;

    @NotBlank(message = "Image cannot be blank")
    private String image;

    @NotBlank(message = "species cannot be blank")
    private String species;

}
