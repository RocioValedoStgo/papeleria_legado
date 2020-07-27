package papeleria_legado.Controllers.Users;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.Users.Index;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Save {

	ObservableList<String> turns = FXCollections.observableArrayList();
	ObservableList<String> rols = FXCollections.observableArrayList();

	@FXML
	private Button home;

	@FXML
	private Button users;

	@FXML
	private Button providers;

	@FXML
	private Button categories;

	@FXML
	private Button products;

	@FXML
	private Button sells;

	@FXML
	private Button makeSale;

	@FXML
	private Button cash_register;

	@FXML
	private Button logout;

	@FXML
	private Rectangle image;

	@FXML
	private TextField name;

	@FXML
	private TextField last_name;

	@FXML
	private TextField phone;

	@FXML
	private ComboBox<String> turn;

	@FXML
	private ComboBox<String> rol;

	@FXML
	private TextField username;

	@FXML
	private TextField email;

	@FXML
	private PasswordField password;

	@FXML
	void cancel(MouseEvent event) throws Exception {
		Index indexUsers = new Index();
		indexUsers.showView(event);
	}

	@FXML
	void cashRegister(MouseEvent event) {

	}

	@FXML
	void categories(MouseEvent event) {

	}

	@FXML
	void home(MouseEvent event) {

	}

	@FXML
	void logout(MouseEvent event) {

	}

	@FXML
	void makeSales(MouseEvent event) {

	}

	@FXML
	void products(MouseEvent event) {

	}

	@FXML
	void providers(MouseEvent event) {

	}

	@FXML
	void sells(MouseEvent event) {

	}

	@FXML
	void users(MouseEvent event) throws Exception {
		Index indexUsers = new Index();
		indexUsers.showView(event);
	}

	@FXML
	public void initialize() {
		turns.addAll("Matutino", "Vespertino");
		turn.setItems(turns);
		rols.addAll("Dueño", "Cajero");
		rol.setItems(rols);
	}

	@FXML
	void register(MouseEvent event) throws Exception {
		MySQLConnection mySQL = new MySQLConnection();
		if (name.getText().isEmpty() && last_name.getText().isEmpty() && username.getText().isEmpty()
				&& phone.getText().isEmpty() && email.getText().isEmpty() && password.getText().isEmpty()
				&& turn.getSelectionModel().getSelectedItem() == null) {
			warningAlert();
		} else {
			if (mySQL.register(name.getText(), last_name.getText(), username.getText(), phone.getText(),
					turn.getSelectionModel().getSelectedItem(), 3, email.getText(), password.getText())) {
				successfullyAlert();
				Index indexUsers = new Index();
				indexUsers.showView(event);
			} else {
				errorAlert();
			}
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Users/UserPost.fxml"));
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

	public void errorAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("Ocurrió un error en el registro");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}

}
