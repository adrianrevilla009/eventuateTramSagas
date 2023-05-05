package org.example;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories
@EnableAutoConfiguration
@Import(OptimisticLockingDecoratorConfiguration.class)
public class ProductSagasConfiguration {

    @Bean
    public ProductSagaService productSagaService(ProductRepository productRepository, SagaInstanceFactory sagaInstanceFactory, CreateProductSaga createProductSaga) {
        return new ProductSagaService(createProductSaga, productRepository, sagaInstanceFactory);
    }

    @Bean
    public CreateProductSaga createProductSaga(ProductService orderService) {
        return new CreateProductSaga(orderService);
    }

//    @Bean
//    public CustomerServiceProxy customerServiceProxy() {
//        return new CustomerServiceProxy();
//    }

}