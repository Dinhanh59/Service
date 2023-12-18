package com.example.phatdang.service;

import com.example.phatdang.models.entity.Invoice;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Service
public class FileuploadImpl implements FileuploadService {
    @Value( "${app.upload.dir:${user.home}}")
    public String uploadDir;


    @Override
    public boolean uploadFile(MultipartFile file) {
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    @Override
    public   List<Invoice> getDataFromExcel(InputStream inputStream)  {
        List<Invoice> invoices = new ArrayList<>();
        try{
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("ex");
            DataFormatter formatter = new DataFormatter(Locale.US);
            int rowIndex = 0;
            for (Row row : sheet){
                if(rowIndex == 0){
                    rowIndex ++;
                    continue;
                }
                Iterator<Cell> cellIterator =row.iterator();
                int cellIndex =0;
                Invoice invoice= new Invoice();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 1 :
                            String name=cell.getStringCellValue();
                            invoice.setName(name);
                        break;
                        case 2 :
                            invoice.setAmount((int) cell.getNumericCellValue());
                        break;
                        case 3 :
                            try{
                                double y = cell.getNumericCellValue();
                                String id = String.valueOf(y);
                                invoice.setNumber(id);
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        case 4: try{
                            LocalDateTime x=cell.getLocalDateTimeCellValue();
                            Date date = Date.from(x.atZone(ZoneId.systemDefault()).toInstant());
                            invoice.setDateStart(date);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        break;
                        default : break;
                    }
                    cellIndex++;

                }
                invoices.add(invoice);
            }
        } catch ( IOException e){
           e.printStackTrace();
        }

        return invoices;
    }

}
