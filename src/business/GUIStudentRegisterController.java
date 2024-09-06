package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

import javax.swing.JOptionPane;

import data.StudentData;
import domain.Student;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.RadioButton;

import javafx.scene.control.DatePicker;

public class GUIStudentRegisterController {
	@FXML
	private TextField tFId;
	@FXML
	private TextField tFName;
	@FXML
	private TextField tFEmail;
	@FXML
	private TextField tFPhone;
	@FXML
	private RadioButton rBIsActiveYes;
	@FXML
	private ToggleGroup isActive;
	@FXML
	private RadioButton rBIsActiveNo;
	@FXML
	private DatePicker dPDateIncome;
	@FXML
	private ComboBox cBGender;
	@FXML
	private TextField tFAvailableMoney;
	@FXML
	private Label lblErrorMessage;
	@FXML
	private Button bRegister;
	@FXML
	private Button bVolver;

	@FXML
	public void initialize() {

		cBGender.getItems().addAll("Masculino", "Femenino");
		cBGender.getSelectionModel().selectFirst();
	}

	@FXML
	public void bVolverAction() {
		closeWindows();
	}

	// Event Listener on Button[#bRegister].onAction
	@FXML
	public boolean bRegistrarAction(ActionEvent event) {

		String messageError = validateForm();
		if (!messageError.isEmpty()) {
			notifyAction(messageError);
			return false;
		}

		boolean isActive = (rBIsActiveYes.isSelected()) ? true : false;
		char gender = (cBGender.getSelectionModel().getSelectedIndex() == 0) ? 'M' : 'F';
		boolean validation = LogicStudent.validateId(tFId.getText());
		Student student = new Student(tFId.getText(), tFName.getText(), tFEmail.getText(),
				Integer.parseInt(tFPhone.getText()), isActive, dPDateIncome.getValue(), gender,
				Double.parseDouble(tFAvailableMoney.getText()));
		int position = getPosition(tFId.getText());

		Object[] options = { "Cancelar", "Registrar", "Editar", "Eliminar" };
		int confirmation = JOptionPane.showOptionDialog(null, "Esta seguro de registrar?", "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		if (validation) {
			notifyAction("El estudiante con ese ID se encuntra registrado");
		}

		if (confirmation == 1 && !validation) {
			if (StudentData.saveStudent(student)) {
				notifyAction("Estudiante registrado correctamente");
				clearForm();
			}
		} else if (confirmation == 2 && validation) {

			StudentData.editStudent(position, student);
			notifyAction("Estudiante editado correctamente");
			clearForm();
		} else if (confirmation == 2 && !validation) {
			notifyAction("Intenta editadar un estudiante que no se encuetra");
			clearForm();
		} else if (confirmation == 3 && validation) {

			StudentData.deleteStudent(position, student);
			notifyAction("Estudiante editado correctamente");
			clearForm();
		} else if (confirmation == 3 && !validation) {
			notifyAction("Intenta eliminar un estudiante que no se encuetra");
			clearForm();
		}

		return true;

	}

	private void clearForm() {
		tFId.setText("");
		tFName.setText("");
		tFEmail.setText("");
		tFPhone.setText("");
		tFAvailableMoney.setText("");
		cBGender.getSelectionModel().selectFirst();
		dPDateIncome.setValue(null);
	}

	private void notifyAction(String message) {
		lblErrorMessage.setText(message);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), e -> lblErrorMessage.setText("")));
		timeline.setCycleCount(1);
		timeline.play();
	}

	private String validateForm() {

		String messageError = "";

		String idText = tFId.getText();
		if (idText.isEmpty() || idText.length() > 10) {
			messageError += "Id vacio o Maximo de letras";
		}

		if (tFName.getText().isEmpty()) {
			messageError += "\tEl nombre es requerido";
		}

		String phoneText = tFPhone.getText();
		if (phoneText.isEmpty() || phoneText.length() < 8 || phoneText.length() > 10) {
			messageError += "\tTelefono fuera de rango o vacio";
		}

		if (dPDateIncome.getValue() == null) {
			messageError += "\tLa fecha es requerida";
		}

		String moneyText = tFAvailableMoney.getText();
		if (moneyText.isEmpty()) {
			messageError += "\tEl monto es requerido";
		} else {
			try {
				double dineroDisponible = Double.parseDouble(moneyText);
				if (dineroDisponible < 5000 || dineroDisponible > 15000) {
					messageError += "\tEl monto supera o falta al limite";
				}
			} catch (NumberFormatException e) {
				messageError += "\tEl monto debe ser un número válido";
			}
		}

		return messageError;
	}

	public int getPosition(String id) {
		int position = 0;
		List<Student> listStudent = StudentData.getStudentList();

		for (int i = 0; i < listStudent.size(); i++) {
			Student student = listStudent.get(i);
			if (id.equals(student.getId())) {
				position = i;
				break;
			}
		}

		return position;
	}

	public void closeWindows() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIConsultRecharge.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			Stage temp = (Stage) bRegister.getScene().getWindow();
			temp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
