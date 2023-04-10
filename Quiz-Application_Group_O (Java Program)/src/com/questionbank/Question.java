package com.questionbank;

import java.sql.Connection;	
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.admin.AdminLogin;
import com.connection.ConnectionTest;
import com.dashboard.Dashboard;
import CustomExceptions.InvalidInputException;

public class Question implements QuestionFunctions {
	static Scanner sc = new Scanner(System.in);

	static Connection connection() throws SQLException {
		Connection con = ConnectionTest.getConnection();
		return con;

	}

	@Override
	public void addQuestion() {
		sc.nextLine();
		System.out.println("Enter Question");
		String que = sc.nextLine();
		System.out.println("Enter Option 1");
		String o1 = sc.nextLine();
		System.out.println("Enter Option 2");
		String o2 = sc.nextLine();
		System.out.println("Enter Option 3");
		String o3 = sc.nextLine();
		System.out.println("Enter Option 4");
		String o4 = sc.nextLine();
		System.out.println("Enter Correct Answer");
		String ca = sc.nextLine();
		try {
			PreparedStatement ps = connection().prepareStatement(
					"insert into question(question,opt1,opt2,opt3,opt4,correct_answer)values(?,?,?,?,?,?) ");

			ps.setString(1, que);
			ps.setString(2, o1);
			ps.setString(3, o2);
			ps.setString(4, o3);
			ps.setString(5, o4);
			ps.setString(6, ca);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int cond = 1;
		while (cond == 1) {
			try {
				System.out.println("To add more question Enter 1");
				System.out.println("To logout Enter 2");
				System.out.println("Admin home enter 3");
				int a = sc.nextInt();

				if (a == 1) {
					sc.nextLine();
					cond = 0;
					addQuestion();
				} else if (a == 2) {
					cond = 0;
					Dashboard.mainDashboard();
				} else if (a == 3) {
					cond = 0;
					new AdminLogin().adminDashboard();
				} else {
					throw new InvalidInputException("Invaild Input, Try Again");
				}
			} catch (Exception e) {
				sc.nextLine();
				System.out.println(e);
			}
		}
	}

	@Override
	public void deleteQuestion() {
		try {
			PreparedStatement ps = connection().prepareStatement(" delete from question");
			ps.execute();
			System.out.println("All Questions Deleted Sucessfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteQuestion(int qid) {
		try {
			PreparedStatement ps = connection().prepareStatement(" delete from question where qid =?");

			ps.setInt(1, qid);

			ps.execute();
			System.out.println("Question Deleted Sucessfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void modifyQuestion(int qid) {
		sc.nextLine();
		System.out.println("Enter Question");
		String que = sc.nextLine();
		System.out.println("Enter Option 1");
		String o1 = sc.nextLine();
		System.out.println("Enter Option 2");
		String o2 = sc.nextLine();
		System.out.println("Enter Option 3");
		String o3 = sc.nextLine();
		System.out.println("Enter Option 4");
		String o4 = sc.nextLine();
		System.out.println("Enter Correct Answer");
		String ca = sc.nextLine();

		try {
			PreparedStatement ps = connection().prepareStatement(
					"Update question set question=? ,opt1=?,opt2=?,opt3=?,opt4=?,correct_answer =? where qid=?");
			ps.setString(1, que);
			ps.setString(2, o1);
			ps.setString(3, o2);
			ps.setString(4, o3);
			ps.setString(5, o4);
			ps.setString(6, ca);
			ps.setInt(7, qid);
			ps.execute();
			System.out.println("Question Updated Succesfully");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void viewQuestion() {
		try {
			PreparedStatement ps = connection().prepareStatement("select * from question ");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int qid = rs.getInt(1);
				System.out.println("Question: " + rs.getString(2));
				System.out.println("Option 1: " + rs.getString(3));
				System.out.println("Option 2: " + rs.getString(4));
				System.out.println("Option 3: " + rs.getString(5));
				System.out.println("Option 4: " + rs.getString(6));
				System.out.println("Correct Answer: " + rs.getString(7));
				int cond = 1;
				while (cond == 1) {
					try {
						System.out.println("Enter 1 to Delete Question");
						System.out.println("Enter 2 to Update Question");
						System.out.println("Enter 3 for Next Question");
						System.out.println("Enter 4 to Delete All Questions");
						System.out.println("Enter 5 for Admin Home");
						System.out.println("Enter 6 for Logout");
						System.out.println("Enter 7 to Quit");
						int x = sc.nextInt();

						if (x == 1) {
							cond = 0;
							deleteQuestion(qid);
						} else if (x == 2) {
							cond = 0;
							modifyQuestion(qid);
						} else if (x == 3) {
							cond = 0;
							continue;
						} else if (x == 4) {
							cond = 0;
							deleteQuestion();
							new AdminLogin().adminDashboard();
						} else if (x == 5) {
							cond = 0;
							new AdminLogin().adminDashboard();
						} else if (x == 6) {
							cond = 0;
							Dashboard.mainDashboard();
						} else if (x == 7) {
							cond = 0;
							System.exit(0);
						} else {
							throw new InvalidInputException("Invalid Input, Try Again");
						}
					} catch (Exception e) {
						sc.nextLine();
						System.out.println(e);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new AdminLogin().adminDashboard();
	}
}
