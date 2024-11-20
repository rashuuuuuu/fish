package com.website.admin.user.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchAdminDetail extends ModelBase {
    @NotBlank(message = "email is required.")
    private String email;
}
