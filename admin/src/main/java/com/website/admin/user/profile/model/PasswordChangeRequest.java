package com.website.admin.user.profile.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PasswordChangeRequest extends ModelBase {

    @NotBlank(message = "please enter old password")
    private String oldPassword;
    @NotBlank(message = "please enter new password")
    private String newPassword;
    @NotBlank(message = "please retype new password")
    private String retypeNewPassword;
}
