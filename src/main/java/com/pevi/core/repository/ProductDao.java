/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.repository;

import com.pevi.core.models.dto.ProductFilter;
import com.pevi.core.models.entity.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public class ProductDao {
    @PersistenceContext private EntityManager em;
    
    public List<Product> retrieveProducts(ProductFilter pf) {
        StringBuilder sb = new StringBuilder();
        Query q = null;
        String sql = "select p from Product p";
        sb.append(sql);
        if (pf.getCategories() != null) {
            sb.append(" where p.categoryId in :categories");
            q = em.createQuery(sb.toString(), Product.class);
            q.setParameter("categories", pf.getCategories());
           

        }
        return q.getResultList();

    }
}
