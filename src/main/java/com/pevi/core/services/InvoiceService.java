/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.constants.Constants;
import com.pevi.core.models.entity.Invoice;
import com.pevi.core.repository.InvoiceRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository prep;

    public List<Invoice> getInvoices(String pageNo) {
        int res = (Integer.parseInt(pageNo) - 1);
        int offset = res != 0 ? res * (Constants.resultCount) + 1 : 0;
        return prep.getInvoices(offset);

    }

}
