package papeleria_legado.Controllers;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.Cash_Register;

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
import javafx.stage.Stage;

import java.util.Date;
import java.sql.SQLException;
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
	void clickedMakeSale(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Sells.Save makeSale = new papeleria_legado.Controllers.Sells.Save();
		makeSale.showView(event);
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
	void clickedSells(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Sells.Index indexSells = new papeleria_legado.Controllers.Sells.Index();
		indexSells.showView(event);
	}

	@FXML
	void clickedUsers(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Users.Index indexUsers = new papeleria_legado.Controllers.Users.Index();
		indexUsers.showView(event);

	}

	@FXML
	public void initialize() {
		MySQLConnection mySQL = new MySQLConnection();
		try {
			if (mySQL.getCashActive() == 0) {
				if (mySQL.newCashRegister()) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText(
							"No se detecto una caja activa, se ha creado una\nFavor de ingresar la venta 0");
					alert.setGraphic(
							new ImageView(this.getClass().getResource("/assets/images/icons/question.png").toString()));
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					stage.getIcons().add(
							new Image(this.getClass().getResource("/assets/images/icons/question.png").toString()));
					alert.showAndWait();
					btnSave.setDisable(false);
					input.setDisable(false);
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

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
	void clickedSave(MouseEvent event) throws SQLException {
		SimpleDateFormat auxFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		MySQLConnection mySQL = new MySQLConnection();

		try {
			if (!mySQL.checkSellZero(auxFormat.format(new Date()))) {
				int cash_id = mySQL.getCashActive();
				if (mySQL.saveSellZero(Float.parseFloat(input.getText()), 0, 0, cash_id)) {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText(null);
					alert.setContentText("Venta 0 registrada");
					alert.setGraphic(
							new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					stage.getIcons()
							.add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
					alert.showAndWait();
					btnSave.setDisable(true);
					input.setDisable(true);
					Cash_Register cash = mySQL.getCashRegister();
					float auxSellTotal = cash.getTotal() + Float.parseFloat(input.getText());
					if (mySQL.updateTotalCash(cash.getId(), auxSellTotal) != 1) {
						Alert alertA = new Alert(AlertType.ERROR);
						alertA.setHeaderText(null);
						alertA.setContentText("Ocurrio un error al actualizar el total de venta");
						alertA.setGraphic(new ImageView(
								this.getClass().getResource("/assets/images/icons/error.png").toString()));
						Stage stageA = (Stage) alertA.getDialogPane().getScene().getWindow();
						stageA.getIcons().add(
								new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
						alertA.showAndWait();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setContentText("Ocurrio un error al guardar la venta 0");
					alert.setGraphic(
							new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
					Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
					stage.getIcons()
							.add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
					alert.showAndWait();
				}
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

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
