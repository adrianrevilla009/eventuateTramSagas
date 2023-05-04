package org.example.controller;

import org.example.domain.Product;
import org.example.dto.CreateProductRequest;
import org.example.dto.CreateProductResponse;
import org.example.repository.ProductRepository;
import org.example.service.ProductSagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductSagaService productSagaService;
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
        Product product = productSagaService.createProduct(createProductRequest);
        return new CreateProductResponse(product.getStock());
    }

}
