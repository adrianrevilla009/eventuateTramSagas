package org.example;

import org.example.createproduct.CreateProductSagaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(CreateProductSagaData data) {
        productRepository.deleteById(data.getId());
    }

    public boolean checkProductStock(Long productId) {
        return productRepository.findById(productId).get().getStock() > 0;
    }

}
