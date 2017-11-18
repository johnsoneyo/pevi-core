/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.repository;

import com.pevi.core.models.entity.Category;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author johnson3yo
 */
public interface CategoryRepository extends CrudRepository<Category, Integer> {
    
}
