package com.website.customer.profile.model;

import com.website.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerProfileDetailModel extends ModelBase {
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String address;
    private String profilePictureName;
    private String dateOfBirth;
}
