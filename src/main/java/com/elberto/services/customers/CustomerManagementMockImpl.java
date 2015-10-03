package com.elberto.services.customers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.elberto.domain.Call;
import com.elberto.domain.Customer;

public class CustomerManagementMockImpl implements CustomerManagementService 
{
	
	private HashMap<String, Customer> customerMap;
	
	public CustomerManagementMockImpl()
	{
		customerMap = new HashMap<String, Customer>();
		
		customerMap.put("CS03939", new Customer("CS03939", "Acme Ltd", "Some notes."));
		customerMap.put("CS03940", new Customer("CS03940", "VirtualPairProgrammers", "Some notes."));
		customerMap.put("CS03941", new Customer("CS03941", "Microsoft", "Some notes."));
	}
	
	
	public Customer newCustomer(Customer newCustomer) {
		customerMap.put(newCustomer.getCustomerId(), newCustomer);
		
		return newCustomer;
	}

	
	public void updateCustomer(Customer changedCustomer) {
		customerMap.put(changedCustomer.getCompanyName(), changedCustomer);
	}

	
	public void deleteCustomer(Customer oldCustomer) {
		customerMap.remove(oldCustomer.getCustomerId());
	}

	
	public Customer findCustomerById(String customerId)
			throws CustomerNotFoundException {

		Customer foundCustomer = customerMap.get(customerId);
		if (foundCustomer == null) throw new CustomerNotFoundException();
		
		return foundCustomer;
	}

	
	public List<Customer> findCustomersByName(String name) {

		List<Customer> results = new ArrayList<Customer>();
		
		for (Customer nextCustomer : customerMap.values())
		{
			if (nextCustomer.getCompanyName().equals(name))
			{
				results.add(nextCustomer);
			}
		}
		
		return results;
	}

	
	public List<Customer> getAllCustomers() {
		return new ArrayList<Customer>(customerMap.values());
	}

	
	public Customer getFullCustomerDetail(String customerId)
			throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		return this.findCustomerById(customerId);
	}

	
	public void recordCall(String customerId, Call callDetails)
			throws CustomerNotFoundException {

		Customer customer = this.getFullCustomerDetail(customerId);
		
		customer.addCall(callDetails);
		
	}

	
	public List<Customer> getAllCustomersWithDetail() {
		// TODO Auto-generated method stub
		return null;
	}

}
