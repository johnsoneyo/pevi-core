/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.constants.Constants;
import com.pevi.core.constants.PeviException;
import com.pevi.core.models.dto.ProductFilter;
import com.pevi.core.models.entity.Product;
import com.pevi.core.repository.ProductDao;
import com.pevi.core.repository.ProductRepository;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author johnson3yo
 */
@Service
public class ProductService {

    @PersistenceContext
    EntityManager em;

    @Value("${image.path}")
    private String imgPath;

    @Autowired
    private ProductRepository prep;
    @Autowired
    private ProductDao pdao;

    public List<Product> getProducts(String pageNo) {
        int res = (Integer.parseInt(pageNo) - 1);
        int offset = res != 0 ? res * (Constants.resultCount) + 1 : 0;
        return prep.getAllProducts(offset);
    }

    public void saveProduct(Product p) {
        prep.save(p);
    }

    public void saveProductWithImg(Product p, MultipartFile file) throws IOException {
        File f = new File(imgPath + file.getOriginalFilename());
        file.transferTo(f);
        p.setImageUrl(f.getAbsolutePath());
        prep.save(p);
    }

    public Product modifyProduct(Product p) {
        return prep.save(p);
    }

    public void deleteProduct(int parseInt) {
        prep.delete(parseInt);
    }

    public Product getProduct(int parseInt) {
        return prep.findById(parseInt);
    }

    public List<Product> getProductByRange(int parseInt, int parseInt0) {
        return prep.findByRange(parseInt, parseInt0);
    }

    public List<Product> retriveProducts(ProductFilter pf) {
        return pdao.retrieveProducts(pf);
    }

    public boolean toggleState(int parseInt) throws PeviException {

        Product findOne = prep.findOne(parseInt);
        if (findOne == null) {
            throw new PeviException(String.format("product not id %d found ", parseInt));
        }
        if (findOne.isActive()) {
            findOne.setActive(Boolean.FALSE);
        } else {
            findOne.setActive(Boolean.TRUE);
        }
        Product save = prep.save(findOne);
        return save.isActive();

    }

}
