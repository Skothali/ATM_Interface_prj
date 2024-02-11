package com.eduATM;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;




public class ATM_operations {

	static Connection conn ;
	private static Statement stmt;
	private static PreparedStatement pst;
	private static String sql;
	private static ResultSet rs;
	
	
	private static String name;
	private static int id,atm_pin ;
	private static Date exp_date;
	private static int updvar;
	private static float balance;
	
public static void userExist() throws SQLException {
	 System.out.println();
	  conn=ATM_Database_Connection.getConnection();
		int ch;
	    Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your ATM PIN ...");
		ch=sc.nextInt();
		int count=0;
		
		sql="select atm_pin from atmlogin";
	    pst=conn.prepareStatement(sql);	
		 rs=pst.executeQuery();
		
		 while (rs.next()) {
			 atm_pin=rs.getInt("atm_pin");
			 
			if ( atm_pin==ch) {
				String s="select name from atmlogin where atm_pin= ? ";
			
				pst=conn.prepareStatement(s);
				pst.setInt(1,ch);
				rs=pst.executeQuery();
					while(rs.next())	{
						name=rs.getString("name");
						System.out.println("Welcome "+name);
					}
					count++;
			}
		 }
		 if ( count>0) {
			 Main_ATMinterface.dis_interface();
		 }
		 else {
			 System.out.println("Invalid ATM PIN ..Enter valid pin ");
			 userExist();
			
		 }
			
		 }
	 
		 
public static void display() throws SQLException{
	
	conn=ATM_Database_Connection.getConnection();
	sql="select * from atmlogin";
    pst=conn.prepareStatement(sql);	
	 rs=pst.executeQuery();
	 while ( rs.next()) {
		 id=rs.getInt("id");
		 name=rs.getString("name");
		 balance=rs.getFloat("balance");
		 atm_pin=rs.getInt("atm_pin");
		  exp_date=rs.getDate("exp_date");
		  System.out.println(id+"\t"+name+"\t"+balance+"\t"+atm_pin+"\t"+exp_date);
	
}
}
	
	public static void check_Balance() throws SQLException {
		//display();
		Scanner sc=new Scanner(System.in);
		 System.out.println();
		  conn=ATM_Database_Connection.getConnection();
	
			sql="select balance from atmlogin where atm_pin=?";
		    pst=conn.prepareStatement(sql);	
		    pst.setInt(1,atm_pin);
			 rs=pst.executeQuery();
			 
			 while (rs.next()) {
				 balance=rs.getInt("balance");
				 
				 System.out.println("Your Balance is :"+balance+"Rs.");
			 } String sol;
			  System.out.println("Do you want to continue banking with us Enter Yes OR No");
			sol=sc.next();
			sol=sol.toLowerCase();
			  if (sol.equals("yes")) {
				  Main_ATMinterface.dis_interface();
			  }
			  else if (sol.equals("no")) {
				  System.exit(0);
			  }
			  else {
				  System.out.println("Enter Valid Choice");
			  }
	}
	
	public static void add_Amount() throws SQLException {
		System.out.println();
		  conn=ATM_Database_Connection.getConnection();
	
		  Scanner sc = new Scanner (System.in);
		  System.out.println("Enter the Amount to Add :");
		  int ch = sc.nextInt();
		  
			sql="select * from atmlogin where atm_pin=?";
		    pst=conn.prepareStatement(sql);	
		    pst.setInt(1,atm_pin);
			 rs=pst.executeQuery();
			 
			  if(rs.next()) {
				 
				  balance=rs.getInt("balance");
				    balance+=ch;
				  String add="update atmlogin set balance=? where atm_pin=?";
				    pst=conn.prepareStatement(add);	
				    pst.setFloat(1,balance);
				    pst.setInt(2, atm_pin);
				   pst.executeUpdate();
				  
				   System.out.println("Your Balance is :"+balance+"Rs.");
				   
				   System.out.println(" \nDO YOU WANT RECIEPT PRESS YES else NO ");
				   String str=sc.next();
				   str=str.toLowerCase();
				  
				   if (str.equals("yes")){
					   print_reciept(str);
				   }
				   else {
					   System.out.println("Thank You ...\nVisit again");
					  
				   } }
			  
			  
			   
        }
	
	public static void withdraw_Amount() throws SQLException {
		System.out.println();
		  conn=ATM_Database_Connection.getConnection();
	
		  Scanner sc = new Scanner (System.in);
		  System.out.println("Enter the Amount to Withdraw :");
		  int ch = sc.nextInt();
		  
			sql="select * from atmlogin where atm_pin=?";
		    pst=conn.prepareStatement(sql);	
		    pst.setInt(1,atm_pin);
			 rs=pst.executeQuery();
			 
			  if(rs.next()) {
				  balance=rs.getInt("balance");
				    balance-=ch;
				  String add="update atmlogin set balance=? where atm_pin=?";
				    pst=conn.prepareStatement(add);	
				    pst.setFloat(1,balance);
				    pst.setInt(2, atm_pin);
				   pst.executeUpdate();
		
				 System.out.println("Your Balance is :"+balance+"Rs.");
				 System.out.println(" \nDO YOU WANT RECIEPT PRESS YES else NO ");
				   String str=sc.next();
				   str=str.toLowerCase();
				  
				   if (str.equals("yes")){
					   print_reciept(str);
				   }
				   else {
					   System.out.println("Thank You ...\nVisit again");
					   System.exit(0);
				   }
					  
					   
				   }
				 }
			  
	
	public static void print_reciept(String str) throws SQLException {
      Scanner sc = new Scanner (System.in);
		
		   DateTimeFormatter dft= DateTimeFormatter.ofPattern("yyyy/MM/dd/HH:mm:ss");
		   LocalDateTime now =LocalDateTime.now();
		   if (str.equals("yes")){
			   System.out.println("--------------------------");
			  System.out.println("*** ROYAL BANK OF INDIA ***");
			  System.out.println("\n*** CASH Reciept ***");
			  
			   System.out.println(dft.format(now));
			 
			   System.out.println("Remaining Balance:"+balance);
			   System.out.println("--------------------------");
			   System.out.println("--------------------------");
			   System.out.println("Thank You For Banking with us\nHave a Good Day...");
			   
			   String sol;
				  System.out.println("Do you want to continue banking with us Enter Yes OR No");
				sol=sc.next();
				sol=sol.toLowerCase();
				  if (sol.equals("yes")) {
					  Main_ATMinterface.dis_interface();
				  }
				  else if (sol.equals("no")) {
					  System.exit(0);
				  }
				  else {
					  System.out.println("Enter Valid Choice");
				  }
	
}
	}
}
