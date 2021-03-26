package com.mooc.moocServer.mapper;

import com.mooc.moocServer.dto.FileDto;
import com.mooc.moocServer.entity.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UploadFileMapper {

    // Entity -> UploadFileResponse
    public FileDto.UploadFileResponse EntityToUploadFileResponse(UploadFile uploadFile) {
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/file/download/")
                .path(uploadFile.getFileId().toString())
                .toUriString();

        return new FileDto.UploadFileResponse(uploadFile.getOriginalFileName(), fileDownloadUri, uploadFile.getMimeType(), uploadFile.getSize());
    }

    // Entity -> DownloadFileResponse
    public FileDto.DownloadFileResponse EntityToDownloadResponse(UploadFile uploadFile) {
        return new FileDto.DownloadFileResponse(uploadFile.getOriginalFileName(), uploadFile.getMimeType(), uploadFile.getSize(), null);
    }

    public List<FileDto.UploadFileResponse> EntityListToUploadFileResponseList(List<UploadFile> uploadFileList){
        List<FileDto.UploadFileResponse> response = new ArrayList<>(uploadFileList.size());
        for(UploadFile file : uploadFileList){
            response.add(EntityToUploadFileResponse(file));
        }
        return response;
    }


}
