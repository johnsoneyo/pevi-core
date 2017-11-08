/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.controllers;

import com.pevi.core.models.entity.Product;
import com.pevi.core.services.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@RequestMapping("api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService pserve;

    @GetMapping("getProducts")
    public ResponseEntity getProducts() {
        List<Product> pdts = pserve.getProducts();
        return new ResponseEntity<List<Product>>(pdts, HttpStatus.OK);
    }

}
