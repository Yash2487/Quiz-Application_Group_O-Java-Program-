package com.student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.connection.ConnectionTest;
import com.dashboard.Dashboard;

import CustomExceptions.InvalidInputException;

public class ViewResultStudent {
	ViewResultStudent() {
	}

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;
	}

	int std_id;
	String f_name;
	String l_name;
	int marks;
	
	public ViewResultStudent(int id, String fname, String lname, int marks) {
		this.std_id = id;
		this.f_name = fname;
		this.l_name = lname;
		this.marks = marks;
	}
	
	@Override
	public String toString() {
		return "ViewResultStudent [std_id=" + std_id + ", f_name=" + f_name + ", l_name=" + l_name + ", marks=" + marks
				+ "]";
	}

	public void showStudentResult(int stuId) {
		ArrayList<ViewResultStudent> result = new ArrayList<ViewResultStudent>();
		try {
			PreparedStatement ps = connection().prepareStatement(
					"select student.std_id,first_name,last_name,marks_obtained from student inner join result on result.std_id=student.std_id where student.std_id=?");
			ps.setInt(1, stuId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new ViewResultStudent(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Object res : result) {
			System.out.println(res);
		}
		int cond = 1;
		Scanner sc = new Scanner(System.in);
		while (cond == 1) {
			System.out.println("For Home - Enter 1");
			System.out.println("Logout - Enter 2");
			System.out.println("Quit - Enter 3");
			try {
				int inp = sc.nextInt();
				if (inp == 1) {
					cond = 0;
					new Student().studentDashboard();
				} else if (inp == 2) {
					cond = 0;
					Dashboard.mainDashboard();
				} else if (inp == 3) {
					cond = 0;
					System.out.println("Thank You, Bye Bye");
					System.exit(0);
				} else {
					throw new InvalidInputException("Invalid Input, Try Again");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(e);
			}
		}
		sc.close();
	}

	



}
