package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.api.messaging.common;

import io.eventuate.examples.common.money.Money;
import io.eventuate.examples.tram.sagas.products.domain.Product;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

@Embeddable
public class OrderDetails {

  private Long customerId;

  @Embedded
  private Money orderTotal;

  private List<Product> productList;

  public OrderDetails() {
  }

  public OrderDetails(Long customerId, Money orderTotal, List<Product> productList) {
    this.customerId = customerId;
    this.orderTotal = orderTotal;
    this.productList = productList;
  }

  public Long getCustomerId() {
    return customerId;
  }

  public Money getOrderTotal() {
    return orderTotal;
  }

  public List<Product> getProductList() {
    return productList;
  }
}
