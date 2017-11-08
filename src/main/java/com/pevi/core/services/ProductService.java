/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.constants.Constants;
import com.pevi.core.models.dto.ProductSearchResponse;
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

    @Autowired
    private ProductRepository prep;

    public List<Product> getProducts(String pageNo) {

        int res = (Integer.parseInt(pageNo) - 1);
        int offset = res != 0 ? res * (Constants.resultCount) + 1 : 0;
        return prep.getAllProducts(offset);

    }

    private ProductSearchResponse paginatorEngine(String pageNo) {
       
        return null;
    }

}
