package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;

import data.FridayBreakfastData;
import data.FridayLunchData;
import data.MondayBreakfastData;
import data.MondayLunchData;
import data.StudentData;
import data.ThursdayBreakfastData;
import data.ThursdayLunchData;
import data.TuesdayBreakfastData;
import data.TuesdayLunchData;
import data.WednesdayBreakfastData;
import data.WednesdayLunchData;
import domain.Food;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.ComboBox;

import javafx.scene.control.RadioButton;

public class GUIServiceRegisterController {
	@FXML
	private RadioButton rBDesayuno;
	@FXML
	private ToggleGroup Horario;
	@FXML
	private RadioButton rBAlmuerzo;
	@FXML
	private ComboBox<String> cBDia;
	@FXML
	private TextField tFDescription;
	@FXML
	private TextField tFPrice;
	@FXML
	private Button bReturn;
	@FXML
	private Button bSave;
	@FXML
	private Label lblErrorMessage;

	private String daySelected;

	@FXML
	public void initialize() {

		cBDia.getItems().addAll("Lunes", "Martes", "Miercoles", "Jueves", "Viernes");
		cBDia.getSelectionModel().selectFirst();
	}

	@FXML
	private void getDaySelected(ActionEvent event) {
		this.daySelected = cBDia.getValue();
	}

	// Event Listener on Button[#bReturn].onAction
	@FXML
	public void bRetrunAction(ActionEvent event) {
		closeWindows();
	}

	// Event Listener on Button[#bSave].onAction
	@FXML
	public boolean bSaveAction(ActionEvent event) {

		String messageError = validateForm();
		if (!messageError.isEmpty()) {
			notifyAction(messageError);
			return false;
		}

		String horarioConfirmation = (rBDesayuno.isSelected()) ? "Desayuno" : "Almuerzo";
		daySelected = cBDia.getValue();

		Object[] options = { "Cancelar", "Registrar" };
		int confirmation = JOptionPane.showOptionDialog(null,
				"Esta seguro de registrar para el dia: " + daySelected + "  al horario: " + horarioConfirmation, "",
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		Food food = new Food(tFDescription.getText(), Double.parseDouble(tFPrice.getText()));

		if (confirmation == 1) {

			if (LogicFilterServices.validateMondayDayAndBreakfast(daySelected, horarioConfirmation)) {
				MondayBreakfastData.saveMondayBreakfast(food);
				notifyAction("Lunes - Desayuno registrado correctamente");
				clearForm();
			} else if (LogicFilterServices.validateMondayDayAndLunch(daySelected, horarioConfirmation)) {
				MondayLunchData.saveMondayLunch(food);
				notifyAction("Lunes - Almuerzo registrado correctamente");
				clearForm();

			} else if (LogicFilterServices.validateTuesdayDayAndBreakfast(daySelected, horarioConfirmation)) {
				TuesdayBreakfastData.saveTuesdayBreakfast(food);
				notifyAction("Martes - Desayuno registrado correctamente");
				clearForm();
			} else if (LogicFilterServices.validateTuesdayDayAndLunch(daySelected, horarioConfirmation)) {
				TuesdayLunchData.saveTuesdayLunch(food);
				notifyAction("Martes - Almuerzo registrado correctamente");
				clearForm();
			} else if (LogicFilterServices.validateWednesdayDayAndBreakfast(daySelected, horarioConfirmation)) {
				WednesdayBreakfastData.saveWednesdayBreakfast(food);
				notifyAction("Miercoles - Desayuno registrado correctamente");
				clearForm();
			} else if (LogicFilterServices.validateWednesdayDayAndLunch(daySelected, horarioConfirmation)) {
				WednesdayLunchData.saveWednesdayLunch(food);
				notifyAction("Miercoles - Almuerzo registrado correctamente");
				clearForm();
			} else if (LogicFilterServices.validateThursdayDayAndBreakfast(daySelected, horarioConfirmation)) {
				ThursdayBreakfastData.saveThursdayBreakfast(food);
				notifyAction("Jueves - Desayuno registrado correctamente");
				clearForm();
			} else if (LogicFilterServices.validateThursdayDayAndLunch(daySelected, horarioConfirmation)) {
				ThursdayLunchData.saveThursdayLunch(food);
				notifyAction("Juves - Almuerzo registrado correctamente");
				clearForm();
			} else if (LogicFilterServices.validateFridayDayAndBreakfast(daySelected, horarioConfirmation)) {
				FridayBreakfastData.saveFridayBreakfast(food);
				notifyAction("Viernes - Desayuno registrado correctamente");
				clearForm();
			} else if (LogicFilterServices.validateFridayDayAndLunch(daySelected, horarioConfirmation)) {
				FridayLunchData.saveFridayLunch(food);
				notifyAction("Viernes - Almuerzo registrado correctamente");
				clearForm();
			}

			else {

				notifyAction("Error al guardar");
			}
		}
		return true;

	}

	private void clearForm() {
		tFDescription.setText("");
		tFPrice.setText("");
		cBDia.getSelectionModel().selectFirst();
	}

	private void notifyAction(String message) {
		lblErrorMessage.setText(message);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), e -> lblErrorMessage.setText("")));
		timeline.setCycleCount(1);
		timeline.play();
	}

	private String validateForm() {

		String messageError = "";

		if (tFDescription.getText().isEmpty()) {
			messageError += "El servicio no puede estar vacio";
		}
		if (tFPrice.getText().isEmpty()) {
			messageError += "\tEl precio no puede estar vacio";
		}
		if (cBDia.getValue() == null) {
			messageError += "\tEl dia no puede estar vacio";
		}

		return messageError;
	}

	public void closeWindows() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIServiceRequest.fxml"));
		try {
			Parent root = loader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();
			Stage temp = (Stage) bReturn.getScene().getWindow();
			temp.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
