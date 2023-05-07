package io.eventuate.examples.tram.sagas.products.api.messaging.commands;

import io.eventuate.examples.common.money.Money;
import io.eventuate.tram.commands.common.Command;

public class DeleteProductCommand implements Command {
  private Long productId;

  public DeleteProductCommand() {
  }

  public DeleteProductCommand(Long productId) {
    this.productId = productId;
  }

  public Long getProductId() {
    return productId;
  }

  public void setProductId(Long productId) {
    this.productId = productId;
  }
}
