package com.website.admin.accessgroup.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssignRoleModel extends ModelBase {
    @NotNull(message = "Role ID is required.")
    private Long roleId;
}
