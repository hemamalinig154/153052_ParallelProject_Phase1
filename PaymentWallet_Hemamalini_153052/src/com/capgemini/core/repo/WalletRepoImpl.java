package com.capgemini.core.repo;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.core.pw.beans.Customer;
import com.capgemini.core.pw.exception.InvalidInputException;

public class WalletRepoImpl implements WalletRepo
{
	private Map<String, Customer> data = new HashMap<String,Customer>(); 
	
	public WalletRepoImpl(Map<String, Customer> data) 
	{
		super();
		this.data = data;
	}
	
	

	public WalletRepoImpl() {
		super();
	}



	@Override
	public boolean save(Customer customer) 
	{
		if(customer==null)
			throw new InvalidInputException("Invalid details") ;
		else
			data.put(customer.getMobileNo(), customer) ;
		return true;
	}

	@Override
	public Customer findOne(String mobileNo) {
		// TODO Auto-generated method stub
		
		Customer customer = null ;
		
		if(data.containsKey(mobileNo))
			customer = data.get(mobileNo) ;
			
		return customer;
	}
	

}
