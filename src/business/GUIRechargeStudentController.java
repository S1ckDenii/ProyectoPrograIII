package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

import data.RechargeData;
import data.StudentData;
import domain.Recharge;
import domain.Student;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class GUIRechargeStudentController {
	@FXML
	private TextField tfID;
	@FXML
	private TextField tfAmount;
	@FXML
	private DatePicker dpRechargeDate;
	@FXML
	private Button btnRecharge;
	@FXML
	private Button btnBack;
	@FXML
	private Label lblErrorMessage;

	// Event Listener on Button[#btnRecharge].onAction
	@FXML
	public boolean rechargeAmount(ActionEvent event) {

		String messageError = validateForm();
		if (!messageError.isEmpty()) {
			notifyAction(messageError);
			return false;
		}

		Double amount = Double.parseDouble(tfAmount.getText());
		String id = tfID.getText();

		if (!LogicStudent.validateId(id)) {
			notifyAction("El carne no coincide con ningun registro");
		}
		if (!LogicStudent.validateAmountRange(amount)) {
			notifyAction("El monto ingresado no se encuentra en el rango establecido: 1000 - 10 000");
		}

		if (LogicStudent.validateId(id) && LogicStudent.validateAmountRangeMy(amount)) {
			Recharge recharge = new Recharge(id, amount, dpRechargeDate.getValue());
			RechargeData.saveRecharge(recharge);
			cambiarSaldoDisponible();
			notifyAction("Recarga exitosa");
			clearForm();
		}

		return true;
	}

	// Event Listener on Button[#btnBack].onAction
	@FXML
	public void back(ActionEvent event) {
		closeWindows();
	}

	public void cambiarSaldoDisponible() {
		Student createStudent;
		double total = 0.0;
		int i = getPosicionDelEstudianteSeleccionado();
		for (Student student : StudentData.getStudentList()) {
			if (tfID.getText().equals(student.getId())) {
				total += student.getAvailableMoney() + Double.parseDouble(tfAmount.getText());
				createStudent = new Student(student.getId(), student.getName(), student.getEmail(), student.getPhone(),
						student.isActive(), student.getDateIncome(), student.getGender(), total);
				StudentData.editStudent(i, createStudent);
			}
		}
	}

	public int getPosicionDelEstudianteSeleccionado() {

		List<Student> studentsList = StudentData.getStudentList();
		int position = 0;
		for (int i = 0; i < studentsList.size(); i++) {
			Student student = studentsList.get(i);
			if (tfID.getText().equals(student.getId())) {
				position = i;
				break;
			}
		}
		return position;
	}

	public void clearForm() {

		tfID.setText("");
		tfAmount.setText("");
		dpRechargeDate.setValue(null);
	}

	private void notifyAction(String message) {
		lblErrorMessage.setText(message);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), e -> lblErrorMessage.setText("")));
		timeline.setCycleCount(1);
		timeline.play();
	}

	private String validateForm() {

		String messageError = "";

		if (tfID.getText().isEmpty()) {
			messageError += "El ID no puede estar vacio";
		}
		if (tfAmount.getText().isEmpty()) {
			messageError += "\tEl monto no puede estar vacio";
		}
		if (dpRechargeDate.getValue() == null) {
			messageError += "\tLa fecha no puede estar vacia";
		}

		return messageError;
	}

	public void closeWindows() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIMainMenu.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			Stage temp = (Stage) btnBack.getScene().getWindow();
			temp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
