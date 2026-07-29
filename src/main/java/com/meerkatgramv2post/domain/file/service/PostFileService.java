package com.meerkatgramv2post.domain.file.service;

import com.meerkatgramv2post.domain.file.response.PostFileResponseDTO;
import com.meerkatgramv2post.global.minio.MinioManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PostFileService {

    private final MinioManager minioManager;

    public PostFileResponseDTO postFile(MultipartFile file) {
        String objectKey = minioManager.generateObjectKey(file);

        minioManager.uploadFile(objectKey,file);

        return PostFileResponseDTO.from(minioManager.createMinioObjectUri(objectKey));
    }
}
