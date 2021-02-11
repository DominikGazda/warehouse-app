package com.warehouse.category.service;

import com.warehouse.category.entity.Category;
import com.warehouse.category.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {
        if(category.getCategoryId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Category cannot have id");
        return categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot find category with provided id"));
    }

    public Category updateCategory(Category category, Long id) {
        if(!category.getCategoryId().equals(id))
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category id must be same as id in path variable");
        Category oldCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot find category with provided id"));
        return categoryRepository.save(category);
    }

    public Category deleteCategory(Long id) {
        Category categoryToDelete = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot find category with provided id"));
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete;
    }

    public void createErrorsMessage(BindingResult errors){
        List<ObjectError> errorList = errors.getAllErrors();
        String message = errorList.stream()
                .map(ObjectError::getDefaultMessage)
                .map(err -> err +" ")
                .map(String::toString)
                .collect(Collectors.joining());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message);
    }
}
