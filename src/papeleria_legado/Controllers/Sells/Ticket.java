package papeleria_legado.Controllers.Sells;

import papeleria_legado.GenerateTicket;
import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.HomeController;
import papeleria_legado.Controllers.LoginController;
import papeleria_legado.Models.Sell_Detail;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;

import com.itextpdf.text.DocumentException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class Ticket {

	@FXML
	private Label ticketNumber;

	@FXML
	private TableView<Sell_Detail> tableTicket;

	@FXML
	private TableColumn<Sell_Detail, String> name;

	@FXML
	private TableColumn<Sell_Detail, Float> price;

	@FXML
	private TableColumn<Sell_Detail, Integer> quantity;

	@FXML
	private TableColumn<Sell_Detail, Float> subtotal;

	@FXML
	private Label totalSells;

	@FXML
	private Label subtotalSell;

	@FXML
	private Label iva;

	@FXML
	private Label noTicket;

	private static int pkSell;
	private static float auxSubtotal = 0;

	@FXML
	void cashRegister(MouseEvent event) throws Exception {
		if (MySQLConnection.UserRol == 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("No cuentas con los permisos necesarios");
			alert.setGraphic(
					new ImageView(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));
			Stage stageC = (Stage) alert.getDialogPane().getScene().getWindow();
			stageC.getIcons()
					.add(new Image(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));

			alert.showAndWait();
		} else {

			papeleria_legado.Controllers.Cashs.Index indexCashs = new papeleria_legado.Controllers.Cashs.Index();
			indexCashs.showView(event);
		}
	}

	@FXML
	void categories(MouseEvent event) throws Exception {
		if (MySQLConnection.UserRol == 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("No cuentas con los permisos necesarios");
			alert.setGraphic(
					new ImageView(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));
			Stage stageC = (Stage) alert.getDialogPane().getScene().getWindow();
			stageC.getIcons()
					.add(new Image(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));

			alert.showAndWait();
		} else {
			papeleria_legado.Controllers.Categories.Index indexCategories = new papeleria_legado.Controllers.Categories.Index();
			indexCategories.showView(event);
		}
	}

	@FXML
	void home(MouseEvent event) throws Exception {
		HomeController home = new HomeController();
		home.showView(event);
	}

	@FXML
	void logout(MouseEvent event) throws Exception {
		LoginController login = new LoginController();
		login.showView(event);
	}

	@FXML
	void makeSales(MouseEvent event) throws Exception {
		Save makeSale = new Save();
		makeSale.showView(event);
	}

	@FXML
	void products(MouseEvent event) throws Exception {
		if (MySQLConnection.UserRol == 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("No cuentas con los permisos necesarios");
			alert.setGraphic(
					new ImageView(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));
			Stage stageC = (Stage) alert.getDialogPane().getScene().getWindow();
			stageC.getIcons()
					.add(new Image(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));

			alert.showAndWait();
		} else {
			papeleria_legado.Controllers.Products.Index indexProducts = new papeleria_legado.Controllers.Products.Index();
			indexProducts.showView(event);
		}
	}

	@FXML
	void providers(MouseEvent event) throws Exception {
		if (MySQLConnection.UserRol == 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("No cuentas con los permisos necesarios");
			alert.setGraphic(
					new ImageView(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));
			Stage stageC = (Stage) alert.getDialogPane().getScene().getWindow();
			stageC.getIcons()
					.add(new Image(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));

			alert.showAndWait();
		} else {
			papeleria_legado.Controllers.Providers.Index indexProviders = new papeleria_legado.Controllers.Providers.Index();
			indexProviders.showView(event);
		}
	}

	@FXML
	void sells(MouseEvent event) throws Exception {
		if (MySQLConnection.UserRol == 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("No cuentas con los permisos necesarios");
			alert.setGraphic(
					new ImageView(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));
			Stage stageC = (Stage) alert.getDialogPane().getScene().getWindow();
			stageC.getIcons()
					.add(new Image(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));

			alert.showAndWait();
		} else {
			papeleria_legado.Controllers.Sells.Index indexSells = new papeleria_legado.Controllers.Sells.Index();
			indexSells.showView(event);
		}
	}

	@FXML
	void users(MouseEvent event) throws Exception {
		if (MySQLConnection.UserRol == 3) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("No cuentas con los permisos necesarios");
			alert.setGraphic(
					new ImageView(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));
			Stage stageC = (Stage) alert.getDialogPane().getScene().getWindow();
			stageC.getIcons()
					.add(new Image(this.getClass().getResource("/assets/images/icons/noAccess.png").toString()));

			alert.showAndWait();
		} else {
			papeleria_legado.Controllers.Users.Index indexUsers = new papeleria_legado.Controllers.Users.Index();
			indexUsers.showView(event);
		}
	}

	@FXML
	void clickedBack(MouseEvent event) throws Exception {
		Save makeSale = new Save();
		makeSale.showView(event);
	}

	@FXML
	public void initialize() {
		MySQLConnection mySQL = new MySQLConnection();
		try {
			name.setCellValueFactory(new PropertyValueFactory<>("name"));
			price.setCellValueFactory(new PropertyValueFactory<>("price"));
			quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
			tableTicket.setItems(mySQL.indexSell(getPkSell()));
			ticketNumber.setText("Compra No. " + getPkSell());
			noTicket.setText("No. " + getPkSell());
			for (Sell_Detail item : tableTicket.getItems()) {
				auxSubtotal = auxSubtotal + subtotal.getCellObservableValue(item).getValue();
			}
			float auxTotal = (float) (auxSubtotal + (auxSubtotal * 0.16));
			totalSells.setText("$" + auxTotal);
			subtotalSell.setText("$" + auxSubtotal);
			auxSubtotal = 0;
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@FXML
	void clickedTicketGeneration(MouseEvent event)
			throws MalformedURLException, DocumentException, IOException, SQLException {
		MySQLConnection mySQL = new MySQLConnection();
		String titlePDF;

		titlePDF = "Ticket de venta No. " + getPkSell();

		if (GenerateTicket.exportPDF(titlePDF, mySQL.getSellDetails(getPkSell()))) {
			successfullyAlert();
		} else {
			errorAlert();
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Sells/Ticket.fxml"));
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
		alert.setContentText("Ticket de venta generado en escritorio");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		alert.showAndWait();
	}

	public void errorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Error al generar el ticket");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}

	public static int getPkSell() {
		return pkSell;
	}

	public static void setPkSell(int pkSell) {
		Ticket.pkSell = pkSell;
	}
}
