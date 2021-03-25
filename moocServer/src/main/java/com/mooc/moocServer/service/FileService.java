package com.mooc.moocServer.service;

import com.mooc.moocServer.dto.FileDto;
import com.mooc.moocServer.entity.UploadFile;
import com.mooc.moocServer.exception.FileStorageException;
import com.mooc.moocServer.exception.MyFileNotFoundException;
import com.mooc.moocServer.mapper.UploadFileMapper;
import com.mooc.moocServer.property.FileStorageProperties;
import com.mooc.moocServer.repository.UploadFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

@Service
@Transactional(readOnly = true)
public class FileService {

    private final Path fileStorageLocation;
    private final UploadFileRepository uploadFileRepository;

    @Autowired
    public FileService(FileStorageProperties fileStorageProperties, UploadFileRepository uploadFileRepository){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.uploadFileRepository = uploadFileRepository;

        try{
            Files.createDirectories(this.fileStorageLocation);
        }catch (Exception e){
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Transactional
    public FileDto.UploadFileResponse store(MultipartFile file){
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try{
            // check invalid characters
            if(fileName.contains("..")){
                throw new FileStorageException("File name contains invalid path sequence " + fileName);
            }

            // Copy file to the target location ( Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // save to jpa
            UploadFile uploadFile = UploadFile.createUploadFile(fileName, file.getSize(), file.getContentType());
            uploadFileRepository.save(uploadFile);

            return UploadFileMapper.uploadFileToUploadFileResponse(uploadFile);

        }catch(IOException e){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again.", e);
        }
    }

    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        }catch (MalformedURLException e){
            throw new MyFileNotFoundException("File not found" + fileName);
        }
    }
}
