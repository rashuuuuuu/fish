package com.website.common.dto;

import com.website.common.constant.AbstractFieldParam;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchFieldParam extends AbstractFieldParam {

    private String fieldCondition;
    private String fieldValue;

    public SearchFieldParam() {
        this.fieldKey = null;
        this.fieldValue = null;
        this.fieldCondition = null;
        this.fieldOperator = null;
    }

}
