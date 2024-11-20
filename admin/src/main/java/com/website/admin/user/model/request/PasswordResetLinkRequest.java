package com.website.admin.user.model.request;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetLinkRequest extends ModelBase {
    @NotBlank(message = "Please enter the email address associated with your account.")
    @Email(message = "Please enter a valid email address.")
    private String email;
}
