package com.website.admin.comment.model;

import com.website.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CommentResponseDto extends ModelBase {

    private String message;
    private String code;
    private CommentModel commentModel;
    private Date responseDate;

}
