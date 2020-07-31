package papeleria_legado.Controllers;

import papeleria_legado.MySQLConnection;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Date;
import java.text.SimpleDateFormat;

public class HomeController {
	@FXML
	private Button home;

	@FXML
	private TextField input;

	@FXML
	private Label username;

	@FXML
	private Label rol;

	@FXML
	private Label date;

	@FXML
	private Button btnSave;

	@FXML
	void clickedCashRegister(MouseEvent event) {

	}

	@FXML
	void clickedCategories(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Categories.Index indexCategories = new papeleria_legado.Controllers.Categories.Index();
		indexCategories.showView(event);
	}

	@FXML
	void clickedLogout(MouseEvent event) throws Exception {
		LoginController login = new LoginController();
		login.showView(event);

	}

	@FXML
	void clickedMakeSale(MouseEvent event) {

	}

	@FXML
	void clickedProducts(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Products.Index indexProducts = new papeleria_legado.Controllers.Products.Index();
		indexProducts.showView(event);
	}

	@FXML
	void clickedProviders(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Providers.Index indexProviders = new papeleria_legado.Controllers.Providers.Index();
		indexProviders.showView(event);
	}

	@FXML
	void clickedSells(MouseEvent event) {
	}

	@FXML
	void clickedUsers(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Users.Index indexUsers = new papeleria_legado.Controllers.Users.Index();
		indexUsers.showView(event);

	}

	@FXML
	public void initialize() {
		SimpleDateFormat format = new SimpleDateFormat("dd-MMMMM-yyyy");
		String dateString = format.format(new Date());
		username.setText(MySQLConnection.UserUsername);
		if (MySQLConnection.UserRol == 1) {
			rol.setText("Desarrollador");
		} else if (MySQLConnection.UserRol == 2) {
			rol.setText("Dueño");
		} else if (MySQLConnection.UserRol == 3) {
			rol.setText("Cajero");
			btnSave.setDisable(true);
			input.setDisable(true);
		}
		date.setText(dateString.toString());
	}

	@FXML
	void clickedSave(MouseEvent event) {

	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../views/Home.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

}
