package com.meerkatgramv2post.domain.post.response;

import com.meerkatgramv2post.domain.post.entity.Post;

import java.time.LocalDateTime;

public record PostResponseDTO(
    Long id,
    Long userId,
    String content,
    String image,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) {
    public static PostResponseDTO from(Post post) {
        return new PostResponseDTO(
            post.getId(),
            post.getUserId(),
            post.getContent(),
            post.getImage(),
            post.getCreatedAt(),
            post.getUpdatedAt(),
            post.getDeletedAt()
        );
    }
}
