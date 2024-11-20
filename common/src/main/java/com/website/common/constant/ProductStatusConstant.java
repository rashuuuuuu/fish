package com.website.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatusConstant {
    DELETED("DELETED", "DELETED"),
    BLOCKED("BLOCKED", "BLOCKED"),
    AVAILABLE("AVAILABLE", "AVAILABLE"),
    OUTOFSTOCK("OUTOFSTOCK", "OUTOFSTOCK");

    private final String name;
    private final String description;
}
