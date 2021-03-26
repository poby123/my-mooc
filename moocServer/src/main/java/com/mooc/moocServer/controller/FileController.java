package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.FileDto;
import com.mooc.moocServer.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @GetMapping("/file/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) {

        FileDto.DownloadFileResponse response = fileService.getFile(id);
        Resource resource = response.getResource();

        // Determine file content's type
        String contentType = response.getFileType();

        // Fallback to the default content type if type could not be determined.
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.getOriginalFileName() + "\"")
                .body(resource);
    }

}
