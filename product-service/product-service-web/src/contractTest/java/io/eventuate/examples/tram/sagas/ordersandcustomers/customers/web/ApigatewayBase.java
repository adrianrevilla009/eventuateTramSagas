package io.eventuate.examples.tram.sagas.ordersandcustomers.customers.web;

import io.eventuate.examples.tram.sagas.products.domain.Product;
import io.eventuate.examples.tram.sagas.products.domain.ProductRepository;
import io.eventuate.examples.tram.sagas.products.domain.ProductService;
import io.eventuate.examples.tram.sagas.products.web.ProductController;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class ApigatewayBase {

  @BeforeEach
  public void setup() {
    ProductService productService = mock(ProductService.class);
    ProductRepository productRepository = mock(ProductRepository.class);
    ProductController productController = new ProductController(productService, productRepository);

    Product product = new Product("Leche", "Desnatada", 10L);
    ReflectionTestUtils.setField(product, "id", 101L);

    when(productService.createProduct(product.getName(), product.getDescription(), product.getStock())).thenReturn(product);
    when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

    RestAssuredMockMvc.standaloneSetup(MockMvcBuilders.standaloneSetup(productController));

  }
}
