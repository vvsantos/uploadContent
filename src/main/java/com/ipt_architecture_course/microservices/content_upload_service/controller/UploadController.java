package com.ipt_architecture_course.microservices.content_upload_service.controller;


import com.ipt_architecture_course.microservices.content_upload_service.service.ContentProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

    @Autowired
    private ContentProcessingService contentProcessingService;

    @PostMapping
    public ResponseEntity<String> uploadContent(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type) {
        contentProcessingService.processUpload(file, type);
        return ResponseEntity.ok("Upload successfully processed!");
    }
}
