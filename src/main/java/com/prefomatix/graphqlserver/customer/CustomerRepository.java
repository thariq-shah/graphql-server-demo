package com.prefomatix.graphqlserver.customer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * @author Thariq
 * @since 25-04-2023
 **/

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    Collection<Customer> findByName(String name);
}
