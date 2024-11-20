package com.website.admin.accessgroup.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateAccessGroupModel extends ModelBase {
    @NotBlank(message = "Name is required.")
    private String name;
    @NotBlank(message = "Descriptions is required.")
    private String description;

    @Valid
    private List<AssignRoleModel> roles;
}
