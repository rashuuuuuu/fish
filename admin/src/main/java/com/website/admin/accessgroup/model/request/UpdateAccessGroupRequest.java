package com.website.admin.accessgroup.model.request;

import com.website.admin.accessgroup.model.AssignRoleModel;
import com.website.common.dto.ModelBase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UpdateAccessGroupRequest extends ModelBase {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "description cannot be blank")
    private String description;
    @Valid
    @NotNull
    private List<AssignRoleModel> roles;
}
