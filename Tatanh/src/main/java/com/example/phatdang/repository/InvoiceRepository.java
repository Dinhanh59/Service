package com.example.phatdang.repository;

import com.example.phatdang.models.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,String> {

    List<Invoice> findByEmail(String email);
    Optional<Invoice>findByUserCodeAndIsActivatedTrue(String name, )
}
