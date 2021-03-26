package com.mooc.moocServer.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_file_id")
    private Long fileId;

    @Column(name = "upload_file_original_name", nullable = false)
    private String originalFileName;

    @Column(name = "upload_file_name", nullable = false)
    private String fileName;

    @Column(name = "upload_file_path", nullable = false)
    private String filePath;

    @Column(name = "upload_file_size")
    private Long size;

    @Column(name = "upload_file_mime_type", nullable = false)
    private String mimeType;

    @CreationTimestamp
    @Column(name = "upload_file_reg_date")
    private Date regDate;

    // == 생성 메서드 == //
    public static UploadFile createUploadFile(String originalFileName, String fileName, String filePath, long size, String mimeType) {
        UploadFile uploadFile = new UploadFile();

        uploadFile.originalFileName = originalFileName;
        uploadFile.fileName = fileName;
        uploadFile.filePath = filePath;
        uploadFile.size = size;
        uploadFile.mimeType = mimeType;

        return uploadFile;
    }

}
