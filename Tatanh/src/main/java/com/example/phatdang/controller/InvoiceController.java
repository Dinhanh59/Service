package com.example.phatdang.controller;
import com.example.phatdang.models.entity.Invoice;
import com.example.phatdang.service.FileuploadService;
import com.example.phatdang.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/v1/ex")
public class InvoiceController {
    @Autowired
    private FileuploadService fileuploadService;
    @Autowired
    private InvoiceService invoiceService;
    @PostMapping("/upload")
    public  ResponseEntity<?> uploadCustomersData(@RequestParam("file") MultipartFile file){
        this.invoiceService.saveInvoice(file);
        return ResponseEntity
                .ok(Map.of("message","invoice data upload and saved to database successfully"));
    }
    @GetMapping
    public ResponseEntity<List<Invoice>> getInvoices(){
        return new ResponseEntity<>(invoiceService.getInvoice(), HttpStatus.FOUND);
    }
}
