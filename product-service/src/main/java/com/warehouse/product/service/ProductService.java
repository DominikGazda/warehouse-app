package com.warehouse.product.service;

import com.warehouse.product.common.Category;
import com.warehouse.product.common.RequestTemplate;
import com.warehouse.product.common.ResponseTemplate;
import com.warehouse.product.entity.Product;
import com.warehouse.product.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;
    private RestTemplate restTemplate;

    public ProductService(ProductRepository productRepository, RestTemplate restTemplate){
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product saveProduct(Product product) {
        if(product.getProductId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Product cannot have id");
        return productRepository.save(product);
    }

    public ResponseTemplate getProductWithDetailsById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot find product with provided id"));
        RequestTemplate requestTemplate = restTemplate.getForObject("http://WAREHOUSE-SERVICE/api/warehouse/"+product.getWarehouseId(),
                RequestTemplate.class);
        Category category = restTemplate.getForObject("http://CATEGORY-SERVICE/api/category/"+product.getProductId(),
                Category.class);
        return new ResponseTemplate(product, requestTemplate.getWarehouse(), requestTemplate.getAddress(), category);
    }
}
