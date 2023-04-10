package com.dashboard;

import java.sql.SQLException;
import java.util.Scanner;
import com.admin.AdminLogin;
import com.student.Student;
import com.student.StudentRegistration;

import CustomExceptions.InvalidInputException;

public class Dashboard {
	void checkInput(int userInput) throws SQLException {
		if (userInput == 1) {
			StudentRegistration.getStudentInput();
			System.out.println("Registraion sucessfull");
		} else if (userInput == 2) {
			Student.studentLogin();
		} else if (userInput == 3) {
			AdminLogin.login();
		} else if (userInput == 4) {
			System.out.println("Thank You, Bye Bye");
			System.exit(0);
		} else {
			throw new InvalidInputException("Invalid Input Try Again");
		}
	}

	public static void mainDashboard() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome To Quiz Based Application");
		System.out.println("----------User Operations----------");
		System.out.println("For Student Registration-Enter 1");
		System.out.println("For Student Login-Enter 2");
//		System.out.println("To Dispaly List Of Questions- Enter 3");
//		System.out.println("To Store Result Into Database- Enter 4");
//		System.out.println("To Display Quiz Result- Enter 5");
		System.out.println("----------Admin Operation----------");
		System.out.println("For Admin Login-Enter 3");
//		System.out.println("Display All Student Score as per ascending order- Enter 7");
//		System.out.println("Fetch Student Score by using ID- Enter 8");
//		System.out.println("Add Questions With Four Options- Enter 9");
		System.out.println("Quit-Enter 4");
		try {
			int a = sc.nextInt();
			new Dashboard().checkInput(a);
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Invalid Input");
			mainDashboard();
		}
		sc.close();
	}

	public static void main(String[] args) {
		mainDashboard();
	}

}
