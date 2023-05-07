package io.eventuate.examples.tram.sagas.ordersandcustomers.apigateway.proxies;

import io.eventuate.examples.tram.sagas.ordersandcustomers.customers.api.web.GetCustomerResponse;
import io.eventuate.examples.tram.sagas.products.api.web.CreateProductRequest;
import io.eventuate.examples.tram.sagas.products.api.web.CreateProductResponse;
import io.eventuate.examples.tram.sagas.products.api.web.GetProductsResponse;
import io.eventuate.examples.tram.sagas.products.domain.Product;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator;
import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductServiceProxy {
    private final CircuitBreaker cb;

    private WebClient client;
    private String productServiceUrl;
    private TimeLimiter timeLimiter;

    public ProductServiceProxy(WebClient client, CircuitBreakerRegistry circuitBreakerRegistry, String productServiceUrl, TimeLimiterRegistry timeLimiterRegistry) {
        this.client = client;
        this.cb = circuitBreakerRegistry.circuitBreaker("PRODUCT_SERVICE_CIRCUIT_BREAKER");
        this.timeLimiter = timeLimiterRegistry.timeLimiter("PRODUCT_SERVICE_TIME_LIMITER");
        this.productServiceUrl = productServiceUrl;
    }

    public Mono<Optional<GetProductsResponse>> getProducts(List<Product> productList) {
        Mono<ClientResponse> response = client
                .post()
                .uri(productServiceUrl + "/products", productList)
                .exchange();
        return response.flatMap(resp -> {
                    switch (resp.statusCode()) {
                        case OK:
                            return resp.bodyToMono(GetProductsResponse.class).map(Optional::of);
                        case NOT_FOUND:
                            Mono<Optional<GetProductsResponse>> notFound = Mono.just(Optional.empty());
                            return notFound;
                        default:
                            return Mono.error(UnknownProxyException.make("/products/", resp.statusCode(), productList.stream().map(p -> p.getId()).toString()));
                    }
                })
                .transformDeferred(TimeLimiterOperator.of(timeLimiter))
                .transformDeferred(CircuitBreakerOperator.of(cb))
                //.onErrorResume(CallNotPermittedException.class, e -> Mono.just(null))
                ;
    }

    public Mono<Optional<CreateProductResponse>> createProduct(CreateProductRequest createProductRequest) {
        Mono<ClientResponse> response = client
                .post()
                .uri(productServiceUrl + "/product", createProductRequest)
                .exchange();
        return response.flatMap(resp -> {
                    switch (resp.statusCode()) {
                        case OK:
                            return resp.bodyToMono(CreateProductResponse.class).map(Optional::of);
                        case NOT_FOUND:
                            Mono<Optional<CreateProductResponse>> notFound = Mono.just(Optional.empty());
                            return notFound;
                        default:
                            return Mono.error(UnknownProxyException.make("/product/", resp.statusCode(), createProductRequest.toString()));
                    }
                })
                .transformDeferred(TimeLimiterOperator.of(timeLimiter))
                .transformDeferred(CircuitBreakerOperator.of(cb))
                //.onErrorResume(CallNotPermittedException.class, e -> Mono.just(null))
                ;
    }
}
