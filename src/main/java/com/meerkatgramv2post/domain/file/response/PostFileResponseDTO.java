package com.meerkatgramv2post.domain.file.response;

public record PostFileResponseDTO(
    String fileUri
) {
    public static PostFileResponseDTO from(String fileUri) {
        return new PostFileResponseDTO(fileUri);
    }
}
