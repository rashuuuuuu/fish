package com.website.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class CustomerDto extends ModelBase{
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private String username;
    private LocalDate dateOfBirth;
    private boolean isActive;
    private String address;
    private StatusDto status;
    private Date registeredDate;
    private String profilePictureName;
}
