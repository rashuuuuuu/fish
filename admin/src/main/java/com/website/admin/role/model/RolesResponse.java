package com.website.admin.role.model;

import com.website.common.dto.ModelBase;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolesResponse extends ModelBase {
    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
    private String parentName;
    private List<RolesResponse> childRoles;
}
