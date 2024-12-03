package com.ipt_architecture_course.microservices.content_upload_service.controller;

import com.ipt_architecture_course.microservices.content_upload_service.service.ContentProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            summary = "Upload de imagem",
            description = "Realiza o upload de uma imagem e processa o conteúdo enviado.",
            requestBody = @RequestBody(
                    content = @Content(
                            mediaType = "multipart/form-data",
                            schema = @Schema(
                                    type = "object",
                                    example = "{ \"file\": \"<arquivo-binário>\", \"type\": \"image\" }",
                                    requiredProperties = { "file", "type" }
                            )
                    )
            ),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Upload processado com sucesso"),
                    @ApiResponse(responseCode = "400", description = "Arquivo enviado não é válido"),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor durante o processamento")
            }
    )
    public ResponseEntity<String> uploadContent(
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type) {

        // Verificar se o arquivo é uma imagem
        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body("O arquivo enviado não é uma imagem válida.");
        }

        try {
            // Processar o arquivo utilizando o serviço
            contentProcessingService.processUpload(file, type);
            return ResponseEntity.ok("Upload de imagem processado com sucesso!");
        } catch (Exception e) {
            // Tratar erros durante o processamento
            return ResponseEntity.status(500).body("Erro ao processar o upload: " + e.getMessage());
        }
    }
}
