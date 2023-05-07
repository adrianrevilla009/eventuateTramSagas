package io.eventuate.examples.tram.sagas.products.messaging;

import io.eventuate.examples.tram.sagas.products.domain.ProductService;
import io.eventuate.tram.commands.consumer.CommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductCommandHandlerConfiguration {

  @Bean
  public ProductCommandHandler productCommandHandler(ProductService productService) {
    return new ProductCommandHandler(productService);
  }

  // TODO Exception handler for ProductNotFoundException

  @Bean
  public CommandDispatcher productCommandDispatcher(ProductCommandHandler target,
                                                     SagaCommandDispatcherFactory sagaCommandDispatcherFactory) {

    return sagaCommandDispatcherFactory.make("productCommandDispatcher", target.commandHandlerDefinitions());
  }

}
