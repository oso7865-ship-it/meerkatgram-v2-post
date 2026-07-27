package com.meerkatgramv2post.domain.controller;

import com.meerkatgramv2post.domain.request.PostIndexRequestDTO;
import com.meerkatgramv2post.domain.response.PostIndexResponseDTO;
import com.meerkatgramv2post.domain.service.PostService;
import com.meerkatgramv2post.global.response.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "게시글 API", description = "게시글 담당")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/posts")
public class PostController {


    private final PostService postService;

    @GetMapping()
    public ResponseEntity<GlobalResponse<PostIndexResponseDTO>> index(
        @Valid PostIndexRequestDTO requestDTO
        ) {
        return GlobalResponse.success(postService.index(requestDTO));
    }
}
