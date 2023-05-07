package io.eventuate.examples.tram.sagas.products.web;

import io.eventuate.examples.tram.sagas.products.api.web.CreateProductRequest;
import io.eventuate.examples.tram.sagas.products.api.web.CreateProductResponse;
import io.eventuate.examples.tram.sagas.products.api.web.GetProductsResponse;
import io.eventuate.examples.tram.sagas.products.domain.Product;
import io.eventuate.examples.tram.sagas.products.domain.ProductRepository;
import io.eventuate.examples.tram.sagas.products.domain.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

  private ProductService productService;
  private ProductRepository productRepository;

  @Autowired
  public ProductController(ProductService productService, ProductRepository productRepository) {
    this.productService = productService;
    this.productRepository = productRepository;
  }

  public ProductController() {
  }

  @RequestMapping(value = "/product", method = RequestMethod.POST)
  public CreateProductResponse createProduct(@RequestBody CreateProductRequest createProductRequest) {
    Product product = productService.createProduct(createProductRequest.getName(), createProductRequest.getDescription(), createProductRequest.getStock());
    return new CreateProductResponse(product.getId());
  }

  @RequestMapping(value = "/products", method = RequestMethod.GET)
  public GetProductsResponse getProducts() {
    List<Product> productList = productService.getProducts();
    return new GetProductsResponse(productList);
  }
}
