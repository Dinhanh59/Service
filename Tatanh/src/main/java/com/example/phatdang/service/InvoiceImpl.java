package com.example.phatdang.service;

import com.example.phatdang.models.entity.Invoice;
import com.example.phatdang.repository.InvoiceRepository;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
@Service
public class InvoiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository repository;
    Workbook work;
    @Autowired
    private FileuploadService fileupload;

    @Override
    public void saveInvoice(MultipartFile file) {
        if(fileupload.uploadFile(file)){
            try{
                List<Invoice> invoices = fileupload.getDataFromExcel(file.getInputStream());
                for (Invoice x: invoices
                     ) {
                    try{
                        repository.save(x);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(" the file í not a valid excel file");
            }
        }
    }
    @Override
    public  List<Invoice> getInvoice(){
        return repository.findAll();
    }


}
