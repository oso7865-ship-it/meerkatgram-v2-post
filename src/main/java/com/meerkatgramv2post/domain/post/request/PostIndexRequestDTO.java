package com.meerkatgramv2post.domain.post.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

@Schema(description = "페이지네이션 RequestDTO")
public record PostIndexRequestDTO(
    @Schema(description = "페이지 번호", examples = "1", nullable = false, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @Min(value = 1, message = "1이상 숫자만 허용합니다.")
    Long page,
    @Schema(description = "출력 개수", examples = "1", nullable = false, requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 1, message = "리미트 입력해주세요")
    Long limit
) {
    public PostIndexRequestDTO(Long page, Long limit) {
        this.page = (page != null && page > 0) ? page : 1;
        this.limit = (limit != null && limit > 0) ? limit : 6;
    }
}
