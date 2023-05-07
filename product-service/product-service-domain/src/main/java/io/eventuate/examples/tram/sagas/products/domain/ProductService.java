package io.eventuate.examples.tram.sagas.products.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, String description, Long stock) {
        Product product = Product.createProduct(name, description, stock);
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public boolean checkProductStock(Long productId) {
        return productRepository.findById(productId).get().getStock() > 0;
    }

}
