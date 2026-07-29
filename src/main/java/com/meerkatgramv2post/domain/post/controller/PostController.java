package com.meerkatgramv2post.domain.post.controller;

import com.meerkatgramv2post.domain.post.request.PostCreateRequestDTO;
import com.meerkatgramv2post.domain.post.request.PostIndexRequestDTO;
import com.meerkatgramv2post.domain.post.response.PostIndexResponseDTO;
import com.meerkatgramv2post.domain.post.response.PostResponseDTO;
import com.meerkatgramv2post.domain.post.service.PostService;
import com.meerkatgramv2post.global.response.GlobalResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<GlobalResponse<PostResponseDTO>> postCreate(
        @Valid @RequestBody PostCreateRequestDTO requestDTO,
        Authentication authentication
    ) {
        return GlobalResponse.success(postService.postCreate(requestDTO,authentication));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<PostResponseDTO>> show(
        @Parameter(description = "게시글 번호", example = "1") @Min(value = 1, message = "1이상 숫자만 허용합니다.") @PathVariable long id
    ) {
        return GlobalResponse.success(postService.show(id));
    }
    @PreAuthorize("hasAllRoles( 'SUPER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<GlobalResponse<Void>> delete(
        @Min(value = 1, message = "1이상 숫자만 허용합니다.") @PathVariable long id,
        Authentication authentication
    ) {
        long userId = Long.parseLong(authentication.getName());
        postService.delete(id, userId);
        return GlobalResponse.success();
    }
}
