package com.website.admin.emailtemplate.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewEmailTemplateRequest extends ModelBase {
    @NotBlank(message = "Template name is required")
    private String name;
}
