package com.warehouse.category.controller;

import com.warehouse.category.entity.Category;
import com.warehouse.category.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<Category> saveCategory(@Valid @RequestBody Category category, BindingResult errors){
        if(errors.hasErrors())
            categoryService.createErrorsMessage(errors);
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

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@Valid @RequestBody Category category, BindingResult errors){
        if(errors.hasErrors())
            categoryService.createErrorsMessage(errors);
        Category updatedCategory = categoryService.updateCategory(category, id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build().toUri();
        return ResponseEntity.created(location).body(updatedCategory);
    }

    @DeleteMapping("{id}")
    public Category deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }
}
