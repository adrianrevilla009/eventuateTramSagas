package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.example.common.CreateProductRequest;

@RestController
public class ProductController {
    @Autowired
    private ProductSagaService productSagaService;
    @Autowired
    private ProductRepository productRepository;

    public ProductController(ProductSagaService productSagaService, ProductRepository productRepository) {
        this.productSagaService = productSagaService;
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
        Product product = productSagaService.createProduct(createProductRequest);
        return new CreateProductResponse(product.getStock());
    }

}