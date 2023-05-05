package org.example;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import org.example.common.CreateProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductSagaService {

    @Autowired
    private CreateProductSaga createProductSaga;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SagaInstanceFactory sagaInstanceFactory;

    public ProductSagaService(CreateProductSaga createProductSaga, ProductRepository productRepository, SagaInstanceFactory sagaInstanceFactory) {
        this.createProductSaga = createProductSaga;
        this.productRepository = productRepository;
        this.sagaInstanceFactory = sagaInstanceFactory;
    }

    @Transactional
    public Product createProduct(CreateProductRequest createProductRequest) {
        CreateProductSagaData data = new CreateProductSagaData(createProductRequest);
        // TODO esto no funciona
        // sagaInstanceFactory.create(createProductSaga, data);
        Product product = productRepository.findByName(data.getName());
        return product;
    }
}
