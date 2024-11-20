package com.website.admin.user.model.request;

import com.website.admin.accessgroup.model.AccessGroupDto;
import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAdminRequest extends ModelBase {

    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Mobile Number cannot be null")
    private String mobileNumber;
    @NotBlank(message = "Address cannot be null")
    private String address;
    @NotNull
    private com.website.admin.accessgroup.model.AccessGroupDto accessGroup;
}
