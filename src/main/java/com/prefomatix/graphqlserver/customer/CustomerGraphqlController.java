package com.prefomatix.graphqlserver.customer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Thariq
 * @since 25-04-2023
 **/
@Slf4j
@Controller
class CustomerGraphqlController {

    private final CustomerRepository customerRepository;

    CustomerGraphqlController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @MutationMapping
    Customer createCustomer(@Argument String name) {
        return this.customerRepository.save(new Customer(null, name));
    }

    @SubscriptionMapping
    Flux<Customer> newCustomers() {
        return Flux.fromIterable(this.customers()).delayElements(Duration.ofSeconds(1));
    }

    @BatchMapping
    Map<Customer, Profile> profile(List<Customer> customers) {
        log.info("calling the batch profile endpoint");
        var map = new HashMap<Customer, Profile>();
        for (var c : customers)
            map.put(c, new Profile(c.getId())); //
        return map;
    }


    @QueryMapping
    Collection<Customer> customersByName(@Argument String name) {
        var results = this.customerRepository.findByName(name);
        if (results.size() == 0) throw new CustomersNotFoundException("" + name);
        return results ;
    }


    @QueryMapping
    Iterable<Customer> customers() {
        log.info("returning all the customers.");
        return this.customerRepository.findAll();
    }
}

record Profile(Integer id) {
}
