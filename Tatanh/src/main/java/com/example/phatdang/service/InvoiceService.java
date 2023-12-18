package com.example.phatdang.service;

import com.example.phatdang.models.entity.Invoice;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InvoiceService {
    public  void saveInvoice(MultipartFile file);
    public  List<Invoice> getInvoice();
}
