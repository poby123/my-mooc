package com.mooc.moocServer.controller;

import com.mooc.moocServer.dto.FileDto;
import com.mooc.moocServer.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FileController {

    private final FileService fileService;

    @PostMapping("/file/upload")
    public FileDto.UploadFileResponse upload(@RequestParam("file") MultipartFile file) {
        return fileService.store(file);
    }

    @PostMapping("/file/uploads")
    public List<FileDto.UploadFileResponse> uploads(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files).stream().map(file -> upload(file)).collect(Collectors.toList());
    }

    @GetMapping("/file/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id, HttpServletRequest request) {

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
