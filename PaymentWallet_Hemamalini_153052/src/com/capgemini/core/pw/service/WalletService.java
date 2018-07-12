package com.capgemini.core.pw.service;

import java.math.BigDecimal;

import com.capgemini.core.pw.beans.Customer;

public interface WalletService 
{
	public Customer createAccount(String name ,String mobileno, BigDecimal amount);
	public Customer showBalance (String mobileno);
	public Customer fundTransfer (String sourceMobileNo,String targetMobileNo, BigDecimal amount);
	public Customer depositAmount (String mobileNo,BigDecimal amount );
	public Customer withdrawAmount(String mobileNo, BigDecimal amount);
	
	public boolean isValidName(String name) ;
	public boolean isValidMobileNo(String mobileno) ;
	public boolean isValidAmount(BigDecimal amount) ;

}
