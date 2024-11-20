package com.website.admin.customer.model;

import com.website.common.dto.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SearchCustomerResponse extends ModelBase {
    private String fullName;
    private String email;
    private String username;
    private String mobileNumber;
    private String address;
    private StatusDto status;
    private Date registeredDate;
}
