package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.FileDto;
import com.mooc.moocServer.entity.UploadFile;
import com.mooc.moocServer.exception.FileStorageException;
import com.mooc.moocServer.exception.MyFileNotFoundException;
import com.mooc.moocServer.mapper.UploadFileMapper;
import com.mooc.moocServer.property.FileStorageProperties;
import com.mooc.moocServer.repository.UploadFileRepository;
import com.mooc.moocServer.util.MD5Generator;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Slf4j
public class FileService {

    private final Path fileStorageLocation;
    private final UploadFileRepository uploadFileRepository;

    @Autowired
    public FileService(FileStorageProperties fileStorageProperties, UploadFileRepository uploadFileRepository) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.uploadFileRepository = uploadFileRepository;

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Transactional
    public FileDto.UploadFileResponse store(MultipartFile file) {
        try {
            // original name
            String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
            String fileType = file.getContentType().toString();

            // throw exception when the original file name contains invalid characters
            if (originalFilename.contains("..")) {
                throw new FileStorageException("File name contains invalid path sequence " + originalFilename);
            }

            // hashing name with uuid
            UUID uuid = UUID.randomUUID();
            String fileName = new MD5Generator(uuid.toString() + originalFilename).toString();
            log.info("Hashed file name : " + fileName);

            // stored location
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            log.info("String targetLocation : " + targetLocation.toString());

            // copy to location
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // db save
            UploadFile uploadFile = UploadFile.createUploadFile(originalFilename, fileName, targetLocation.toString(), file.getSize(), fileType);
            uploadFileRepository.save(uploadFile);

            return UploadFileMapper.uploadFileToUploadFileResponse(uploadFile);

        } catch (Exception e) {
            throw new FileStorageException("Could not store file. Please try again.", e);
        }
    }

    public FileDto.DownloadFileResponse getFile(@NonNull Long id) {

        try {
            // get file information from db
            Optional<UploadFile> fileOptional = uploadFileRepository.findById(id);
            UploadFile uploadFile = fileOptional.orElseThrow(() -> new MyFileNotFoundException("File not found"));

            // mapping entity to dto
            FileDto.DownloadFileResponse downloadFileResponse = UploadFileMapper.uploadFileToDownloadResponse(uploadFile);

            // get file resource
            Path path = Paths.get(uploadFile.getFilePath());
            Resource resource = new InputStreamResource((Files.newInputStream(path)));

            // check resource is exist
            if (resource.exists()) {
                downloadFileResponse.setResource(resource);
                return downloadFileResponse;
            } else {
                throw new MyFileNotFoundException("File not found");
            }

        } catch (Exception e) {
            throw new FileStorageException("Could not load file.", e);
        }
    }
}
