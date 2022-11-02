package com.mainoramg.buchalka.section8;

import java.util.ArrayList;

public class Branch {
    private String name;
    private ArrayList<Customer> customers;

    public boolean newCustomer(String name, double initialTransaction) {
        if (findCustomer(name) == null) {
            customers.add(new Customer(name, initialTransaction));
            return true;
        }
        return false;
    }

    public boolean addCustomerTransaction(String name, double transaction) {
        Customer customer = findCustomer(name);
        if (customer != null) {
            customer.addTransaction(transaction);
            return true;
        }
        return false;
    }

    private Customer findCustomer(String name) {
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if (customer.getName().equals(name)) {
                return customer;
            }
        }

        return null;
    }

    public Branch(String name) {
        this.name = name;
        this.customers = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
