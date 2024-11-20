package com.website.admin.emailtemplate.model.dto;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailTemplateDto extends ModelBase {
    @NotBlank(message="Template name is required")
    private String name;
    @NotBlank(message="Email content is required")
    private String template;
}
