package com.website.admin.adminActivityLog.model;

import com.website.common.dto.ModelBase;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SearchAdminActivityLogResponse extends ModelBase {
    private AdminDto admin;
    private String action;
    private String object;
    private String objectIdentifier;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    private LocalDateTime activityDate;
}
