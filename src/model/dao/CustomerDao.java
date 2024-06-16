package model.dao;

import model.entity.Customer;

import java.util.List;

public interface CustomerDao {
    List<Customer> queryAllCustomers();
    int updateAllCustomersById(Integer id);
    int deleteAllCustomersById(Integer id);
    int addNewCustomersById(Customer customer);
    Customer searchCustomerById(Integer id);

}
