package com.website.common.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentModel {
    @NotBlank(message = "Subject field is required")
    private String subject;
    @NotBlank(message = "Description field is required")
    private String description;
}
