package com.meerkatgramv2post.domain.repository;

import com.meerkatgramv2post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    long countByUserId(long userId);
}
