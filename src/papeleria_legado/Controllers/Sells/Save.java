package papeleria_legado.Controllers.Sells;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.HomeController;
import papeleria_legado.Controllers.LoginController;
import papeleria_legado.Models.Cash_Register;
import papeleria_legado.Models.Product;
import papeleria_legado.Models.Sell_Detail;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;

import org.controlsfx.control.textfield.TextFields;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Save {

	@FXML
	private TableView<Sell_Detail> tableSells;

	@FXML
	private TableColumn<Sell_Detail, String> name;

	@FXML
	private TableColumn<Sell_Detail, String> optionRemove;

	@FXML
	private TableColumn<Sell_Detail, Float> price;

	@FXML
	private TableColumn<Sell_Detail, Integer> quantity;

	@FXML
	private TableColumn<Sell_Detail, Float> subtotal;

	@FXML
	private Label totalSells;

	@FXML
	private TextField search;

	@FXML
	private Button addProduct;

	@FXML
	private Button btnPayment;

	@FXML
	private Label subtotalSell;

	@FXML
	private Label iva;

	private ObservableList<Sell_Detail> listDetails = FXCollections.observableArrayList();
	private float subTotal = 0;
	private float auxTotal = 0;
	private float total = 0;

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
	void clickedCancel(MouseEvent event) throws Exception {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText(null);
		alert.setContentText("¿Desea cancelar la compra?");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/cancelSell.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/cancelSell.png").toString()));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			Alert alertC = new Alert(AlertType.INFORMATION);
			alertC.setHeaderText(null);
			alertC.setContentText("Compra cancelada");
			alertC.setGraphic(
					new ImageView(this.getClass().getResource("/assets/images/icons/cancelSell.png").toString()));
			Stage stageC = (Stage) alertC.getDialogPane().getScene().getWindow();
			stageC.getIcons()
					.add(new Image(this.getClass().getResource("/assets/images/icons/cancelSell.png").toString()));

			alertC.showAndWait();
			HomeController home = new HomeController();
			home.showView(event);
		}

	}

	@FXML
	public void initialize() {
		MySQLConnection mySQL = new MySQLConnection();
		try {
			name.setCellValueFactory(new PropertyValueFactory<>("product_id"));
			price.setCellValueFactory(new PropertyValueFactory<>("price"));
			quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
			subtotal.setCellValueFactory(new PropertyValueFactory<>("subtotal"));
			buttonDelete();
			subtotalSell.setText("$0.00");
			totalSells.setText("$0.00");
			TextFields.bindAutoCompletion(search, mySQL.getProducts());
			addProduct.setTooltip(new Tooltip("Añadir"));
			btnPayment.setDisable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void clickedAddProduct(MouseEvent event) throws SQLException {
		if (search.getText().isEmpty()) {
			errorAlert();
		} else {
			TextInputDialog dialog = new TextInputDialog();
			dialog.setHeaderText(null);
			dialog.setGraphic(
					new ImageView(this.getClass().getResource("/assets/images/icons/question.png").toString()));
			Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
			stage.getIcons()
					.add(new Image(this.getClass().getResource("/assets/images/icons/question.png").toString()));
			dialog.setTitle("Realizar compra");
			dialog.setContentText("Ingrese la cantidad a comprar:");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()) {
				MySQLConnection mySQL = new MySQLConnection();
				Product product = mySQL.getProduct(search.getText());
				if (Integer.parseInt(result.get()) > product.getQuantity()) {
					warningAlert();
				} else {
					btnPayment.setDisable(false);
					Sell_Detail sellDetail = new Sell_Detail(mySQL.getProduct(search.getText()),
							Integer.parseInt(result.get()));
					sellDetail.setPrice(product.getPrice());
					sellDetail.setQuantity(Integer.parseInt(result.get()));
					sellDetail.setSubtotal(Integer.parseInt(result.get()));
					listDetails.add(sellDetail);
					tableSells.setItems(listDetails);
					for (Sell_Detail item : tableSells.getItems()) {
						auxTotal = auxTotal + subtotal.getCellObservableValue(item).getValue();
						subTotal = subTotal + subtotal.getCellObservableValue(item).getValue();
					}
					auxTotal = (float) (auxTotal + (auxTotal * 0.16));
					totalSells.setText("$" + auxTotal);
					subtotalSell.setText("$" + subTotal);
					total = auxTotal;
					auxTotal = 0;
					subTotal = 0;
					search.setText("");
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText(null);
				alert.setContentText("Es necesario ingresar la cantidad a comprar");
				alert.setGraphic(
						new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
				Stage stageError = (Stage) alert.getDialogPane().getScene().getWindow();
				stageError.getIcons()
						.add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
				alert.showAndWait();
			}
		}
	}

	@FXML
	void clickedPayment(MouseEvent event) throws Exception {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setHeaderText(null);
		dialog.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/makeSale.png").toString()));
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/makeSale.png").toString()));
		dialog.setTitle("Realizar compra");
		dialog.setContentText("Ingrese el monto:");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			float change = 0;
			if (Float.parseFloat(result.get()) > total) {
				MySQLConnection mySQL = new MySQLConnection();
				int aux = 0;
				int sell_id = 0;
				for (Sell_Detail sellDetail : tableSells.getItems()) {
					String nameProduct = name.getCellObservableValue(sellDetail).getValue();
					Product product = mySQL.getProduct(nameProduct);
					int product_id = product.getId();
					int quantityProduct = quantity.getCellObservableValue(sellDetail).getValue();
					float subTotal = subtotal.getCellObservableValue(sellDetail).getValue();
					change = Math.round(Float.parseFloat(result.get()) - total);
					int cash_id = mySQL.getCashActive();
					Cash_Register cash = mySQL.getCashRegister();
					float auxSellTotal = cash.getTotal() + total;

					if (aux == 0) {
						sell_id = mySQL.saveSell(total, Float.parseFloat(result.get()), change, cash_id);
						if (mySQL.updateTotalCash(cash.getId(), auxSellTotal) != 1) {
							Alert alertEE = new Alert(AlertType.ERROR);
							alertEE.setHeaderText(null);
							alertEE.setContentText("Ocurrio un error al actualizar el total de venta");
							alertEE.setGraphic(new ImageView(
									this.getClass().getResource("/assets/images/icons/error.png").toString()));
							Stage stageEE = (Stage) alertEE.getDialogPane().getScene().getWindow();
							stageEE.getIcons().add(new Image(
									this.getClass().getResource("/assets/images/icons/error.png").toString()));
							alertEE.showAndWait();
							break;
						}
						aux++;
					}
					if (mySQL.saveDetails(product_id, quantityProduct, subTotal, sell_id) == false) {
						Alert alertG = new Alert(AlertType.ERROR);
						alertG.setHeaderText(null);
						alertG.setContentText("Ocurrio un error al guardar el detalle");
						alertG.setGraphic(new ImageView(
								this.getClass().getResource("/assets/images/icons/error.png").toString()));
						Stage stageG = (Stage) alertG.getDialogPane().getScene().getWindow();
						stageG.getIcons().add(
								new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
						alertG.showAndWait();
						break;
					}
				}
				Ticket ticket = new Ticket();
				Ticket.setPkSell(sell_id);
				ticket.showView(event);
				successfullyAlertSell(change);
				search.setText("");
				tableSells.getItems().clear();
			} else {
				Alert alertE = new Alert(AlertType.ERROR);
				alertE.setHeaderText(null);
				alertE.setContentText("El monto ingresado es insuficiente");
				alertE.setGraphic(
						new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
				Stage stageE = (Stage) alertE.getDialogPane().getScene().getWindow();
				stageE.getIcons()
						.add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
				alertE.showAndWait();
			}
		} else {
			Alert alertM = new Alert(AlertType.ERROR);
			alertM.setHeaderText(null);
			alertM.setContentText("Necesita ingresar el monto");
			alertM.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
			Stage stageM = (Stage) alertM.getDialogPane().getScene().getWindow();
			stageM.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
			alertM.showAndWait();
		}
	}

	private void buttonDelete() {
		TableColumn<Sell_Detail, Void> tlButton = new TableColumn<Sell_Detail, Void>();
		Callback<TableColumn<Sell_Detail, Void>, TableCell<Sell_Detail, Void>> cellFactory = new Callback<TableColumn<Sell_Detail, Void>, TableCell<Sell_Detail, Void>>() {
			@Override
			public TableCell<Sell_Detail, Void> call(final TableColumn<Sell_Detail, Void> param) {
				final TableCell<Sell_Detail, Void> cell = new TableCell<Sell_Detail, Void>() {
					private final Button btnDelete = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/remove.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnDelete.setGraphic((new ImageView(imgShow)));
						btnDelete.setTooltip(new Tooltip("Quitar"));
						btnDelete.setOnAction((ActionEvent event) -> {
							Sell_Detail sellDetail = tableSells.getSelectionModel().getSelectedItem();
							tableSells.getItems().remove(sellDetail);
							float auxTotal = 0;
							for (Sell_Detail item : tableSells.getItems()) {
								auxTotal = auxTotal + subtotal.getCellObservableValue(item).getValue();
								subTotal = subTotal + subtotal.getCellObservableValue(item).getValue();
							}
							auxTotal = (float) (auxTotal + (auxTotal * 0.16));
							subtotalSell.setText("$" + subTotal);
							totalSells.setText("$" + auxTotal);
							total = auxTotal;
							auxTotal = 0;
							subTotal = 0;
						});

					}

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
						} else {
							setGraphic(btnDelete);
						}
					}
				};
				return cell;
			}
		};
		tlButton.setCellFactory(cellFactory);
		optionRemove.getColumns().add(tlButton);
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Sells/Cart.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public void successfullyAlertSell(float change) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Compra realizada con éxito");
		alert.setContentText("Su cambio es de: " + change);
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/makeSale.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/makeSale.png").toString()));
		alert.showAndWait();
	}

	public void successfullyAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Producto removido con éxito");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		alert.showAndWait();
	}

	public void warningAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("La cantidad ingresada supera lo existente en almacen");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/warning.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/warning.png").toString()));
		alert.showAndWait();
	}

	public void errorAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Necesita buscar un producto para poder agregarlo al carrito");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}
}
