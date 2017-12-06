/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pevi.core.constants.PeviException;
import com.pevi.core.models.dto.ProductFilter;
import com.pevi.core.models.entity.Product;
import com.pevi.core.services.ProductService;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author johnson3yo
 */
@RequestMapping("api/product")
@RestController
public class ProductController {

    @Autowired
    private ProductService pserve;

    @GetMapping("getProducts/{pageNo}")
    public ResponseEntity getProducts(@PathVariable("pageNo") String pageNo) {
        List<Product> pdts = pserve.getProducts(pageNo);
        return new ResponseEntity<List<Product>>(pdts, HttpStatus.OK);
    }

    @PostMapping("saveProduct")
    public ResponseEntity saveProduct(@RequestBody Product p) {
        pserve.saveProduct(p);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("saveProductv2")
    public ResponseEntity saveProductWithPhoto(@RequestPart(value = "product") String product,
            @RequestPart(value = "uploadFile") MultipartFile file) throws IOException {
        ObjectMapper m = new ObjectMapper();
        Product p = m.readValue(product, Product.class);
        pserve.saveProductWithImg(p, file);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("modifyProduct")
    public ResponseEntity modifyProduct(@RequestBody Product p) {
        Product pr = pserve.modifyProduct(p);
        return new ResponseEntity<Product>(pr, HttpStatus.OK);
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") String id) {
        pserve.deleteProduct(Integer.parseInt(id));
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("getProduct/{id}")
    public ResponseEntity getProduct(@PathVariable("id") String id) {
        Product p = pserve.getProduct(Integer.parseInt(id));
        return new ResponseEntity<Product>(p, HttpStatus.OK);
    }

    @GetMapping("getProductByRange/{from}/{to}")
    public ResponseEntity getProductByRange(@PathVariable("from") String from,
            @PathVariable("to") String to) {
        List<Product> pl = pserve.getProductByRange(Integer.parseInt(from), Integer.parseInt(to));
        return new ResponseEntity<List<Product>>(pl, HttpStatus.OK);
    }

    @PostMapping("getProductsV2")
    public ResponseEntity getProductsV2(@RequestBody ProductFilter pf) {
        List<Product> rp = pserve.retriveProducts(pf);
        return new ResponseEntity<List<Product>>(rp, HttpStatus.OK);
    }

    @GetMapping("getProductImage/{productId}")
    public ResponseEntity getProductImage(@PathVariable("productId") String productId, HttpServletResponse res) {

        OutputStream os = null;
        try {
            Product p = pserve.getProduct(Integer.parseInt(productId));
            os = res.getOutputStream();
            InputStream fis = new FileInputStream(new File(p.getImageUrl()));
            byte[] img = IOUtils.toByteArray(fis);
            os.write(img);
            os.close();
            return new ResponseEntity(HttpStatus.OK);

        } catch (IOException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
                Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @GetMapping("toggleState/{productId}")
    public ResponseEntity toggleState(@PathVariable("productId") String productId) {

        try {
            boolean state = this.pserve.toggleState(Integer.parseInt(productId));
            return new ResponseEntity<Boolean>(state, HttpStatus.OK);
        } catch (PeviException ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.OK);
        }

    }

}
