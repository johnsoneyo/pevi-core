/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.repository;

import com.pevi.core.models.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnson3yo
 */
@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, JpaSpecificationExecutor  {

    @Query(value = "select * from product limit ?1,100", nativeQuery = true)
    List<Product> getAllProducts(int offset);
    @Query(value = "select * from product where id = ?1", nativeQuery = true)
    Product findById(int id);

    @Query(value="select * from product where price between ?1 and ?2",nativeQuery=true)
    public List<Product> findByRange(int parseInt,int parseInt0);

}
