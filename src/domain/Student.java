package domain;

import java.time.LocalDate;

public class Student {

	private String id;
	private String name;
	private String email;
	private int phone;
	private boolean isActive;
	private LocalDate dateIncome;
	private char gender;
	private double availableMoney;

	public Student() {
	}

	public Student(String id, String name, String email, int phone, boolean isActive, LocalDate dateIncome, char gender,
			double availableMoney) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.isActive = isActive;
		this.dateIncome = dateIncome;
		this.gender = gender;
		this.availableMoney = availableMoney;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDate getDateIncome() {
		return dateIncome;
	}

	public void setDateIncome(LocalDate dateIncome) {
		this.dateIncome = dateIncome;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public double getAvailableMoney() {
		return availableMoney;
	}

	public void setAvailableMoney(double availableMoney) {
		this.availableMoney = availableMoney;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", isActive="
				+ isActive + ", dateIncome=" + dateIncome + ", gender=" + gender + ", availableMoney=" + availableMoney
				+ "]";
	}

}
