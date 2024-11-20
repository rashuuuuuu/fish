package com.website.common.dto;

import com.website.entity.StatusProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductDto extends ModelBase{
    private String name;
    private String details;
    private Date recordedDate;
    private String code;
    private StatusProduct statusProduct;
}
