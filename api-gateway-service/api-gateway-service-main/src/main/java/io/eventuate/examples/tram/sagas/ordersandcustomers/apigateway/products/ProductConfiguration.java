package io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.products;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ProductDestinations.class)
public class ProductConfiguration {

    @Bean
    public RouteLocator productProxyRouting(RouteLocatorBuilder builder, ProductDestinations productDestinations) {
        return builder.routes()
                .route(r -> r.path("/products/**").and().method("POST").uri(productDestinations.getProductServiceUrl())) // Get stock products
                .route(r -> r.path("/product/**").and().method("POST").uri(productDestinations.getProductServiceUrl()))  // Add product
                .build();
    }
}
