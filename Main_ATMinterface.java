package com.eduATM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class Main_ATMinterface {
	

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
    ATM_operations.userExist();
    
	}
	public static void dis_interface() throws SQLException {
	 Scanner sc = new Scanner(System.in);
	int ch;
    System.out.println("\n----ROYAL BANK OF INDIA----");
	System.out.println("***** WELCOME *****");
	System.out.println("Enter your Choice ");
	System.out.println("1.Check Balance");
	System.out.println("2.Add Amount");
	System.out.println("3.Withdraw Amount");
	System.out.println("4.Exit / Return ");
	ch=sc.nextInt();
	
	
	 switch (ch) {
	 case 1: // Check Balance 
		   // ATM_operations.display();

		   ATM_operations.check_Balance(); 
			
			break;
			
		case 2:// Add Amount 
			ATM_operations.add_Amount(); 
			break;
			
		case 3: // Withdrwa Amount
			ATM_operations.withdraw_Amount();  
			break;
		
		
		case 4:// exit here
			System.out.println("Thank you ....!");
			System.exit(0);
			
			break;
		default :
			System.out.println("Invalid Choise ....! Enter valid choise ");
			dis_interface();
		}
	}
}

	


