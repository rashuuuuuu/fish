package com.website.admin.user.model;

import com.website.admin.accessgroup.model.AccessGroupDto;
import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAdminModel extends ModelBase {
    @NotBlank(message = "Name Cannot Be Blank")
    private String name;
    @Size(min=10, max=10, message = "Invalid mobile number format")
    @NotBlank(message = "Mobile Number Cannot Be Blank")
    private String mobileNumber;
    @NotBlank(message = "Address Cannot Be Blank")
    private String address;
    @Email
    @NotBlank(message = "Email Cannot Be Null")
    private String email;
    @NotNull(message = "Access Group Cannot Be Null")
    private AccessGroupDto accessGroup;
}
