/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.models.entity.Product;
import com.pevi.core.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class ProductService {
    
    @Autowired private ProductRepository prep; 
    
    public List<Product>getProducts(){
        return prep.findAll();
    }
    
}
