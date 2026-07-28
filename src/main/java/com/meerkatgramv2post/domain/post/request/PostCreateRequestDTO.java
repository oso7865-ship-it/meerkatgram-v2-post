package com.meerkatgramv2post.domain.post.request;

import jakarta.validation.constraints.NotBlank;

public record PostCreateRequestDTO(
    @NotBlank(message = "필수 항목입니다.")
    String content,
    @NotBlank(message = "필수 항목입니다.")
    String image
) {
}
