package com.ipt_architecture_course.microservices.content_upload_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadRequest {
    private String fileName; // Nome do arquivo
    private String type;     // Tipo de conteúdo

    // Lombok gerará automaticamente os getters, setters, toString, equals e hashCode.
}
