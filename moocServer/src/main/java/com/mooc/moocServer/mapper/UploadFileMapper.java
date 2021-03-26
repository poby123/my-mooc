package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.FileDto;
import com.mooc.moocServer.entity.UploadFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UploadFileMapper {

    public static FileDto.UploadFileResponse uploadFileToUploadFileResponse(UploadFile uploadFile) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download/")
                .path(uploadFile.getFileId().toString())
                .toUriString();

        return new FileDto.UploadFileResponse(uploadFile.getOriginalFileName(), fileDownloadUri, uploadFile.getMimeType(), uploadFile.getSize());
    }

    public static FileDto.DownloadFileResponse uploadFileToDownloadResponse(UploadFile uploadFile) {
        return new FileDto.DownloadFileResponse(uploadFile.getOriginalFileName(), uploadFile.getMimeType(), uploadFile.getSize(), null);
    }
}
