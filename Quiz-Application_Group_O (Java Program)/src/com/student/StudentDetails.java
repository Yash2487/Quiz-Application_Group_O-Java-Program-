package com.student;

public class StudentDetails {
	private static int std_id;
	private static String firstName;
	private static String lastName;
	private static String username;
	private static String password;
	private static String city;
	private static String email;
	private static long mobileNo;

	public static int getStd_id() {
		return std_id;
	}

	public static void setStd_id(int std_id) {
		StudentDetails.std_id = std_id;
	}

	public static String getFirstName() {
		return firstName;
	}

	public static void setFirstName(String firstName) {
		StudentDetails.firstName = firstName;
	}

	public static String getLastName() {
		return lastName;
	}

	public static void setLastName(String lastName) {
		StudentDetails.lastName = lastName;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		StudentDetails.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		StudentDetails.password = password;
	}

	public static String getCity() {
		return city;
	}

	public static void setCity(String city) {
		StudentDetails.city = city;
	}

	public static String getEmail() {
		return email;
	}

	public static void setEmail(String email) {
		StudentDetails.email = email;
	}

	public static long getMobileNo() {
		return mobileNo;
	}

	public static void setMobileNo(long mobileNo) {
		StudentDetails.mobileNo = mobileNo;
	}

}
