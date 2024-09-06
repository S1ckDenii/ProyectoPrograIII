package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import domain.Student;

public class GUIServiceRequestController implements Initializable {

	@FXML
	private ComboBox<String> cBEstudiantes;
	@FXML
	private ComboBox<String> cBDia;
	@FXML
	private RadioButton rBAlmuerzo;
	@FXML
	private ToggleGroup Horario;
	@FXML
	private RadioButton rBDesayuno;
	@FXML
	private TableView<Food> tVServices;
	@FXML
	private Button bReturn;
	@FXML
	private Button bAddNewFood;
	@FXML
	private Label lblNotify;

	private ObservableList<Food> food;
	private String daySelected;
	private String studentSelected;
	// private double totalSelected;

	@FXML
	private TableColumn<Food, String> columnDescription;
	@FXML
	private TableColumn<Food, Double> columnPrice;
	@FXML
	private TableColumn<Food, Boolean> columnSolicitar;

	@FXML
	public void bReturnAction(ActionEvent event) {
		closeWindows();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		food = FXCollections.observableArrayList();
		cBDia.getItems().addAll("Lunes", "Martes", "Miercoles", "Jueves", "Viernes");
		cBDia.getSelectionModel().selectFirst();
		loadStudentsShortVersion();

		columnDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

		columnSolicitar.setCellValueFactory(cellData -> cellData.getValue().solicitarProperty());
		columnSolicitar.setCellFactory(CheckBoxTableCell.forTableColumn(columnSolicitar));

		tVServices.setEditable(true);
		columnSolicitar.setEditable(true);

		cBDia.valueProperty().addListener((observable, oldValue, newValue) -> refreshTableView());
		Horario.selectedToggleProperty().addListener((observable, oldValue, newValue) -> refreshTableView());

		refreshTableView();

		tVServices.setItems(food);

	}

	public void refreshTableView() {
		daySelected = cBDia.getValue();
		RadioButton selectedRadioButton = (RadioButton) Horario.getSelectedToggle();
		String horarioConfirmation = selectedRadioButton != null ? selectedRadioButton.getText() : "";

		food.clear();
		if (LogicFilterServices.validateMondayDayAndBreakfast(daySelected, horarioConfirmation)) {
			food.addAll(MondayBreakfastData.getMondayBreakfastList());
		} else if (LogicFilterServices.validateMondayDayAndLunch(daySelected, horarioConfirmation)) {
			food.addAll(MondayLunchData.getMondayLunchList());
		} else if (LogicFilterServices.validateTuesdayDayAndBreakfast(daySelected, horarioConfirmation)) {
			food.addAll(TuesdayBreakfastData.getTuesdayBreakfastList());
		} else if (LogicFilterServices.validateTuesdayDayAndLunch(daySelected, horarioConfirmation)) {
			food.addAll(TuesdayLunchData.getTuesdayLunchList());
		} else if (LogicFilterServices.validateWednesdayDayAndBreakfast(daySelected, horarioConfirmation)) {
			food.addAll(WednesdayBreakfastData.getWednesdayBreakfastList());
		} else if (LogicFilterServices.validateWednesdayDayAndLunch(daySelected, horarioConfirmation)) {
			food.addAll(WednesdayLunchData.getWednesdayLunchList());
		} else if (LogicFilterServices.validateThursdayDayAndBreakfast(daySelected, horarioConfirmation)) {
			food.addAll(ThursdayBreakfastData.getThursdayBreakfastList());
		} else if (LogicFilterServices.validateThursdayDayAndLunch(daySelected, horarioConfirmation)) {
			food.addAll(ThursdayLunchData.getThursdayLunchList());
		} else if (LogicFilterServices.validateFridayDayAndBreakfast(daySelected, horarioConfirmation)) {
			food.addAll(FridayBreakfastData.getFridayBreakfastList());
		} else if (LogicFilterServices.validateFridayDayAndLunch(daySelected, horarioConfirmation)) {
			food.addAll(FridayLunchData.getFridayLunchList());
		}

		for (Food item : food) {
			item.solicitarProperty().addListener((observable, oldValue, newValue) -> updateTotalPrice1());
		}

		tVServices.setItems(food);
	}

	public int getPosicionDeEstudianteSeleccionado(String id) {
		id = cBEstudiantes.getValue();
		List<Student> studentsList = StudentData.getStudentList();
		int position = 0;
		for (int i = 0; i < studentsList.size(); i++) {
			Student student = studentsList.get(i);
			if (id.equals(student.getId())) {
				position = i;
				break;
			}
		}
		return position;
	}

	private void updateTotalPrice1() {

		double saldoDelEstudiante = 0.0;
		double resta = 0.0;
		String id = cBEstudiantes.getValue();
		double totalPrice = getTotalSelectedPrice();
		int position = 0;

		position = getPosicionDeEstudianteSeleccionado(id);

		/*
		 * System.out.println(StudentData.getStudentList());
		 * System.out.println(getPosicionDeEstudianteSeleccionado(id));
		 */

		for (Student student : StudentData.getStudentList()) {

			if (id.equals(student.getName())) {
				saldoDelEstudiante = student.getAvailableMoney();

				if (totalPrice <= saldoDelEstudiante) {
					resta = saldoDelEstudiante - totalPrice;
					student = new Student(student.getId(), student.getName(), student.getEmail(), student.getPhone(),
							student.isActive(), student.getDateIncome(), student.getGender(), resta);
					StudentData.editStudent(position, student);
					lblNotify.setText("Su dinero disponible ahora es: " + resta);
				} else if (totalPrice > saldoDelEstudiante) {
					resta = totalPrice - saldoDelEstudiante;
					lblNotify.setText("Dinero insuficiente su compra por : " + totalPrice + " cuenta con"
							+ saldoDelEstudiante + " necesita " + resta);
				}
			}
		}
	}

	public double getTotalSelectedPrice() {
		double total = 0.0;
		ObservableList<Food> items = tVServices.getItems();

		for (Food item : items) {
			if (item.isSolicitar()) {
				total += item.getPrice();
			}
		}

		return total;
	}

	public void loadStudentsShortVersion() {
		for (Student student : StudentData.getStudentList()) {
			cBEstudiantes.getItems().add(student.getName());
		}
		cBEstudiantes.getSelectionModel().selectFirst();
	}

	@FXML
	public void bAddNewFoodAction(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIServiceRegister.fxml"));
			Parent root = loader.load();
			GUIServiceRegisterController controller = loader.getController();
			controller.bSaveAction(event);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			stage.setOnCloseRequest(e -> controller.closeWindows());
			Stage temp = (Stage) this.bAddNewFood.getScene().getWindow();
			temp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeWindows() {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIMainMenu.fxml"));
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
