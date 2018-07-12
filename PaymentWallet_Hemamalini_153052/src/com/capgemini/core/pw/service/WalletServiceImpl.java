package com.capgemini.core.pw.service;

import java.math.BigDecimal;
import java.util.Map;

import com.capgemini.core.pw.beans.Customer;
import com.capgemini.core.pw.beans.Wallet;
import com.capgemini.core.pw.exception.InsufficientBalanceException;
import com.capgemini.core.pw.exception.InvalidInputException;
import com.capgemini.core.repo.WalletRepo;
import com.capgemini.core.repo.WalletRepoImpl;

public class WalletServiceImpl implements WalletService{

private WalletRepo repo;
	
	public WalletServiceImpl(Map<String, Customer> data)
	{
		repo= new WalletRepoImpl(data);
	}
	
	public WalletServiceImpl(WalletRepo repo) 
	{
		super();
		this.repo = repo;
	}

	public WalletServiceImpl() 
	{
		repo = new WalletRepoImpl() ;
	}

	
	@Override
	public Customer createAccount(String name, String mobileno, BigDecimal amount) 
	{
		// TODO Auto-generated method stub
		
		Customer customer = null ;
		
		if(name==null || mobileno==null || amount.equals(new BigDecimal(0)))
				throw new InvalidInputException("Invalid Input") ;
		
		if(!isValidName(name))
			throw new InvalidInputException("Name must be valid") ;
		if(!isValidMobileNo(mobileno))
			throw new InvalidInputException("Mobile number must be 10 digits") ;
		if(!isValidAmount(amount))
			throw new InvalidInputException("Amount must be valid") ;
		
		customer = new Customer(name,mobileno,new Wallet(amount)) ;
		
		if(repo.save(customer)==false)
		{
			throw new InvalidInputException("Sorry...Account creation failed") ;
		}
		else
		return customer;
	}

	@Override
	public Customer showBalance(String mobileno) 
	{
		// TODO Auto-generated method stub
		Customer customer = null ;
		
		if(mobileno==null || !(isValidMobileNo(mobileno)))
			throw new InvalidInputException("Mobile number must contain only 10 digits") ;
		else
			customer=repo.findOne(mobileno);
			
		
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no ");
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) 
	{
		if(sourceMobileNo==null || !(isValidMobileNo(sourceMobileNo)))
			throw new InvalidInputException("Mobile number must contain only 10 digits") ;
		
		if(targetMobileNo==null || !(isValidMobileNo(targetMobileNo)))
			throw new InvalidInputException("Mobile number must contain only 10 digits") ;
		
		
		Customer customer1 = null ;
		Customer customer2 = null ;
		
		customer1 = withdrawAmount(sourceMobileNo, amount) ;
		
		if(repo.save(customer1)==false)
			throw new InvalidInputException("Account cannot be updated") ;
		
		if(customer1==null)
			throw new InsufficientBalanceException("Insufficient Balance") ;
		
		customer2 = depositAmount(targetMobileNo, amount) ;
		
		if(repo.save(customer2)==false)
			throw new InvalidInputException("Account cannot be updated") ;
		
		return customer1;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) 
	{
		// TODO Auto-generated method stub
		
		if((mobileNo==null) || !(isValidMobileNo(mobileNo)))
			throw new InvalidInputException("Mobile number must contain only 10 digits") ;
		
		if(!isValidAmount(amount))
			throw new InvalidInputException("Amount cannot be negative") ;
		
		Customer customer = repo.findOne(mobileNo) ;
		
		if(customer!=null)
			customer.getWallet().setBalance(customer.getWallet().getBalance().add(amount));
		
		if(repo.save(customer)==false)
			throw new InvalidInputException("Account cannot be updated") ;
		
		return customer;
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {
		// TODO Auto-generated method stub
		
		if((mobileNo==null) || !(isValidMobileNo(mobileNo)))
			throw new InvalidInputException("Mobile number must contain only 10 digits") ;
		
		if(!isValidAmount(amount))
			throw new InvalidInputException("Amount cannot be negative") ;
		
		Customer customer = repo.findOne(mobileNo) ;
		
		if(customer!=null && customer.getWallet().getBalance().compareTo(amount)>0)
		{
			customer.getWallet().setBalance(customer.getWallet().getBalance().subtract(amount));
			System.out.println("Withdrawal successfully done");
		}
		else
			throw new InsufficientBalanceException("Withdrawal not possible") ;
		
		if(repo.save(customer)==false)
			throw new InvalidInputException("Acount cannot be updated") ;
		
	
		return customer;
		
	}

	@Override
	public boolean isValidName(String name) {
		// TODO Auto-generated method stub
		if(name.matches("[A-Z][a-zA-Z]*"))
			return true;
		
		return false ;
	}

	@Override
	public boolean isValidMobileNo(String mobileno) {
		// TODO Auto-generated method stub
		if(mobileno.matches("[7-9]?[0-9]{10}"))
			return true ;
		
		return false;
	}

	@Override
	public boolean isValidAmount(BigDecimal amount) {
		// TODO Auto-generated method stub
		if(amount.compareTo(new BigDecimal(0))>0)
			return true ;
		
		return false;
	}
	
	

}
