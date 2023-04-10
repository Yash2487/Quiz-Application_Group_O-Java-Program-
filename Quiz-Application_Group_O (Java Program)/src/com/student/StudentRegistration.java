package com.student;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.connection.ConnectionTest;
import com.dashboard.Dashboard;

import CustomExceptions.UserNotFoundException;

public class StudentRegistration {
	static StudentDetails sd = new StudentDetails();

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;

	}

	@SuppressWarnings("static-access")
	public static void getStudentInput() {
		System.out.println("Hi, Welcome..");
		System.out.println("Please Register To Start Quiz....");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your first name");
		String firstName = sc.nextLine();
		System.out.println("Enter your Last name");
		String lastName = sc.nextLine();
		System.out.println("Enter The Username");
		String userName = sc.nextLine();
		System.out.println("Enter your Password");
		String password = sc.nextLine();
		System.out.println("Enter your city");
		String city = sc.next();
		System.out.println("Enter your Email ID");
		String email = sc.next();
		System.out.println("Enter your Mobile Number");
		Long moNo = sc.nextLong();

		sd.setFirstName(firstName);
		sd.setLastName(lastName);
		sd.setUsername(userName);
		sd.setPassword(password);
		sd.setCity(city);
		sd.setEmail(email);
		sd.setMobileNo(moNo);
		insertStudentData();
		System.out.println();
		sc.close();
	}

	@SuppressWarnings("static-access")
	static void insertStudentData() {
		try {
			// check mobileNumber entered by student, if its already in DB then
			// return to mainDashboard & throw User Already Registered exception
			PreparedStatement pStatement = connection().prepareStatement(
					"select first_name, last_Name, user_name, password, city, emailID, mobileNumber from student where first_name=? and last_Name=? and user_name=? and password=? and city=? and emailID=? and mobileNumber=?");
			pStatement.setString(1, sd.getFirstName());
			pStatement.setString(2, sd.getLastName());
			pStatement.setString(3, sd.getUsername());
			pStatement.setString(4, sd.getPassword());
			pStatement.setString(5, sd.getCity());
			pStatement.setString(6, sd.getEmail());
			pStatement.setLong(7, sd.getMobileNo());
			ResultSet resultset = pStatement.executeQuery();
			if (resultset.next() == true) {
				throw new UserNotFoundException("User Already Registered");
			} else {

				PreparedStatement ps = connection().prepareStatement(
						"insert into student(first_name, last_Name, user_name, password, city, emailID, mobileNumber)values(?,?,?,?,?,?,?)");
				ps.setString(1, sd.getFirstName());
				ps.setString(2, sd.getLastName());
				ps.setString(3, sd.getUsername());
				ps.setString(4, sd.getPassword());
				ps.setString(5, sd.getCity());
				ps.setString(6, sd.getEmail());
				ps.setLong(7, sd.getMobileNo());

				ps.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			Dashboard.mainDashboard();
		}
		System.out.println("Registration Successful");
		Dashboard.mainDashboard();
	}
}
