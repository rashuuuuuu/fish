package com.website.admin.product.model;
import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductRequest extends ModelBase {

    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Image cannot be blank")
    private String image;

}
