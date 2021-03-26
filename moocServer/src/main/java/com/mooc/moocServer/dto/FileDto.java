package com.mooc.moocServer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.core.io.Resource;

public class FileDto {

    @NoArgsConstructor
    @Getter
    @Setter
    public static class UploadFileResponse {
        private String originalFileName;
        private String fileDownloadUri;
        private String fileType;
        private long size;

        public UploadFileResponse(String originalFileName, String fileDownloadUri, String fileType, long size) {
            this.originalFileName = originalFileName;
            this.fileDownloadUri = fileDownloadUri;
            this.fileType = fileType;
            this.size = size;
        }
    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class DownloadFileResponse {
        private String originalFileName;
        private String fileType;
        private long size;
        private Resource resource;

        public DownloadFileResponse(String originalFileName,String fileType, long size, Resource resource) {
            this.originalFileName = originalFileName;
            this.fileType = fileType;
            this.size = size;
        }
    }
}
