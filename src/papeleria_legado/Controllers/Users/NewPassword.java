package papeleria_legado.Controllers.Users;

import papeleria_legado.MySQLConnection;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewPassword {
	@FXML
	private PasswordField passwordNew;

	@FXML
	private Label lblNameUser;

	private static int pkUser;
	private static String user;

	@FXML
	void initialize() {
		lblNameUser.setText("Usuario: " + getUser());
	}

	@FXML
	void clickedCancel(MouseEvent event) throws Exception {
		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}

	@FXML
	void clickedUpdatePassword(MouseEvent event) throws Exception {
		if (passwordNew.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("No puede estar vacía la nueva contraseña");
			alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
			alert.showAndWait();
		} else {
			MySQLConnection mySQL = new MySQLConnection();
			if (mySQL.editPwdUser(getPkUser(), passwordNew.getText()) == 1) {
				successfullyAlert();
				Node source = (Node) event.getSource();
				Stage stage = (Stage) source.getScene().getWindow();
				stage.close();
			} else {
				errorAlert();
			}
		}
	}

	public void showView(Event event) throws Exception {
		Stage appStage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Users/UserChangePassword.fxml"));
		appStage.setScene(new Scene(root));
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setTitle("Cambio de contraseña");
		appStage.initModality(Modality.WINDOW_MODAL);
		appStage.show();
	}

	public void successfullyAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Contraseña cambiada para el usuario: " + getUser());
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		alert.showAndWait();
	}

	public void errorAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("Ocurrió un error al cambiar la contraseña");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}

	public static int getPkUser() {
		return pkUser;
	}

	public static void setPkUser(int pkUser) {
		NewPassword.pkUser = pkUser;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		NewPassword.user = user;
	}
}
