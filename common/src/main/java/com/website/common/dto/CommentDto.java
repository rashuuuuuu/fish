package com.website.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentDto extends ModelBase{
    private String subject;
    private String description;
    private String code;
    private boolean isReplied;
    private CustomerModel customer;
    private Date recordedDate;
}
