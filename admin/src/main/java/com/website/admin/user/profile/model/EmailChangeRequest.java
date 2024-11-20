package com.website.admin.user.profile.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailChangeRequest  extends ModelBase {
    @NotBlank(message = "uuid is required.")
    private String uuid;
    @NotBlank(message = "password is required.")
    private String password;

}
