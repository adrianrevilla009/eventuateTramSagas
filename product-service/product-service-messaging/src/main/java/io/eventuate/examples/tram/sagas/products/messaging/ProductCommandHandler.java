package io.eventuate.examples.tram.sagas.products.messaging;

import io.eventuate.examples.tram.sagas.products.api.messaging.commands.CreateProductCommand;
import io.eventuate.examples.tram.sagas.products.api.messaging.replies.ProductCreated;
import io.eventuate.examples.tram.sagas.products.api.messaging.replies.ProductNotFound;
import io.eventuate.examples.tram.sagas.products.domain.ProductNotFoundException;
import io.eventuate.examples.tram.sagas.products.domain.ProductService;
import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

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

}
