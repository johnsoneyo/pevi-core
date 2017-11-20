/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pevi.core.controllers;

import com.pevi.core.models.entity.Category;
import com.pevi.core.services.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author johnson3yo
 */
@RequestMapping("api/category")
@RestController
public class CategoryController {

    @Autowired
    private CategoryService cats;

    @PostMapping("saveCategory")
    public ResponseEntity saveCategory(@RequestBody Category cat) {
        cats.saveCategory(cat);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("modifyCategory")
    public ResponseEntity modifyCategory(@RequestBody Category cat) {
        Category c = cats.modifyCategory(cat);
        return new ResponseEntity<Category>(c, HttpStatus.OK);
    }

    @GetMapping("getCategories")
    public ResponseEntity getCategories() {
        List<Category> clist = cats.getCategories();
        return new ResponseEntity<List<Category>>(clist, HttpStatus.OK);
    }
    
     @DeleteMapping("deleteCategory/{catId}/")
    public ResponseEntity deleteCategory(@PathVariable("catId")String catId) {
        this.cats.deleteCategory(catId);
        return new ResponseEntity(HttpStatus.OK);
    }
    

}
