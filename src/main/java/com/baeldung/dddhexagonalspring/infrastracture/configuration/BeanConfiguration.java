package com.baeldung.dddhexagonalspring.infrastracture.configuration;

import com.baeldung.dddhexagonalspring.domain.repository.OrderRepository;
import com.baeldung.dddhexagonalspring.domain.service.DomainOrderService;
import com.baeldung.dddhexagonalspring.domain.service.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    OrderService orderService(final OrderRepository orderRepository) {
        return new DomainOrderService(orderRepository);
    }
}
