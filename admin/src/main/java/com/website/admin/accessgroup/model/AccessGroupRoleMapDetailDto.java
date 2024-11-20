package com.website.admin.accessgroup.model;

import com.website.common.dto.ModelBase;
import com.website.admin.role.model.RolesDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessGroupRoleMapDetailDto extends ModelBase {
    private Boolean isActive;
    private RolesDto roles;
}
