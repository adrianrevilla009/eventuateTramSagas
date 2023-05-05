package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDomainConfiguration {

  @Bean
  public ProductService orderService(ProductRepository productRepository) {
    return new ProductService(productRepository);
  }
}
