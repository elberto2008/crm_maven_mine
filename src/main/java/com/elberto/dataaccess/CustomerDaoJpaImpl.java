package com.elberto.dataaccess;

import java.util.List;



import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.elberto.domain.Call;
import com.elberto.domain.Customer;
import com.elberto.exceptions.RecordNotFoundException;

public class CustomerDaoJpaImpl implements CustomerDao 
{

	@PersistenceContext
	private EntityManager em;
	
	
	public void create(Customer customer) 
	{
		em.persist(customer);
	}

	
	public Customer getById(String customerId) throws RecordNotFoundException 
	{
		try
		{
			return (Customer)em.createQuery("select customer from Customer as customer where customer.customerId=:customerId")
				.setParameter("customerId", customerId)
				.getSingleResult();
		}
		catch (NoResultException e)
		{
			throw new RecordNotFoundException();
		}
	}

	
	public List<Customer> getByName(String name) {
		return em.createQuery("select customer from Customer where customer.companyName=:companyName", Customer.class)
				 .setParameter("companyName", name)
				 .getResultList();
	}

	
	public void update(Customer customerToUpdate)
			throws RecordNotFoundException 
	{
		em.merge(customerToUpdate);
	}

	
	public void delete(Customer oldCustomer) throws RecordNotFoundException 
	{
		oldCustomer = em.merge(oldCustomer);
		em.remove(oldCustomer);
	}

	
	public List<Customer> getAllCustomers() 
	{
		return em.createQuery("select customer from Customer as customer ", Customer.class).getResultList();
	}

	
	public Customer getFullCustomerDetail(String customerId)throws RecordNotFoundException 
	{
		
		try
		{
			return (Customer)em.createQuery("select customer from Customer as customer left join fetch customer.calls where customer.customerId=:customerId")
					.setParameter("customerId", customerId)
					.getSingleResult();
		}
		catch (javax.persistence.NoResultException e)
		{
			throw new RecordNotFoundException();
		}
		
	}

	
	public void addCall(Call newCall, String customerId)throws RecordNotFoundException 
	{
		try
		{
			Customer customer = em.find(Customer.class, customerId);
			customer.addCall(newCall);
		}
		catch(java.lang.NullPointerException e)
		{
			throw new RecordNotFoundException();

		}

	}

	
	public List<Customer> getAllCustomersWithDetail() 
	{

			return em.createQuery("select customer from Customer as customer    left join fetch   customer.calls",Customer.class).getResultList();
			
    }

	
	public String getNextAvaillableCustomerID() 
	{
		return (em.createNativeQuery("select CUSTOMER_ID_SEQ.nextval from dual").getSingleResult()).toString();
	}

}
