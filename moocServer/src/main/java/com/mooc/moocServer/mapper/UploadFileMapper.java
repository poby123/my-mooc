package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.FileDto;
import com.mooc.moocServer.entity.UploadFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class UploadFileMapper {

    public static FileDto.UploadFileResponse uploadFileToUploadFileResponse(UploadFile uploadFile) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download/")
                .path(uploadFile.getFileName())
                .toUriString();

        return new FileDto.UploadFileResponse(uploadFile.getFileName(), fileDownloadUri, uploadFile.getMimeType(), uploadFile.getSize());
    }
}
