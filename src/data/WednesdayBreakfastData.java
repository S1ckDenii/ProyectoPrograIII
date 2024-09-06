package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.Food;

public class WednesdayBreakfastData {

	public static ArrayList<Food> petList = new ArrayList();

	private static final String fileName = "wednesdayBreakfast.json";
	private static JsonUtils<Food> jsonUtils = new JsonUtils<>(fileName);

	public static List<Food> getWednesdayBreakfastList() {

		try {
			return jsonUtils.getElements(Food.class);

		} catch (IOException e) {
			// TODO: handle exception
		}
		return null;
	}

	public static String getFoodStringFormat(Food food) {

		return "Nombre del servicio: " + food.getDescription() + "\tPrecio del servicio: " + food.getPrice();

	}

	public static boolean saveWednesdayBreakfast(Food food) {

		try {
			jsonUtils.saveElement(food);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static boolean deleteWednesdayBreakfast(int i, Food food) {

		try {
			jsonUtils.deleteElement(i, food);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean editWednesdayBreakfast(int i, Food food) {

		try {

			jsonUtils.deleteElement(i, food);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
