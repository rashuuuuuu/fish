package com.website.admin.user.model.request;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteAdminRequest extends ModelBase {
    @NotBlank(message = "Email is required")
    private String email;
}
