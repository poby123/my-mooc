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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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

    @GetMapping("/file/download/{fileName:.+}")
    public ResponseEntity<Resource> download(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as resource
        Resource resource = fileService.loadFileAsResource(fileName);

        // Determine file content's type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined.
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
