package com.website.admin.comment.model;

import com.website.common.dto.ModelBase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyComment extends ModelBase {

    @NotBlank(message = "Message cannot be blank")
    private String message;

    @NotNull
    private CommentModel commentModel;
}
