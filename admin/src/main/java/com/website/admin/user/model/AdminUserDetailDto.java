package com.website.admin.user.model;

import com.website.admin.accessgroup.model.request.FetchAccessGroupDetail;
import com.website.common.dto.ModelBase;
import com.website.common.dto.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminUserDetailDto extends ModelBase {
    private String name;
    private String username;
    private String email;
    private StatusDto status;
    private String mobileNumber;
    private String address;
    private String profilePictureName;
    private FetchAccessGroupDetail accessGroup;
}
