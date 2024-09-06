package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.Recharge;

public class RechargeData {

	public static ArrayList<Recharge> rechargeList = new ArrayList();
	private static final String fileName = "studentRecharges.json";
	private static JsonUtils<Recharge> jsonUtils = new JsonUtils<>(fileName);

	public static List<Recharge> getRechargeList() {

		try {
			return jsonUtils.getElements(Recharge.class);

		} catch (IOException e) {
			// TODO: handle exception
		}
		return null;
	}

	public static String getRechargeStringFormat(Recharge recharge) {

		return "carne: " + recharge.getId() + "\nMonto: " + recharge.getAmount() + "\nFecha de recarga: "
				+ recharge.getRechargeDate();

	}

	public static boolean saveRecharge(Recharge recharge) {

		try {
			jsonUtils.saveElement(recharge);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteRecharge(int i, Recharge recharge) {

		try {
			jsonUtils.deleteElement(i, recharge);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean editRecharge(int i, Recharge recharge) {

		try {

			jsonUtils.deleteElement(i, recharge);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
}
