package com.website.admin.customer.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewCustomerRequest extends ModelBase {

    @NotBlank(message = "Email cannot be blank")
    private String email;
}
