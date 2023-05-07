package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.sagas;

import io.eventuate.examples.tram.sagas.products.api.messaging.commands.CreateProductCommand;
import io.eventuate.examples.tram.sagas.products.api.messaging.commands.DeleteProductCommand;
import io.eventuate.examples.tram.sagas.products.api.messaging.commands.GetProductsCommand;
import io.eventuate.examples.tram.sagas.products.domain.Product;
import io.eventuate.tram.commands.consumer.CommandWithDestination;

import java.util.List;

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

    CommandWithDestination getProducts(List<Product> productList) {
        return send(new GetProductsCommand(productList))
                .to("productService")
                .build();
    }
}
