package com.example.phatdang.models.converter;
import com.example.phatdang.models.dto.InvoiceDto;
import com.example.phatdang.models.entity.Invoice;
public class InvoiceConverter {
    public static InvoiceDto convertDto(Invoice unit) {
        InvoiceDto output = new InvoiceDto();
        if (unit.getId() != null) output.setId(unit.getId());
        if (unit.getName() != null) output.setName(unit.getName());
        if (unit.getNumber() != null) output.setNumber(unit.getNumber());
        return output;
    }

    public static InvoiceDto convertDto(Invoice unit, InvoiceDto output) {
        if (unit.getId() != null) output.setId(unit.getId());
        if (unit.getName() != null) output.setName(unit.getName());
        if (unit.getNumber() != null) output.setNumber(unit.getNumber());
        return output;
    }

    public static Invoice convertEntity(InvoiceDto unit) {
        Invoice output = new Invoice();
        if (unit.getId() != null) output.setId(unit.getId());
        if (unit.getName() != null) output.setName(unit.getName());
        if (unit.getAmount() != null) output.setAmount(unit.getAmount());
        if (unit.getNumber() != null) output.setNumber(unit.getNumber());
        return output;
    }

    public static Invoice convertEntity(InvoiceDto unit, Invoice output) {
        if (unit.getId() != null) output.setId(unit.getId());
        if (unit.getName() != null) output.setName(unit.getName());
        if (unit.getAmount() != null) output.setAmount(unit.getAmount());
        if (unit.getNumber() != null) output.setNumber(unit.getNumber());
        return output;
    }

    public static Invoice convertEntityIgnoreId(Invoice unit) {
        Invoice output = new Invoice();
        if (unit.getId() != null) output.setId(unit.getId());
        if (unit.getName() != null) output.setName(unit.getName());
        if (unit.getNumber() != null) output.setNumber(unit.getNumber());
        return output;
    }
}
