package io.eventuate.examples.tram.sagas.products.domain;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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

    public List<Product> getProducts() {
        List<Product> productList = (List<Product>) productRepository.findAll();
        List<Product> productsWithStock = new ArrayList<>();
        for (Product product : productList) {
            if (product.getStock() > 0) {
                productsWithStock.add(product);
            }
        }
        return productsWithStock;
    }

}
