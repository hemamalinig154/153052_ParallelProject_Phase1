package com.capgemini.core.pw.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.core.pw.beans.Customer;
import com.capgemini.core.pw.beans.Wallet;
import com.capgemini.core.pw.exception.InvalidInputException;
import com.capgemini.core.pw.service.WalletService;
import com.capgemini.core.pw.service.WalletServiceImpl;

public class TestClass {

	
	WalletService walletService;
	
	@Before
	public void initData()
	{
		 Map<String,Customer> data= new HashMap<String, Customer>();
		 Customer cust1=new Customer("Amit", "9900112212",new Wallet(new BigDecimal(9000)));
		 Customer cust2=new Customer("Ajay", "9963242422",new Wallet(new BigDecimal(6000)));
		 Customer cust3=new Customer("Yogini", "9922950519",new Wallet(new BigDecimal(7000)));
				
		 data.put("9900112212", cust1);
		 data.put("9963242422", cust2);	
		 data.put("9922950519", cust3);	
			walletService= new WalletServiceImpl(data);
			
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount1() 
	{
		walletService.createAccount(null, null, new BigDecimal(0)) ;
	}

	@Test(expected=InvalidInputException.class)
	public void testShowBalance2() 
	{
		walletService.showBalance(null) ;
	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer3() 
	{
		walletService.fundTransfer(null, "9876543210", new BigDecimal(1000)) ;
	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer4() 
	{
		walletService.fundTransfer("9876543210",null, new BigDecimal(1000)) ;
	}
	
	@Test(expected=InvalidInputException.class)
	public void testDepositAmount5() 
	{
		walletService.depositAmount(null, new BigDecimal(10000)) ;
	}

	@Test(expected=InvalidInputException.class)
	public void testWithdrawAmount6() 
	{
		walletService.withdrawAmount(null, new BigDecimal(10000)) ;
	}

	@Test(expected=InvalidInputException.class)
	public void testCreateAccount7() {
		
		walletService.createAccount(null,null,null);
		
		
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount8() throws InvalidInputException
	{
		
		walletService.createAccount(null,null,new BigDecimal(-100));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount9() 
	{
		
		walletService.createAccount(null,"9900112212",new BigDecimal(9000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount10() 
	{
		
		walletService.createAccount("","9900112212",new BigDecimal(9000));
	}
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount11() 
	{
		
		walletService.createAccount("","",new BigDecimal(9000));
	}
	
	@Test(expected=InvalidInputException.class)
	public void testCreateAccount12() 
	{
		
		walletService.createAccount("Eric","9898989898",new BigDecimal(-100));
	}

	@Test	
	public void testCreateAccount13() {
			
			
			
			Customer cust=new Customer();
			cust=walletService.createAccount("Eric","9898989898",new BigDecimal(6000));
			assertEquals("Eric", cust.getName());
		
			
			
		}
	@Test
	public void testCreateAccount14() {
		
		Customer cust=new Customer();
		cust=walletService.createAccount("Eric","9898989898",new BigDecimal(7000));
		assertEquals("9898989898", cust.getMobileNo());

		
		
	}
	@Test(expected=InvalidInputException.class)
	public void testShowBalance15() {
		Customer cust=new Customer();
	cust=walletService.showBalance("9898989898");

	}
	@Test(expected=InvalidInputException.class)
	public void testShowBalance16() {
		walletService.showBalance(null);
	}
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance17() {
		walletService.showBalance("9898989898");
	}
	
	@Test(expected=InvalidInputException.class)
	public void testShowBalance18() {
		walletService.showBalance("");
	}
	
	@Test
	public void testShowBalance19() {
		
		Customer cust=new Customer();
	cust=walletService.showBalance("9900112212");
	BigDecimal actual=cust.getWallet().getBalance();
	BigDecimal expected=new BigDecimal(9000);
	assertEquals(expected, actual);

	}
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer20() {
		walletService.fundTransfer(null, null,new BigDecimal(6000));
	}

	@Test
	public void testFundTransfer21() {
		Customer cust1 = walletService.fundTransfer("9900112212","9963242422",new BigDecimal(2000));
		BigDecimal actual=cust1.getWallet().getBalance();
		BigDecimal expected=new BigDecimal(7000);
		assertEquals(expected, actual);
	}
	@Test(expected=InvalidInputException.class)
	public void testFundTransfer22() {
		walletService.fundTransfer("", "", new BigDecimal(6000));
	}

	@Test(expected=InvalidInputException.class)
	public void testFundTransfer23() {
		walletService.fundTransfer("", "", new BigDecimal(0));
	}
	
	
@Test(expected=InvalidInputException.class)
public void testDeposit24()
{
	walletService.depositAmount("9898989", new BigDecimal(2000));
}
	
@Test
public void testDeposit25()
{
	Customer cust1 = walletService.depositAmount("9900112212", new BigDecimal(2000));
	BigDecimal actual=cust1.getWallet().getBalance();
	BigDecimal expected=new BigDecimal(11000);
	assertEquals(expected, actual);
}
@Test(expected=InvalidInputException.class)
public void testWithdraw26()
{
	walletService.withdrawAmount("9898989898", new BigDecimal(-2000));
}
	
@Test
public void testWithdraw27()
{
	Customer cust1 = walletService.withdrawAmount("9900112212", new BigDecimal(2000));
	BigDecimal actual=cust1.getWallet().getBalance();
	BigDecimal expected=new BigDecimal(7000);
	assertEquals(expected, actual);
}	
	

}
