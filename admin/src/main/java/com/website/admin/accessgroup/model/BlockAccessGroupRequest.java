package com.website.admin.accessgroup.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BlockAccessGroupRequest extends ModelBase {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Remarks cannot be blank")
    private String remarks;

}
