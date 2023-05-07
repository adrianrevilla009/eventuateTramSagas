package io.eventuate.examples.tram.sagas.ordersandcustomers.orders.sagas;

import io.eventuate.examples.tram.sagas.ordersandcustomers.customers.api.messaging.replies.CustomerCreditLimitExceeded;
import io.eventuate.examples.tram.sagas.ordersandcustomers.customers.api.messaging.replies.CustomerNotFound;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.api.messaging.sagas.createorder.CreateOrderSagaData;
import io.eventuate.examples.tram.sagas.ordersandcustomers.orders.domain.Order;
import io.eventuate.examples.tram.sagas.products.api.messaging.createorder.CreateProductSagaData;
import io.eventuate.examples.tram.sagas.products.domain.Product;
import io.eventuate.examples.tram.sagas.products.domain.ProductService;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;

public class CreateProductSaga implements SimpleSaga<CreateProductSagaData>  {
    private ProductService productService;

    public CreateProductSaga(ProductService productService) {
        this.productService = productService;
    }
    @Override
    public SagaDefinition<CreateProductSagaData> getSagaDefinition() {
        return this.sagaDefinition;
    }


    private SagaDefinition<CreateProductSagaData> sagaDefinition =
            step()
                    .invokeLocal(this::create)
                    .withCompensation(this::delete)
                    .build();

    private void create(CreateProductSagaData data) {
        Product product = productService.createProduct(data.getName(), data.getDescription(), data.getStock());
        data.setId(product.getId());
    }

    private void delete(CreateProductSagaData data) {
        /*Product product = productService.createProduct(data.getOrderDetails());
        data.setOrderId(product.getId());*/
    }
}
