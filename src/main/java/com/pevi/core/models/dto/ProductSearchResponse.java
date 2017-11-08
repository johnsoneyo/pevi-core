/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.models.dto;

import com.pevi.core.models.entity.Product;
import java.util.List;

/**
 *
 * @author johnson3yo
 */
public class ProductSearchResponse {
    
    private List<Product>productsRetrieved;
    private int noOfPages;
   
    public ProductSearchResponse() {
    }

    
    public List<Product> getProductsRetrieved() {
        return productsRetrieved;
    }

    public void setProductsRetrieved(List<Product> productsRetrieved) {
        this.productsRetrieved = productsRetrieved;
    }

    public int getNoOfPages() {
        return noOfPages;
    }

    public void setNoOfPages(int noOfPages) {
        this.noOfPages = noOfPages;
    }

   
   
    
    
}
