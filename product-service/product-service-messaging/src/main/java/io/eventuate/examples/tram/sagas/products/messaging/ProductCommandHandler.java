package io.eventuate.examples.tram.sagas.products.messaging;

import io.eventuate.examples.tram.sagas.products.api.messaging.commands.CreateProductCommand;
import io.eventuate.examples.tram.sagas.products.api.messaging.commands.DeleteProductCommand;
import io.eventuate.examples.tram.sagas.products.api.messaging.commands.GetProductsCommand;
import io.eventuate.examples.tram.sagas.products.api.messaging.replies.*;
import io.eventuate.examples.tram.sagas.products.domain.ProductNotFoundException;
import io.eventuate.examples.tram.sagas.products.domain.ProductService;
import io.eventuate.examples.tram.sagas.products.domain.ProductsWithNoStockException;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;
import org.hibernate.sql.Delete;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

public class ProductCommandHandler {

  private ProductService productService;

  public ProductCommandHandler(ProductService productService) {
    this.productService = productService;
  }

  public CommandHandlers commandHandlerDefinitions() {
    return SagaCommandHandlersBuilder
            .fromChannel("productService")
            .onMessage(CreateProductCommand.class, this::createProduct)
            .onMessage(DeleteProductCommand.class, this::deleteProduct)
            .onMessage(GetProductsCommand.class, this::getProducts)
            .build();
  }

  public Message createProduct(CommandMessage<CreateProductCommand> cm) {
    CreateProductCommand cmd = cm.getCommand();
    try {
      productService.createProduct(cmd.getName(), cmd.getDescription(), cmd.getStock());
      return withSuccess(new ProductCreated());
    } catch (ProductNotFoundException e) {
      return withFailure(new ProductNotFound());
    }
  }

  public Message deleteProduct(CommandMessage<DeleteProductCommand> cm) {
    DeleteProductCommand cmd = cm.getCommand();
    try {
      productService.deleteProduct(cmd.getProductId());
      return withSuccess(new ProductDeleted());
    } catch (ProductNotFoundException e) {
      return withFailure(new ProductNotFound());
    }
  }

  public Message getProducts(CommandMessage<GetProductsCommand> cm) {
    GetProductsCommand cmd = cm.getCommand();
    try {
      productService.getProducts(cmd.getProductList());
      return withSuccess(new ProductsGet());
    } catch (ProductsWithNoStockException e) {
      return withFailure(new ProductsWithNoStock());
    }
  }

}
