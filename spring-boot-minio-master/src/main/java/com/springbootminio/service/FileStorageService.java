package com.springbootminio.service;

import com.springbootminio.payload.FileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    FileResponse addFile(MultipartFile multipartFile);

    void deleteFile(String fileName);

    FileResponse getFile(String fileName);

    FileResponse getFileDetails(String fileName);
}
