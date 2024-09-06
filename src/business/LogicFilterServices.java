package business;

public class LogicFilterServices {

	public LogicFilterServices() {
	}

	public static boolean validateMondayDayAndBreakfast(String day, String schedule) {
		return (day.equals("Lunes") && (schedule.equals("Desayuno"))) ? true : false;
	}

	public static boolean validateMondayDayAndLunch(String day, String schedule) {
		return (day.equals("Lunes") && (schedule.equals("Almuerzo"))) ? true : false;
	}

	public static boolean validateTuesdayDayAndBreakfast(String day, String schedule) {
		return (day.equals("Martes") && (schedule.equals("Desayuno"))) ? true : false;
	}

	public static boolean validateTuesdayDayAndLunch(String day, String schedule) {
		return (day.equals("Martes") && (schedule.equals("Almuerzo"))) ? true : false;
	}

	public static boolean validateWednesdayDayAndBreakfast(String day, String schedule) {
		return (day.equals("Miercoles") && (schedule.equals("Desayuno"))) ? true : false;
	}

	public static boolean validateWednesdayDayAndLunch(String day, String schedule) {
		return (day.equals("Miercoles") && (schedule.equals("Almuerzo"))) ? true : false;
	}

	public static boolean validateThursdayDayAndBreakfast(String day, String schedule) {
		return (day.equals("Jueves") && (schedule.equals("Desayuno"))) ? true : false;
	}

	public static boolean validateThursdayDayAndLunch(String day, String schedule) {
		return (day.equals("Jueves") && (schedule.equals("Almuerzo"))) ? true : false;
	}

	public static boolean validateFridayDayAndBreakfast(String day, String schedule) {
		return (day.equals("Viernes") && (schedule.equals("Desayuno"))) ? true : false;
	}

	public static boolean validateFridayDayAndLunch(String day, String schedule) {
		return (day.equals("Viernes") && (schedule.equals("Almuerzo"))) ? true : false;
	}

}
