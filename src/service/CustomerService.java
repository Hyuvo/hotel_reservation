package service;

import model.Customer;

import java.util.HashMap;

public class CustomerService {
    private static HashMap<String, Customer> customers = new HashMap<>();
    public void addCustomer(String email, String firstName, String lastName) {
        customers.put(email, new Customer(firstName, lastName, email));
    }
    public Customer getCustomer(String customerEmail) {
        // check email valid?
        return customers.get(customerEmail);
    }
}
