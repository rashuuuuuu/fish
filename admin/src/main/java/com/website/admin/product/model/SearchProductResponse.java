package com.website.admin.product.model;

import com.website.common.dto.ModelBase;
import com.website.common.dto.StatusDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductResponse extends ModelBase {

    private String name;
    private String description;
    private String code;
    private StatusDto status;
}
