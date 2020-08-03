package papeleria_legado.Controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import papeleria_legado.MySQLConnection;

public class LoginController {

	@FXML
	private PasswordField password;

	@FXML
	private TextField username;

	@FXML
	void clickedLogin(MouseEvent event) throws Exception {
		MySQLConnection mySQL = new MySQLConnection();
		if (username.getText().isEmpty() && password.getText().isEmpty()) {
			warningAlert();
		} else {
			if (mySQL.login(username.getText(), password.getText())) {
				HomeController home = new HomeController();
				home.showView(event);
			} else {
				errorAlert();
			}
		}
	}

	@FXML
	void clickedRegister(MouseEvent event) throws Exception {
		RegisterController register = new RegisterController();
		register.showView(event);
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../views/Login.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();

	}

	public void warningAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("Los campos se encuentran vacíos, favor de llenarlos");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/warning.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/warning.png").toString()));
		alert.showAndWait();
	}

	public void errorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Correo y/o contraseña incorrectos");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}

}
