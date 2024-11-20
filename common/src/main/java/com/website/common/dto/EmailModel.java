package com.website.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailModel {
    private String recipient;
    private String subject="fish";
    private String body = "Thank You for Siginig up!";
}
