package com.website.common.dto.request;

import com.website.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentResponseDto extends ModelBase {
    private String message;
    private String code;
    private Date responseDate;
}
