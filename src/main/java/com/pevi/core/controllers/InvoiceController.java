/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.controllers;

import com.pevi.core.models.entity.Invoice;
import com.pevi.core.services.InvoiceService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@RequestMapping("api/invoices")
@RestController
@Api(value = "Food Order Services API", description = " API")
public class InvoiceController {

    @Autowired
    private InvoiceService invService;
    
    @GetMapping("getInvoices/{pageNo}")
    public ResponseEntity getInvoices(@PathVariable("pageNo") String pageNo) {
        List<Invoice> invcs = invService.getInvoices(pageNo);
        return new ResponseEntity<List<Invoice>>(invcs, HttpStatus.OK);
    }

}
