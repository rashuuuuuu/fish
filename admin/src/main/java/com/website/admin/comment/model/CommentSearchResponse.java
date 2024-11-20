package com.website.admin.comment.model;

import com.website.common.dto.CustomerModel;
import com.website.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CommentSearchResponse extends ModelBase {
    private String subject;
    private String description;
    private String code;
    private boolean isReplied;
    private CustomerModel customer;
    private Date recordedDate;
}
