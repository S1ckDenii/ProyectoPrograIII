package business;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class GUIMainMenuController {
	@FXML
	private Button bSolicitarServicio;
	@FXML
	private Button bSolicitarRecarga;
	@FXML
	private Button bConsultarSaldo;

	// Event Listener on Button[#bSolicitarServicio].onAction

	@FXML
	public void bSolicitarServicioAction(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIServiceRequest.fxml"));
			Parent root = loader.load();
			GUIServiceRequestController controller = loader.getController();
			controller.loadStudentsShortVersion();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			stage.setOnCloseRequest(e -> controller.closeWindows());
			Stage temp = (Stage) this.bSolicitarServicio.getScene().getWindow();
			temp.close();
		} catch (IOException e) {
		}

	}

	@FXML
	public void bSolicitarRecargaAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIRechargeStudent.fxml"));
			Parent root = loader.load();
			GUIRechargeStudentController controller = loader.getController();
			controller.clearForm();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			stage.setOnCloseRequest(e -> controller.closeWindows());
			Stage temp = (Stage) this.bSolicitarServicio.getScene().getWindow();
			temp.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

	@FXML
	public void bConsultarSaldoAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/presentation/GUIConsultRecharge.fxml"));
			Parent root = loader.load();
			GUIConsultRechargeController controller = loader.getController();
			controller.ConsultRechargeAction(event);
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

			stage.setOnCloseRequest(e -> controller.closeWindows());
			Stage temp = (Stage) this.bSolicitarServicio.getScene().getWindow();
			temp.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}
}
