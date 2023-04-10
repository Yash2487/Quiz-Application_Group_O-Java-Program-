package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.connection.ConnectionTest;
import com.dashboard.Dashboard;

import CustomExceptions.InvalidInputException;

public class StudentList {

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;
	}

	int std_id;
	String f_name;
	String l_name;
	String username;
	String password;
	String city;
	String email;
	long mobile;

	public StudentList(int a, String b, String c, String d, String e, String f, String g, long h) {
		this.std_id = a;
		this.f_name = b;
		this.l_name = c;
		this.username = d;
		this.password = e;
		this.city = f;
		this.email = g;
		this.mobile = h;

	}

	@Override
	public String toString() {
		return "StudentList [std_id=" + std_id + ", f_name=" + f_name + ", l_name=" + l_name + ", username=" + username
				+ ", password=" + password + ", city=" + city + ", email=" + email + ", mobile=" + mobile + "]";
	}

	// details of all students
	public static void showStudentDetails() throws SQLException {
		ArrayList<StudentList> studentList = new ArrayList<StudentList>();
		PreparedStatement ps = connection().prepareStatement("select * from student");
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int std_id = rs.getInt(1);
			String f_name = rs.getString(2);
			String l_name = rs.getString(3);
			String username = rs.getString(4);
			String password = rs.getString(5);
			String city = rs.getString(6);
			String email = rs.getString(7);
			long mobile = rs.getLong(8);

			studentList.add(new StudentList(std_id, f_name, l_name, username, password, city, email, mobile));
		}
		System.out.println(
				"             StudentID  First Name    Last Name     UserName            Password             City           EmailID                        MobileNum");
		for (Object a : studentList) {
			System.out.println(a);
		}
		System.out.println("For student details by ID: Enter-1");
		System.out.println("For Admin Home: Enter-2");
		System.out.println("Logout: Enter-3");
		System.out.println("Quit: Enter-4");
		Scanner scanner = new Scanner(System.in);

		boolean con = true;
		while (con == true) {
			int opt = scanner.nextInt();
			try {
				if (opt == 1) {
					con = false;
					System.out.println("Enter Student Id");
					int sid = scanner.nextInt();
					showStudentDetails(sid);
				} else if (opt == 2) {
					con = false;
					new AdminLogin().adminDashboard();
				} else if (opt == 3) {
					con = false;
					Dashboard.mainDashboard();
				} else if (opt == 4) {
					con = false;
					System.out.println("Thank You, Bye Bye");
					System.exit(0);
				} else {
					con = true;
					throw new InvalidInputException("Invalid Input, Try Again");
				}
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println(e);
			}
		}
		scanner.close();
	}

	// details of single student
	public static void showStudentDetails(int std_id) throws SQLException {
		ArrayList<StudentList> studentList = new ArrayList<StudentList>();
		PreparedStatement ps = connection().prepareStatement("select * from student where std_id=?");
		ps.setInt(1, std_id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int std_id1 = rs.getInt(1);
			String f_name = rs.getString(2);
			String l_name = rs.getString(3);
			String username = rs.getString(4);
			String password = rs.getString(5);
			String city = rs.getString(6);
			String email = rs.getString(7);
			long mobile = rs.getLong(8);

			studentList.add(new StudentList(std_id1, f_name, l_name, username, password, city, email, mobile));
		}
		System.out.println("             StudentID    First Name    Last Name    UserName    Mobile       Email        City");
		for (Object a : studentList) {
			System.out.println(a);
		}

		Scanner scanner = new Scanner(System.in);
		boolean cond = true;
		while (cond == true) {
			try {
				System.out.println("For another student: Enter-1");
				System.out.println("For all students: Enter-2");
				System.out.println("For Admin Home: Enter-3");
				System.out.println("Logout: Enter-4");
				System.out.println("Quit: Enter-5");
				int opt = scanner.nextInt();

				if (opt == 1) {
					cond = false;
					System.out.println("Enter Student Id");
					int sid = scanner.nextInt();
					showStudentDetails(sid);
				} else if (opt == 2) {
					cond = false;
					showStudentDetails();
				} else if (opt == 3) {
					cond = false;
					new AdminLogin().adminDashboard();
				} else if (opt == 4) {
					cond = false;
					Dashboard.mainDashboard();
				} else if (opt == 5) {
					cond = false;
					System.out.println("Thank You, Bye Bye");
					System.exit(0);
				} else {
					cond = true;
					throw new InvalidInputException("Invalid Input, Try Again");
				}
			} catch (Exception e) {
				scanner.nextLine();
				System.out.println(e);
			}
		}
		scanner.close();
	}

	void fetchDetails() {

	}
}
