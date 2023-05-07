package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.sagas;

import io.eventuate.examples.tram.sagas.products.api.messaging.commands.CreateProductCommand;
import io.eventuate.examples.tram.sagas.products.api.messaging.commands.DeleteProductCommand;
import io.eventuate.tram.commands.consumer.CommandWithDestination;

import static io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder.send;

public class ProductServiceProxy {
    CommandWithDestination createProduct(String name, String description, Long stock) {
        return send(new CreateProductCommand(name, description, stock))
                .to("productService")
                .build();
    }

    CommandWithDestination deleteProduct(Long id) {
        return send(new DeleteProductCommand(id))
                .to("productService")
                .build();
    }
}
