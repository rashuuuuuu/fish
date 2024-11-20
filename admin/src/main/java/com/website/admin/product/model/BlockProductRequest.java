package com.website.admin.product.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockProductRequest extends ModelBase {
    @NotBlank(message = "Code cannot be blank")
    private String code;
    @NotBlank(message = "Remarks is required")
    private String remarks;
}
