package com.ipt_architecture_course.microservices.content_upload_service.service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ContentProcessingService {

    public void processUpload(MultipartFile file, String type) {
        // Valida o tipo do arquivo
        if (!type.equals("image")) {
            throw new IllegalArgumentException("Unsupported file type: " + type);
        }

        // Simula processamento de imagem para carrossel
        System.out.println("Processing file: " + file.getOriginalFilename());
        System.out.println("Generating carousel...");
    }
}
