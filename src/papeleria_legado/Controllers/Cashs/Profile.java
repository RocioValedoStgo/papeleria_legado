package papeleria_legado.Controllers.Cashs;

import papeleria_legado.GeneratePDF;
import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.HomeController;
import papeleria_legado.Controllers.LoginController;
import papeleria_legado.Controllers.Sells.Ticket;
import papeleria_legado.Models.Sell;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.itextpdf.text.DocumentException;

import javafx.event.ActionEvent;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Profile {
	@FXML
	private TableView<Sell> tableSales;

	@FXML
	private TableColumn<Sell, Integer> id;

	@FXML
	private TableColumn<Sell, Timestamp> dateCreated;

	@FXML
	private TableColumn<Sell, Float> totalSale;

	@FXML
	private TableColumn<Sell, String> options;

	@FXML
	private Label period;

	@FXML
	private Label totalSales;

	private static int pkCash;
	private static String dateInitial;
	private static String dateFinal;
	private float auxTotal = 0;

	@FXML
	void cashRegister(MouseEvent event) throws Exception {
		Index indexCashs = new Index();
		indexCashs.showView(event);
	}

	@FXML
	void categories(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Categories.Index indexCategories = new papeleria_legado.Controllers.Categories.Index();
		indexCategories.showView(event);
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
		papeleria_legado.Controllers.Sells.Save makeSale = new papeleria_legado.Controllers.Sells.Save();
		makeSale.showView(event);
	}

	@FXML
	void products(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Products.Index indexProducts = new papeleria_legado.Controllers.Products.Index();
		indexProducts.showView(event);
	}

	@FXML
	void providers(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Providers.Index indexProviders = new papeleria_legado.Controllers.Providers.Index();
		indexProviders.showView(event);
	}

	@FXML
	void sells(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Sells.Index indexSells = new papeleria_legado.Controllers.Sells.Index();
		indexSells.showView(event);
	}

	@FXML
	void users(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Users.Index indexUsers = new papeleria_legado.Controllers.Users.Index();
		indexUsers.showView(event);
	}

	@FXML
	void clickedCancel(MouseEvent event) throws Exception {
		Index indexCash = new Index();
		indexCash.showView(event);
	}

	@FXML
	public void initialize() {
		if (getDateFinal().isEmpty()) {
			period.setText("Periodo de corte: " + getDateInitial().substring(0, 10) + " - actual");
		} else {
			period.setText("Periodo de corte: " + getDateInitial().substring(0, 10) + " al "
					+ getDateFinal().substring(0, 10));
		}

		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		totalSale.setCellValueFactory(new PropertyValueFactory<>("total"));
		dateCreated.setCellValueFactory(new PropertyValueFactory<>("created"));
		buttonShow();
		MySQLConnection mySQL = new MySQLConnection();
		try {
			tableSales.setItems(mySQL.getSells(getPkCash()));
			for (Sell item : tableSales.getItems()) {
				auxTotal = auxTotal + totalSale.getCellObservableValue(item).getValue();
			}
			totalSales.setText("$ " + auxTotal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void clickedGenerateReport(MouseEvent event)
			throws MalformedURLException, DocumentException, IOException, SQLException {
		MySQLConnection mySQL = new MySQLConnection();
		String titlePDF;
		if (getDateFinal().isEmpty()) {
			titlePDF = "Ventas del " + getDateInitial().substring(0, 10) + " - actual";
		} else {
			titlePDF = "Ventas del " + getDateInitial().substring(0, 10) + " al " + getDateFinal().substring(0, 10);
		}

		if (GeneratePDF.exportPDF(titlePDF, mySQL.getSells(getPkCash()))) {
			successfullyAlert();
		} else {
			errorAlert();
		}
	}

	private void buttonShow() {
		TableColumn<Sell, Void> tlButton = new TableColumn<Sell, Void>();
		Callback<TableColumn<Sell, Void>, TableCell<Sell, Void>> cellFactory = new Callback<TableColumn<Sell, Void>, TableCell<Sell, Void>>() {
			@Override
			public TableCell<Sell, Void> call(final TableColumn<Sell, Void> param) {
				final TableCell<Sell, Void> cell = new TableCell<Sell, Void>() {
					private final Button btnShow = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/show-eye.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnShow.setGraphic((new ImageView(imgShow)));
						btnShow.setTooltip(new Tooltip("Ver"));
						btnShow.setOnAction((ActionEvent event) -> {
							Sell sell = getTableView().getItems().get(getIndex());
							Ticket.setPkSell(sell.getId());
							Ticket ticket = new Ticket();
							try {
								ticket.showView(event);
							} catch (Exception e) {
								e.printStackTrace();
							}
						});
					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnShow);
						}
					}
				};
				return cell;
			}
		};
		tlButton.setCellFactory(cellFactory);
		options.getColumns().add(tlButton);
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/CashRegister/CashRegister.fxml"));
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
		alert.setContentText("Reporte de ventas generado en escritorio");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		alert.showAndWait();
	}

	public void errorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Error al generar el reporte");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}

	public static int getPkCash() {
		return pkCash;
	}

	public static void setPkCash(int pkCash) {
		Profile.pkCash = pkCash;
	}

	public static String getDateInitial() {
		return dateInitial;
	}

	public static void setDateInitial(String dateInitial) {
		Profile.dateInitial = dateInitial;
	}

	public static String getDateFinal() {
		return dateFinal;
	}

	public static void setDateFinal(String dateFinal) {
		Profile.dateFinal = dateFinal;
	}
}
