package com.project.minio;

import org.springframework.context.annotation.Configuration;

import io.minio.MinioClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@Configuration
public class MinioConfig {
    @Value("${minio.access.name}")
    private String accessKey;
    @Value("${minio.access.secret}")
    private String accessSecret;
    @Value("${minio.url}")
    private String minioUrl;
    
    @Bean
    public MinioClient generateMinioClient() {
        try {
            MinioClient client = new MinioClient(minioUrl, accessKey, accessSecret);
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
