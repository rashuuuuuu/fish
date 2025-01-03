package com.website.admin.accessgroup.model.request;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteAccessGroupRequest extends ModelBase {

    @NotBlank(message = "Name cannot be blank")
    private String name;
}
