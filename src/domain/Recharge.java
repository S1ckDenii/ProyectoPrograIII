package domain;

import java.time.LocalDate;

public class Recharge {

	private String id;
	private double amount;
	private LocalDate rechargeDate;

	public Recharge() {

	}

	public Recharge(String id, double amount, LocalDate rechargeDate) {
		this.id = id;
		this.amount = amount;
		this.rechargeDate = rechargeDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDate getRechargeDate() {
		return rechargeDate;
	}

	public void setRechargeDate(LocalDate rechargeDate) {
		this.rechargeDate = rechargeDate;
	}

	@Override
	public String toString() {
		return "Recharge [id=" + id + ", amount=" + amount + ", rechargeDate=" + rechargeDate + "]";
	}

}
