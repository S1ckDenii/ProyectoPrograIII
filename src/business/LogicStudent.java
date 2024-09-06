package business;

import java.time.LocalDate;

import data.RechargeData;
import data.StudentData;
import domain.Recharge;
import domain.Student;

public class LogicStudent {

	public static boolean validateId(String id) {
		for (Student student : StudentData.getStudentList()) {
			if (id.equals(student.getId())) {
				return true;
			}
		}
		return false;
	}

	public static boolean validateAmountRange(Double amount) {

		boolean validation = false;

		if ((amount >= 1000) && (amount <= 10000)) {
			validation = true;
		}
		return validation;
	}

	public static boolean validateAmountRangeMy(Double amount) {

		return ((amount >= 1000) && (amount <= 10000)) ? true : false;

	}

	public static Recharge getRecharge(String id) {

		Recharge r = new Recharge();

		for (Recharge recharge : RechargeData.getRechargeList()) {

			if (id.equals(recharge.getId())) {
				r = recharge;
			}

		}

		return r;
	}

	public static Student getStudent(String id) {

		Student s = new Student();

		for (Student student : StudentData.getStudentList()) {

			if (id.equals(student.getId())) {
				s = student;
			}
		}

		return s;
	}
}
