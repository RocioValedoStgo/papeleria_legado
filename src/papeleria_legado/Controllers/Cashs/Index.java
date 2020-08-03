package papeleria_legado.Controllers.Cashs;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.HomeController;
import papeleria_legado.Controllers.LoginController;
import papeleria_legado.Models.Cash_Register;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Index implements Initializable {

	@FXML
	private TableView<Cash_Register> tableCash;

	@FXML
	private TableColumn<Cash_Register, Integer> id;

	@FXML
	private TableColumn<Cash_Register, Timestamp> dates;

	@FXML
	private TableColumn<Cash_Register, Float> sell;

	@FXML
	private TableColumn<Cash_Register, String> options;

	@FXML
	private Button btnCortCash;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		MySQLConnection mySQL = new MySQLConnection();
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		sell.setCellValueFactory(new PropertyValueFactory<>("total"));
		dates.setCellValueFactory(new PropertyValueFactory<>("created"));
		buttonShow();
		try {
			tableCash.setItems(mySQL.indexCashs());
			if (mySQL.getCashActive() == 0) {
				btnCortCash.setDisable(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void clickedCloseCashRegister(MouseEvent event) throws Exception {
		MySQLConnection mySQL = new MySQLConnection();
		int cash_id = mySQL.getCashActive();
		if (mySQL.closeCashRegister(cash_id) == 1) {
			if (mySQL.newCashRegister()) {
				successfullyAlert();
				showView(event);
			} else {
				errorAlertCreate();
			}
		} else {
			errorAlert();
		}
	}

	private void buttonShow() {
		TableColumn<Cash_Register, Void> tlButton = new TableColumn<Cash_Register, Void>();
		Callback<TableColumn<Cash_Register, Void>, TableCell<Cash_Register, Void>> cellFactory = new Callback<TableColumn<Cash_Register, Void>, TableCell<Cash_Register, Void>>() {
			@Override
			public TableCell<Cash_Register, Void> call(final TableColumn<Cash_Register, Void> param) {
				final TableCell<Cash_Register, Void> cell = new TableCell<Cash_Register, Void>() {
					private final Button btnShow = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/show-eye.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnShow.setGraphic((new ImageView(imgShow)));
						btnShow.setTooltip(new Tooltip("Ver"));
						btnShow.setOnAction((ActionEvent event) -> {
							Cash_Register cashRegister = getTableView().getItems().get(getIndex());
							Profile showProfileCash = new Profile();
							Profile.setPkCash(cashRegister.getId());
							Profile.setDateInitial(cashRegister.getCreated());
							Profile.setDateFinal(cashRegister.getClose());
							try {
								showProfileCash.showView(event);
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/CashRegister/CashRegisterIndex.fxml"));
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
		alert.setContentText("Corte de caja exitoso");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		alert.showAndWait();
	}

	public void errorAlertCreate() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Error al crear la nueva caja");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}

	public void errorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Error al hacer el corte de caja");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}
}
