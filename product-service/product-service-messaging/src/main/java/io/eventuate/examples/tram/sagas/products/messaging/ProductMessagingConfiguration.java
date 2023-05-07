package io.eventuate.examples.tram.sagas.products.messaging;

import io.eventuate.tram.spring.flyway.EventuateTramFlywayMigrationConfiguration;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({OptimisticLockingDecoratorConfiguration.class, EventuateTramFlywayMigrationConfiguration.class,
        ProductCommandHandlerConfiguration.class})
public class ProductMessagingConfiguration {



}
