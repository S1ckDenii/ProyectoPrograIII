package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import data.RechargeData;
import data.StudentData;
import domain.Recharge;
import domain.Student;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class GUIConsultRechargeController implements Initializable {
	@FXML
	private TextField tfID;
	@FXML
	private Button btnConsulRecharge;
	@FXML
	private TableView<Map.Entry<Student, Recharge>> tableView;

	@FXML
	private TableColumn<Map.Entry<Student, Recharge>, String> columnID;

	@FXML
	private TableColumn<Map.Entry<Student, Recharge>, String> columnStudent;

	@FXML
	private TableColumn<Map.Entry<Student, Recharge>, LocalDate> columnRechargeDate;

	@FXML
	private TableColumn<Map.Entry<Student, Recharge>, Double> columnBalance;
	@FXML
	private Button btnAddNewStudent;
	@FXML
	private Button btnBack;
	@FXML
	private Label lblErrorMessage;

	ObservableList<Map.Entry<Student, Recharge>> combinedData;

	// Event Listener on Button[#btnConsulRecharge].onAction
	@FXML
	public boolean ConsultRechargeAction(ActionEvent event) {

		String messageError = validateForm();
		if (!messageError.isEmpty()) {
			notifyAction(messageError);
			return false;
		}

		if (!LogicStudent.validateId(tfID.getText())) {
			notifyAction("Usuario no encontrado, intentelo de nuevo");
		} else if (LogicStudent.validateId(tfID.getText())) {
			addDataToObservableList(tfID.getText());
			clearForm();

		}

		return true;
	}

	// Event Listener on Button[#btnAddNewStudent].onAction
	@FXML
	public void addNewStudentAction(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIStudentRegister.fxml"));
			Parent root = loader.load();
			GUIStudentRegisterController controller = loader.getController();
			controller.bRegistrarAction(event);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			stage.setOnCloseRequest(e -> controller.closeWindows());
			Stage temp = (Stage) this.btnBack.getScene().getWindow();
			temp.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

	// Event Listener on Button[#btnBack].onAction
	@FXML
	public void backAction(ActionEvent event) {
		closeWindows();
	}

	public void addDataToObservableList(String id) {
		combinedData.clear();

		for (Student student : StudentData.getStudentList()) {
			if (student.getId().equals(id)) {
				for (Recharge recharge : RechargeData.getRechargeList()) {
					if (recharge.getId().equals(id)) {
						combinedData.add(new AbstractMap.SimpleEntry<>(student, recharge));
					}
				}
			}
		}
	}

	private void clearForm() {
		tfID.setText("");

	}

	private String validateForm() {

		String messageError = "";

		if (tfID.getText().isEmpty()) {
			messageError = "Debe de ingresar su carne";
		}

		return messageError;
	}

	private void notifyAction(String messageError) {

		lblErrorMessage.setText(messageError);
		Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), e -> lblErrorMessage.setText("")));
		timeline.setCycleCount(1);
		timeline.play();

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		columnID.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey().getId()));
		columnStudent.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getKey().getName()));
		columnRechargeDate.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getRechargeDate()));

		columnBalance.setCellValueFactory(
				cellData -> new SimpleObjectProperty<>(cellData.getValue().getValue().getAmount()));

		combinedData = FXCollections.observableArrayList();
		tableView.setItems(combinedData);

	}

}
