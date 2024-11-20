package com.website.admin.product.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteProductRequest extends ModelBase {

    @NotBlank(message = "Code cannot be blank")
    private String code;
}
