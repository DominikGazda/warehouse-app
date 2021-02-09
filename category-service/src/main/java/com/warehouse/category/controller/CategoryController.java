package com.warehouse.category.controller;

import com.warehouse.category.entity.Category;
import com.warehouse.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/category/")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getCategories(){
        return categoryService.getCategories();
    }

    @PostMapping("")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        Category savedCategory = categoryService.saveCategory(category);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCategory.getCategoryId())
                .toUri();
        return ResponseEntity.created(location).body(category);
    }

    @GetMapping("{id}")
    public Category getCategoryById(@PathVariable Long id){
        return categoryService.getCategoryById(id);
    }
}
