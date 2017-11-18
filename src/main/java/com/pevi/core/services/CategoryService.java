/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.services;

import com.pevi.core.models.entity.Category;
import com.pevi.core.repository.CategoryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnson3yo
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository crepo;

    public void saveCategory(Category cat) {
        crepo.save(cat);
    }

    public Category modifyCategory(Category cat) {
        return crepo.save(cat);
    }

    public List<Category> getCategories() {
        return (List<Category>) crepo.findAll();
    }

}
