package org.example;

import io.eventuate.tram.spring.flyway.EventuateTramFlywayMigrationConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackageClasses = {ProductRepository.class})
@EntityScan(basePackageClasses = {Product.class})
@Import(EventuateTramFlywayMigrationConfiguration.class)
public class ProductPersistenceConfiguration {
}
