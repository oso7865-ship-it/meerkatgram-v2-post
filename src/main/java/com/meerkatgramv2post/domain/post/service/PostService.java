package com.meerkatgramv2post.domain.post.service;

import com.meerkatgramv2post.domain.post.entity.Post;
import com.meerkatgramv2post.domain.post.repository.PostQueryDSLRepository;
import com.meerkatgramv2post.domain.post.repository.PostRepository;
import com.meerkatgramv2post.domain.post.request.PostCreateRequestDTO;
import com.meerkatgramv2post.domain.post.request.PostIndexRequestDTO;
import com.meerkatgramv2post.domain.post.response.PostIndexResponseDTO;
import com.meerkatgramv2post.domain.post.response.PostResponseDTO;
import com.meerkatgramv2post.global.error.custom.InvalidPostCreateException;
import com.meerkatgramv2post.global.error.custom.ResourceAuthMismatchException;
import com.meerkatgramv2post.global.error.custom.ResourceNotFoundException;
import com.meerkatgramv2post.global.minio.MinioManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostQueryDSLRepository postQueryDSLRepository;
    private final PostRepository postRepository;
    private final MinioManager minioManager;

    public PostIndexResponseDTO index(PostIndexRequestDTO requestDTO) {
        long offset = (requestDTO.page() - 1) * requestDTO.limit();

        // 특정 페이지 게시글 조회
        List<Post> result = postQueryDSLRepository.pagination(offset, requestDTO.limit());

        // 토탈 및 마지막 페이지 여부 흭득
        long total = postRepository.count();

        boolean isLastPage = offset + requestDTO.limit() >= total;

        return PostIndexResponseDTO.from(total, isLastPage, result);
    }

    public PostResponseDTO postCreate(PostCreateRequestDTO requestDTO, Authentication authentication) {


        if (requestDTO.content() == null || requestDTO.content().isBlank()) {
            throw new InvalidPostCreateException("게시글 내용을 입력해주세요.");
        }

        if (requestDTO.image() == null || requestDTO.image().isEmpty()) {
            throw new InvalidPostCreateException("게시글 이미지를 첨부해주세요.");
        }

        Long userId = Long.parseLong(authentication.getName());

        Post post = new Post();
        post.setImage(requestDTO.image());
        post.setContent(requestDTO.content());
        post.setUserId(userId);
        postRepository.save(post);

        return PostResponseDTO.from(post);
    }

    public PostResponseDTO show(long id) {

        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("이미 삭제된 게시글" + id));

        return PostResponseDTO.from(post);
    }

    public void delete (long id, long userId) {

        Post post = postRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("이미 삭제된 게시글" + id));

        if(!post.getUserId().equals(userId)) {
            throw new ResourceAuthMismatchException("게시글 삭제 실패: 작성자 다름");
        }
        postRepository.delete(post);

        minioManager.removeObject(post.getImage());
    }
}
