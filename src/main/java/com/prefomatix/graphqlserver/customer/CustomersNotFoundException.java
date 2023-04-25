package com.prefomatix.graphqlserver.customer;

/**
 * @author Thariq
 * @since 25-04-2023
 **/
public class CustomersNotFoundException extends IllegalArgumentException {

    private final String name;

    CustomersNotFoundException(String name) {
        this.name = name;
    }

    public String name() {
        return this.name;
    }
}