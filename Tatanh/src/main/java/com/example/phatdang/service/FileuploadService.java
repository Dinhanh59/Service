package com.example.phatdang.service;

import com.example.phatdang.models.entity.Invoice;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

public interface FileuploadService {
    public boolean uploadFile(MultipartFile file);
    public  List<Invoice> getDataFromExcel(InputStream inputStream);
}
