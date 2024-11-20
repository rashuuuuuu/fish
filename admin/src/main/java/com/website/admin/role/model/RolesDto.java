package com.website.admin.role.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolesDto {
    private String name;
    private String description;
    private String icon;
    private String navigation;
    private Integer position;
    private String uiGroupName;
    private String parentName;
    private String permission;
}
