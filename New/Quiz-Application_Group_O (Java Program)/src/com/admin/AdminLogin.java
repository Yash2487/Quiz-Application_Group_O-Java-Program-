package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.connection.ConnectionTest;
import com.dashboard.Dashboard;
import com.questionbank.*;

import CustomExceptions.InvalidInputException;
import CustomExceptions.UserNotFoundException;

public class AdminLogin {
	@SuppressWarnings("resource")
	public void adminDashboard() {
		QuestionFunctions questionFunctions=new Question();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter 1 - View Questions");
		System.out.println("Enter 2 - Add Questions");
		System.out.println("Enter 3 - View Students Details");
		System.out.println("Enter 4 - View Result");
		System.out.println("Enter 5 - Logout");
		System.out.println("Enter 6 - Quit");
		try {
		int opt= sc.nextInt();
		if (opt==1) {
			System.out.println("View Questions");
			questionFunctions.viewQuestion();
		}
		else if (opt==2) {
			System.out.println("Add Questions");
			questionFunctions.addQuestion();
		}
		else if (opt==3) {
			System.out.println("Student List");
			System.out.println(" To View Details Of All Student Enter 1");
			System.out.println(" To View Details Of Student By Student ID Enter 2");

				 int inp = sc.nextInt();
					 	if( inp==1) {
					 		StudentList.showStudentDetails();
					 	} else if( inp==2) {
					 		System.out.println(" Enter Student Id");
				 			int std_id= sc.nextInt();
				 			StudentList.showStudentDetails(std_id);
					 	}
					 	 else {
					 		 throw new InvalidInputException("Invalid Input");
			 	}
			 sc.close();
			 }
		else if (opt==4) {
			ViewStudentResult viewResult = new ViewStudentResult();
			System.out.println("View Result");
			System.out.println(" To View Result Of All Student - Enter 1");
			System.out.println(" To View Result By Student Id - Enter 2");
			 int y = sc.nextInt();
			 if( y==1) {
				 viewResult.showStudentResult();
			 } else if( y==2) {
				 System.out.println(" Enter Student Id");
				 int z= sc.nextInt();
				 viewResult.showStudentResult(z);
			 }
		}
		else if (opt==5) {
			Dashboard.mainDashboard();
		}
		else if (opt==6) {
			System.out.println("Thank You, Bye Bye");
			System.exit(0);
		}
		else {
			throw new InvalidInputException("Invalid Input");
		}
		}
		catch (Exception e) {
			sc.nextLine();
			System.out.println(e);
			adminDashboard();
		}
		sc.close();
		
	}
	@SuppressWarnings("resource")
	void wrongCredentials() throws SQLException {
		Scanner sc=new Scanner(System.in); 
		System.out.println("Enter 1 - Try Again");
		 System.out.println("Enter 2 - Home");
		 System.out.println("Enter 3 - Quit");
		 try {
		 int b=sc.nextInt();
		 if (b==1) {
			login();
		}
		 else if (b==2) {
			Dashboard.mainDashboard();
		}
		 else if (b==3) {
			 System.out.println("Thank You, Bye Bye");
			 System.exit(0);
		}
		 else {
			 throw new InvalidInputException("Invalid Input");
		}
	}
		 catch (Exception e) {
			 System.out.println(e);
			 wrongCredentials();
		}
		sc.close();
	}
	@SuppressWarnings("resource")
	public static void login() throws SQLException {
		AdminLogin obj=new AdminLogin();
		Scanner sc=new Scanner(System.in);
			System.out.println("Enter Username");
			String username =sc.next();
			System.out.println("Enter Password");
			String pass=sc.next();
			Connection con=ConnectionTest.getConnection();
			 PreparedStatement ps=con.prepareStatement("select username, password from admin");
//			 ps.setString(1, username);
//			 ps.setString(2, pass);
			 ResultSet rs=ps.executeQuery();
			 try {
			 if(rs.next()==false) {
				 throw new UserNotFoundException("User Not Found");
			 }
			 else if (username.equals(rs.getString(1)) && pass.equals(rs.getString(2))) {
						System.out.println("Login Successful");
						obj.adminDashboard();
					}
					else {
						System.out.println("Invalid Password, Enter Again");
						obj.wrongCredentials();
					}		
					 sc.close();
			} 
			 catch (UserNotFoundException e) {
				System.out.println(e);
				new AdminLogin().wrongCredentials();
			}
			 catch (Exception e) {
				 System.out.println(e);
				 new AdminLogin().wrongCredentials();
			}
		}	 
	}		
	
