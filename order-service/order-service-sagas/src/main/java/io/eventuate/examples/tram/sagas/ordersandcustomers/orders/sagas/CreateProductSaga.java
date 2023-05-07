package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.sagas;

import io.eventuate.examples.tram.sagas.ordersandcustomers.customers.api.messaging.replies.CustomerNotFound;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.api.messaging.common.RejectionReason;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.api.messaging.sagas.createorder.CreateOrderSagaData;
import io.eventuate.examples.tram.sagas.products.api.messaging.createproduct.CreateProductSagaData;
import io.eventuate.examples.tram.sagas.products.api.messaging.replies.ProductNotFound;
import io.eventuate.examples.tram.sagas.products.domain.Product;
import io.eventuate.examples.tram.sagas.products.domain.ProductService;
import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

public class CreateProductSaga implements SimpleSaga<CreateProductSagaData>  {
    private ProductServiceProxy productService;

    public CreateProductSaga(ProductServiceProxy productService) {
        this.productService = productService;
    }
    @Override
    public SagaDefinition<CreateProductSagaData> getSagaDefinition() {
        return this.sagaDefinition;
    }


    private SagaDefinition<CreateProductSagaData> sagaDefinition =
            step()
                    .invokeParticipant(this::create)
                    .withCompensation(this::delete)
                    .build();

    private CommandWithDestination create(CreateProductSagaData data) {
        return productService.createProduct(data.getName(), data.getDescription(), data.getStock());
    }

    private CommandWithDestination delete(CreateProductSagaData data) {
        return productService.deleteProduct(data.getId());
    }
}
