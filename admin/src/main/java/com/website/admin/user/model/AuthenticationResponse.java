package com.website.admin.user.model;

import com.website.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationResponse extends ModelBase {
    private String token;
}
