package com.website.customer.profile.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class EditProfileRequest extends ModelBase {

    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    private String username;
    @NotBlank(message = "Mobile number is required")
    @Size(min = 10, max = 10, message = "Mobile number is invalid")
    private String mobileNumber;
    @NotBlank(message = "Address is required")
    private String address;
    private String profilePictureName;
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

}
