package com.website.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerModel  extends ModelBase{
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String mobileNumber;
    private String image;
}
