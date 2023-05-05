package org.example;

import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.createproduct.CreateProductSagaData;
import org.springframework.stereotype.Service;

@Service
public class CreateProductSaga implements SimpleSaga<CreateProductSagaData> {
    @Autowired
    private ProductService productService;
    // private CustomerServiceProxy customerService;


    public CreateProductSaga(ProductService productService) {
        this.productService = productService;
    }

    private SagaDefinition<CreateProductSagaData> sagaDefinition =
            step()
                    .invokeLocal(this::create)
                    .withCompensation(this::delete)
                    .build();
                    /*.step()
                    .invokeParticipant(this::reserveCredit)
                    .onReply(CustomerNotFound.class, this::handleCustomerNotFound)
                    .onReply(CustomerCreditLimitExceeded.class, this::handleCustomerCreditLimitExceeded)
                    .step()
                    .invokeLocal(this::approve)
                    .build();*/

//    private void handleCustomerNotFound(CreateOrderSagaData data, CustomerNotFound reply) {
//        data.setRejectionReason(RejectionReason.UNKNOWN_CUSTOMER);
//    }
//
//    private void handleCustomerCreditLimitExceeded(CreateOrderSagaData data, CustomerCreditLimitExceeded reply) {
//        data.setRejectionReason(RejectionReason.INSUFFICIENT_CREDIT);
//    }


    @Override
    public SagaDefinition<CreateProductSagaData> getSagaDefinition() {
        return this.sagaDefinition;
    }

    private void create(CreateProductSagaData data) {
        Product product = productService.createProduct(new Product(data.getName(), data.getDescription(), data.getStock()));
        data.setId(product.getId());
    }

    private void delete(CreateProductSagaData data) {
        productService.deleteProduct(data);
    }

//    private CommandWithDestination reserveCredit(CreateOrderSagaData data) {
//        long orderId = data.getOrderId();
//        Long customerId = data.getOrderDetails().getCustomerId();
//        Money orderTotal = data.getOrderDetails().getOrderTotal();
//        return customerService.reserveCredit(orderId, customerId, orderTotal);
//    }
//
//    private void approve(CreateOrderSagaData data) {
//        orderService.approveOrder(data.getOrderId());
//    }
//
//    private void reject(CreateOrderSagaData data) {
//        orderService.rejectOrder(data.getOrderId(), data.getRejectionReason());
//    }
}
