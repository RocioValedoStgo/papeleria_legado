package papeleria_legado.Controllers.Providers;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.Provider;
import papeleria_legado.Controllers.Providers.Index;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Edit {

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
	private Label titleProviderName;

	@FXML
	private Rectangle image;

	@FXML
	private TextField name;

	@FXML
	private TextField address;

	@FXML
	private TextField phone;

	@FXML
	private TextField email;

	@FXML
	private Label lblProveedor;

	private static int pkProvider;

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
	void users(MouseEvent event) {

	}

	@FXML
	void clickedCancel(MouseEvent event) throws Exception {
		Index indexProviders = new Index();
		indexProviders.showView(event);
	}

	@FXML
	public void initialize() {

		MySQLConnection mySQL = new MySQLConnection();
		try {
			Provider provider = mySQL.getProvider(getPkProvider());
			titleProviderName.setText("Editando proveedor: " + provider.getName());
			lblProveedor.setText(provider.getName());
			name.setText(provider.getName());
			address.setText(provider.getAddress());
			phone.setText(provider.getPhone());
			email.setText(provider.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void clickedUpdate(MouseEvent event) throws Exception {
		if (name.getText().isEmpty() || address.getText().isEmpty() || phone.getText().isEmpty()
				|| email.getText().isEmpty()) {
			warningAlert();
		} else {
			MySQLConnection mySQL = new MySQLConnection();
			if (mySQL.editProvider(getPkProvider(), name.getText(), address.getText(), phone.getText(),
					email.getText()) == 1) {
				successfullyAlert();
				Profile providerProfile = new Profile();
				Profile.setPkUser(getPkProvider());
				providerProfile.showView(event);
			} else {
				errorAlert();
			}
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Providers/ProvidersUpdate.fxml"));
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
		alert.setContentText("Proveedor editado correctamente");
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
		alert.setContentText("Ocurrió un error al editar al proveedor");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}

	public static int getPkProvider() {
		return pkProvider;
	}

	public static void setPkProvider(int pkProvider) {
		Edit.pkProvider = pkProvider;
	}

}
