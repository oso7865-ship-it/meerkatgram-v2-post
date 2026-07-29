package com.meerkatgramv2post.domain.file.controller;

import com.meerkatgramv2post.domain.file.response.PostFileResponseDTO;
import com.meerkatgramv2post.domain.file.service.PostFileService;
import com.meerkatgramv2post.global.response.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "포스트 파일 업로드", description = "포스트 파일 관련")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts/files")
public class PostFileController {

    private final PostFileService postFileService;

    @PostMapping("/images")
    public ResponseEntity<GlobalResponse<PostFileResponseDTO>> postFile(
        @ModelAttribute MultipartFile file
    ) {
        return GlobalResponse.success(postFileService.postFile(file));
    }

}
