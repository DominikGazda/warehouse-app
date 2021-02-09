package com.warehouse.product.controller;

import com.warehouse.product.common.ResponseTemplate;
import com.warehouse.product.entity.Product;
import com.warehouse.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("")
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @PostMapping("")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product savedProduct = productService.saveProduct(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getProductId())
                .toUri();
        return ResponseEntity.created(location).body(savedProduct);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTemplate> getProductWithDetailsById(@PathVariable Long id){
        ResponseTemplate productWithDetails = productService.getProductWithDetailsById(id);
        return ResponseEntity.ok(productWithDetails);
    }
}
