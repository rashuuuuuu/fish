package com.website.admin.comment.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewCommentRequest extends ModelBase {

    @NotBlank(message = "Code cannot be blank")
    private String code;
}
