package com.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.connection.ConnectionTest;
import com.dashboard.Dashboard;

import CustomExceptions.InvalidInputException;
import CustomExceptions.UserNotFoundException;

public class Student {

	static Scanner sc = new Scanner(System.in);

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;

	}

	@SuppressWarnings("static-access")
	public static void studentLogin() throws SQLException {
		StudentDetails studentDetails = new StudentDetails();
		Student obj = new Student();

		// take username & password input from user to check with our DB
		System.out.println("Enter The UserName");
		String userName = sc.next();
		System.out.println("Enter Password");
		String password = sc.next();
		PreparedStatement ps = connection()
				.prepareStatement(" select * from student where user_name = ? and password = ?");
		ps.setString(1, userName);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();// rs having user_name, password from DB
		try {
			if (rs.next() == false) {
				throw new UserNotFoundException("User Not Found");
			} else {
				studentDetails.setStd_id(rs.getInt(1));
				studentDetails.setFirstName(rs.getString(2));
				studentDetails.setLastName(rs.getString(3));
				studentDetails.setUsername(rs.getString(4));
				studentDetails.setPassword(rs.getString(5));
				studentDetails.setCity(rs.getString(6));
				studentDetails.setEmail(rs.getString(7));
				studentDetails.setMobileNo(rs.getLong(8));

			}
			if (userName.equals(studentDetails.getUsername()) && password.equals(studentDetails.getPassword())) {
				System.out.println("Login Sucessful");
				obj.studentDashboard();
			} else {
				System.out.println("Invalid Credentials");
				obj.wrongCredentials();
			}
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			Dashboard.mainDashboard();
		} catch (Exception e) {
			System.out.println("Invalid Input..Try Again");
			Dashboard.mainDashboard();
		}
	}

	@SuppressWarnings("static-access")
	void studentDashboard() {

		System.out.println(" Enter 1 - Start Quiz");
		System.out.println(" Enter 2 - Store Quiz Result");
		System.out.println(" Enter 3 - View Result");
		System.out.println(" Enter 4 - LogOut");

		try {
			int a = sc.nextInt();
			if (a == 1) {
				new StudentFunctions().startQuiz();
			} else if (a == 2) {
				new StudentFunctions().storeQuizResult();
			} else if (a == 3) {
				new ViewResultStudent().showStudentResult(new StudentDetails().getStd_id());
			} else if (a == 4) {
				Dashboard.mainDashboard();
			} else {
				throw new InvalidInputException("Invalid Input, Try Again");
			}
		} catch (Exception e) {
			sc.nextLine();
			System.out.println(e);
			studentDashboard();
		}
	}

	void wrongCredentials() {

		System.out.println("Enter 1 - Try Again");
		System.out.println("Enter 2 - Home");
		System.out.println("Enter 3 - Quit");
		try {
			int b = sc.nextInt();
			if (b == 1) {
				studentLogin();
			} else if (b == 2) {
				Dashboard.mainDashboard();
			} else if (b == 3) {
				System.out.println("Thank You, Bye Bye");
				System.exit(0);
			} else {
				throw new InvalidInputException("Invalid Input");
			}
		} catch (Exception e) {
			sc.nextLine();
			new Student().wrongCredentials();
		}
		sc.close();
	}
}
