package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.api.web;


import io.eventuate.examples.common.money.Money;
import io.eventuate.examples.tram.sagas.products.domain.Product;

import java.util.List;

public class CreateOrderRequest {
  private Money orderTotal;
  private Long customerId;

  private List<Product> productList;

  public CreateOrderRequest() {
  }

  public CreateOrderRequest(Long customerId, Money orderTotal, List<Product> productList) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
    this.productList = productList;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public List<Product> getProductList() {
    return productList;
  }
}
