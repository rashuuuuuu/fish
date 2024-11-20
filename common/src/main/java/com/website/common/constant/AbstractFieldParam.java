package com.website.common.constant;
import com.website.common.dto.ModelBase;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractFieldParam extends ModelBase {

    protected String fieldKey;

    protected String fieldOperator;
}
