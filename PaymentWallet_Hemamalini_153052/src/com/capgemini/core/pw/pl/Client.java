package com.capgemini.core.pw.pl;

import java.math.BigDecimal;
import java.util.Scanner;

import com.capgemini.core.pw.beans.Customer;
import com.capgemini.core.pw.exception.InsufficientBalanceException;
import com.capgemini.core.pw.exception.InvalidInputException;
import com.capgemini.core.pw.service.WalletService;
import com.capgemini.core.pw.service.WalletServiceImpl;

public class Client 
{

	private WalletService walletService ;
	
	public Client() {
		walletService = new WalletServiceImpl() ;
	}

	public void menu()
	{
		Customer customer = new Customer() ;
		
		System.out.println("Payment Wallet");
		System.out.println("1) Create Account");
		System.out.println("2) Show Balance");
		System.out.println("3) Fund Transfer");
		System.out.println("4) Deposit Amount");
		System.out.println("5) Withdraw Amount");
		System.out.println("0) Exit");
		
		Scanner console = new Scanner(System.in) ;
		
		System.out.println("Enter the choice : ");
		int choice = console.nextInt() ;
		
		switch(choice)
		{
		case 1 :
			//Create account
			System.out.println("Enter the customer name : ");
			String name = console.next() ;
			System.out.println("Enter the mobile number : ");
			String mobile = console.next() ;
			System.out.println("Enter the balance : ");
			BigDecimal amount=console.nextBigDecimal() ;
			try
			{
				customer = walletService.createAccount(name, mobile, amount) ;
				System.out.println("Account created successfully");
			}
			catch(InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			catch(InsufficientBalanceException e)
			{
				System.out.println(e.getMessage());
			}
			
			
			
			break ;
			
		case 2 :
			//show balance
			System.out.println("Enter the mobile number : ");
			mobile = console.next() ;
			try
			{
				customer = walletService.showBalance(mobile) ;
				System.out.println("Balance : "+customer.getWallet().getBalance());
			}
			catch(InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			catch(InsufficientBalanceException e)
			{
				System.out.println(e.getMessage());
			}
			
			
			break ;
			
		case 3:
			//fund transfer
			System.out.println("Enter the source mobile number : ");
			String sourceMobileNo = console.next() ;
			
			System.out.println("Enter the target mobile number : ");
			String targetMobileNo = console.next() ;
			
			System.out.println("Enter the amount to be transfered : ");
			amount = console.nextBigDecimal() ;
			try
			{
				customer = walletService.fundTransfer(sourceMobileNo, targetMobileNo, amount) ;
				System.out.println("Fund transferred successfully");
				
				System.out.println("Balance : "+customer.getWallet().getBalance());
			}
			catch(InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			catch(InsufficientBalanceException e)
			{
				System.out.println(e.getMessage());
			}
			
			
			break ;
			
		case 4 :
			//deposit
			System.out.println("Enter the mobile number : ");
			mobile = console.next() ;
			
			System.out.println("Enter the amount to be deposited : ");
			amount = console.nextBigDecimal() ;
			try
			{
				customer = walletService.depositAmount(mobile, amount) ;
				System.out.println("Balance : "+customer.getWallet().getBalance());
			}
			catch(InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			catch(InsufficientBalanceException e)
			{
				System.out.println(e.getMessage());
			}
			
			
			break ;
			
		case 5 :
			//withdraw 
			System.out.println("Enter the mobile number : ");
			mobile = console.next() ;
			
			System.out.println("Enter the amount to be withdrawed : ");
			amount = console.nextBigDecimal() ;
			try
			{
				customer = walletService.withdrawAmount(mobile, amount) ;
				System.out.println("Balance : "+customer.getWallet().getBalance());
			}
			catch(InvalidInputException e)
			{
				System.out.println(e.getMessage());
			}
			catch(InsufficientBalanceException e)
			{
				System.out.println(e.getMessage());
			}
			
		
			break ;
			
		case 0 :
			System.out.println("Exit....");
			System.exit(0);
			
			break ;
			
		default :
			System.out.println("Invalid choice");
			
			break ;
		}
		
	}
	
	public static void main( String[] args )
	{
	    Client client = new Client() ;
	    
	    while(true)
	    	client.menu();
	    
	}
}
