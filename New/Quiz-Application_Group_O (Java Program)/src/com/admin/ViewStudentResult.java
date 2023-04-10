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

public class ViewStudentResult {
	ViewStudentResult() {
	}

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;
	}

	int std_id;
	String f_name;
	String l_name;
	int total_que;
	int c_ans;
	int marks;

	public ViewStudentResult(int id, String fname, String lname, int que, int ca, int marks) {
		this.std_id = id;
		this.f_name = fname;
		this.l_name = lname;
		this.total_que = que;
		this.c_ans = ca;
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "ViewStudentResult [std_id=" + std_id + ", f_name=" + f_name + ", l_name=" + l_name + ", total_que="
				+ total_que + ", c_ans=" + c_ans + ", marks=" + marks + "]";
	}

	public void showStudentResult() {
		ArrayList<ViewStudentResult> result = new ArrayList<ViewStudentResult>();
		try {
			PreparedStatement ps = connection().prepareStatement(
					"select student.std_id,first_name,last_name,total_question,correct_answer,marks_obtained from student inner join result on result.std_id=student.std_id");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new ViewStudentResult(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6)));
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
			System.out.println("To view result of student by ID - Enter 1");
			System.out.println("For Home - Enter 2");
			System.out.println("Logout - Enter 3");
			System.out.println("Quit - Enter 4");
			try {
				int inp = sc.nextInt();
				if (inp == 1) {
					cond = 0;
					System.out.println("Enter Student Id");
					try {
						int sid = sc.nextInt();
						showStudentResult(sid);
					} catch (Exception e) {
						System.out.println(e);
					}
				} else if (inp == 2) {
					cond = 0;
					new AdminLogin().adminDashboard();
				} else if (inp == 3) {
					cond = 0;
					Dashboard.mainDashboard();
				} else if (inp == 4) {
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
			sc.close();
		}
	}

	public void showStudentResult(int stuId) {
		ArrayList<ViewStudentResult> result = new ArrayList<ViewStudentResult>();
		try {
			PreparedStatement ps = connection().prepareStatement(
					"select student.std_id,first_name,last_name,total_question,correct_answers,marks_obtained from student inner join result on result.std_id=student.std_id where student.std_id=?");
			ps.setInt(1, stuId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result.add(new ViewStudentResult(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4),
						rs.getInt(5), rs.getInt(6)));
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
			System.out.println("For Another Student - Enter 1");
			System.out.println("For All Students - Enter 2");
			System.out.println("For Home - Enter 3");
			System.out.println("Logout - Enter 4");
			System.out.println("Quit - Enter 5");
			try {
				int inp = sc.nextInt();
				if (inp == 1) {
					cond = 0;
					System.out.println("Enter Student Id");
					try {
						int sid = sc.nextInt();
						showStudentResult(sid);
					} catch (Exception e) {
						System.out.println(e);
					}
				} else if (inp == 2) {
					cond = 0;
					showStudentResult();
				} else if (inp == 3) {
					cond = 0;
					new AdminLogin().adminDashboard();
				} else if (inp == 4) {
					cond = 0;
					Dashboard.mainDashboard();
				} else if (inp == 5) {
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
			sc.close();
		}
	}
}
