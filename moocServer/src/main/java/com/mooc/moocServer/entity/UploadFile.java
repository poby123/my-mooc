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
    private Long id;

    @Column(name = "upload_file_name")
    private String fileName;

    @Column(name = "upload_file_size")
    private Long size;

    @Column(name = "upload_file_mime_type")
    private String mimeType;

    @CreationTimestamp
    @Column(name = "upload_file_reg_date")
    private Date regDate;

    // == 생성 메서드 == //
    public static UploadFile createUploadFile(String fileName, long size, String mimeType) {
        UploadFile uploadFile = new UploadFile();

        uploadFile.fileName = fileName;
        uploadFile.size = size;
        uploadFile.mimeType = mimeType;

        return uploadFile;
    }

}
