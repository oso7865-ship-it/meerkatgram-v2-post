package com.meerkatgramv2post.domain.service;

import com.meerkatgramv2post.domain.entity.Post;
import com.meerkatgramv2post.domain.repository.PostQueryDSLRepository;
import com.meerkatgramv2post.domain.repository.PostRepository;
import com.meerkatgramv2post.domain.request.PostIndexRequestDTO;
import com.meerkatgramv2post.domain.response.PostIndexResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostQueryDSLRepository postQueryDSLRepository;
    private final PostRepository postRepository;

    public PostIndexResponseDTO index(PostIndexRequestDTO requestDTO) {
        long offset = (requestDTO.page() - 1) * requestDTO.limit();

        // 특정 페이지 게시글 조회
        List<Post> result = postQueryDSLRepository.pagination(offset, requestDTO.limit());

        // 토탈 및 마지막 페이지 여부 흭득
        long total = postRepository.count();

        boolean isLastPage = offset + requestDTO.limit() >= total;

        return PostIndexResponseDTO.from(total, isLastPage, result);
    }
}
