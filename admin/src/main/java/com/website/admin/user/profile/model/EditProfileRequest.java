package com.website.admin.user.profile.model;

import com.website.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditProfileRequest extends ModelBase {
    private String name;
    private String mobileNumber;
    private String address;
}
