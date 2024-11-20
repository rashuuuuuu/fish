package com.website.admin.user.model;

import com.website.admin.accessgroup.model.AccessGroupDto;
import com.website.common.dto.ModelBase;
import com.website.common.dto.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAdminUserResponse extends ModelBase {
    private String name;
    private String email;
    private String mobileNumber;
    private AccessGroupDto accessGroup;
    private StatusDto status;
}
