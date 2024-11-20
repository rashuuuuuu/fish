package com.website.admin.user.profile.model;

import com.website.admin.accessgroup.model.AccessGroupDto;
import com.website.common.dto.ModelBase;
import com.website.common.dto.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggedInUserDetailDto extends ModelBase {
    private String name;
    private String username;
    private String email;
    private String mobileNumber;
    private StatusDto status;
    private AccessGroupDto accessGroup;
    private String profilePictureName;
    private String address;
}
