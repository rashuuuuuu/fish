package com.website.admin.user.profile.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchLoggedInUserDetail extends ModelBase {
    @NotBlank(message = "Username is required.")
    private String username;
}
