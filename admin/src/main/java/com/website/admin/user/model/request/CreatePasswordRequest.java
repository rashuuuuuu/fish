package com.website.admin.user.model.request;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePasswordRequest extends ModelBase {
    @NotBlank(message = "Password field cannot be blank")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d)(?=.*[a-z]).{8,15}$",
            message = "Invalid password format"
    )
    private String password;
    @NotBlank(message = "Confirm password field cannot be blank")
    @Pattern(
            regexp = "^(?=.*[!@#$%^&*(),.?\":{}|<>])(?=.*\\d)(?=.*[a-z]).{8,15}$",
            message = "Invalid password format"
    )
    private String confirmPassword;
    @NotBlank(message = "UUID field cannot be blank")
    private String uuid;
}
