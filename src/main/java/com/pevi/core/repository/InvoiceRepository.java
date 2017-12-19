/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.repository;

import com.pevi.core.models.entity.Invoice;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Integer> {

    @Query(value = "select * from invoice limit ?1,100", nativeQuery = true)
    List<Invoice> getInvoices(int offset);

}
