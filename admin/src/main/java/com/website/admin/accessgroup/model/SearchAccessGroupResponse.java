package com.website.admin.accessgroup.model;

import com.website.common.dto.ModelBase;
import com.website.common.dto.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchAccessGroupResponse extends ModelBase {
    private String name;
    private String description;
    private StatusDto status;
}
