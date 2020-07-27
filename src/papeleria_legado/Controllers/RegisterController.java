package papeleria_legado.Controllers;

import papeleria_legado.MySQLConnection;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class RegisterController {

	ObservableList<String> turns = FXCollections.observableArrayList();

	@FXML
	private PasswordField password;

	@FXML
	private TextField name;

	@FXML
	private TextField last_name;

	@FXML
	private TextField phone;

	@FXML
	private TextField username;

	@FXML
	private TextField email;

	@FXML
	private ComboBox<String> turn;

	@FXML
	public void initialize() {
		turns.addAll("Matutino", "Vespertino");
		turn.setItems(turns);
	}

	@FXML
	void clickedBack(MouseEvent event) throws Exception {
		LoginController login = new LoginController();
		login.showView(event);
	}

	@FXML
	void clickedRegister(MouseEvent event) throws SQLException {
		MySQLConnection mySQL = new MySQLConnection();
		if (name.getText().isEmpty() && last_name.getText().isEmpty() && username.getText().isEmpty()
				&& phone.getText().isEmpty() && email.getText().isEmpty() && password.getText().isEmpty()
				&& turn.getSelectionModel().getSelectedItem() == null) {
			warningAlert();
		} else {
			if (mySQL.register(name.getText(), last_name.getText(), username.getText(), phone.getText(),
					turn.getSelectionModel().getSelectedItem(), 3, email.getText(), password.getText())) {
				successfullyAlert();
				LoginController login = new LoginController();
				try {
					login.showView(event);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../views/Register.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public void successfullyAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Registro exitoso");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		alert.showAndWait();
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

}
