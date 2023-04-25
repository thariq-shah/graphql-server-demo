package com.prefomatix.graphqlserver;

import com.prefomatix.graphqlserver.customer.Customer;
import com.prefomatix.graphqlserver.customer.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@Slf4j
@SpringBootApplication
public class GraphqlServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlServerApplication.class, args);
    }


    @Bean
    ApplicationListener<ApplicationReadyEvent> readyEventApplicationListener(
            CustomerRepository repository) {
        return event -> {
            repository.deleteAll();
            Set.of("A", "B", "C", "D").forEach(c -> repository.save(new Customer(null, c)));
            repository.findAll().forEach(System.out::println);
        };
    }
}
