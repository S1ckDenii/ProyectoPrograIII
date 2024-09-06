package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import domain.Student;

public class StudentData {

	public static ArrayList<Student> petList = new ArrayList();

	private static final String fileName = "Student.json";
	private static JsonUtils<Student> jsonUtils = new JsonUtils<>(fileName);

	public static List<Student> getStudentList() {

		try {
			return jsonUtils.getElements(Student.class);

		} catch (IOException e) {
			// TODO: handle exception
		}
		return null;
	}

	public static String getStudentStringFormat(Student student) {

		return "Estudiante: " + student.getName().toUpperCase() + "\nGenero: "
				+ ((student.getGender() == 'M') ? "Masculino" : "Femenino") + "\nFecha Ingreso: "
				+ student.getDateIncome() + "\n\n";

	}

	public static boolean saveStudent(Student student) {

		try {
			jsonUtils.saveElement(student);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public static boolean deleteStudent(int i, Student student) {

		try {
			jsonUtils.deleteElement(i, student);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean editStudent(int i, Student student) {

		try {

			jsonUtils.editElement(student, i);

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

}
