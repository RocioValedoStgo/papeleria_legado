package papeleria_legado.Controllers.Providers;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.HomeController;
import papeleria_legado.Controllers.LoginController;
import papeleria_legado.Controllers.Providers.Edit;
import papeleria_legado.Controllers.Providers.Profile;
import papeleria_legado.Models.Provider;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.util.Callback;

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

public class Index implements Initializable {

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
	private TableView<Provider> tableProviders;

	@FXML
	private TableColumn<Provider, Integer> id;

	@FXML
	private TableColumn<Provider, String> name;

	@FXML
	private TableColumn<Provider, String> address;

	@FXML
	private TableColumn<Provider, String> phone;

	@FXML
	private TableColumn<Provider, String> email;

	@FXML
	private TableColumn<Provider, String> options;

	@FXML
	void cashRegister(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Cashs.Index indexCashs = new papeleria_legado.Controllers.Cashs.Index();
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
		Index indexProviders = new Index();
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
	void clickedCreateProvider(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Providers.Save saveProvider = new papeleria_legado.Controllers.Providers.Save();
		saveProvider.showView(event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		address.setCellValueFactory(new PropertyValueFactory<>("address"));
		phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		buttonShow();
		buttonEdit();
		buttonDelete();
		MySQLConnection mySQL = new MySQLConnection();
		try {
			tableProviders.setItems(mySQL.indexProviders());
		} catch (Exception e) {

		}
	}

	private void buttonShow() {
		TableColumn<Provider, Void> tlButton = new TableColumn<Provider, Void>();
		Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>> cellFactory = new Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>>() {
			@Override
			public TableCell<Provider, Void> call(final TableColumn<Provider, Void> param) {
				final TableCell<Provider, Void> cell = new TableCell<Provider, Void>() {
					private final Button btnShow = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/show-eye.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnShow.setGraphic((new ImageView(imgShow)));
						btnShow.setTooltip(new Tooltip("Ver"));
						btnShow.setOnAction((ActionEvent event) -> {
							Provider provider = getTableView().getItems().get(getIndex());
							Profile showProvider = new Profile();
							try {
								Profile.setPkUser(provider.getId());
								showProvider.showView(event);
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

	private void buttonEdit() {
		TableColumn<Provider, Void> tlButton = new TableColumn<Provider, Void>();
		Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>> cellFactory = new Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>>() {
			@Override
			public TableCell<Provider, Void> call(final TableColumn<Provider, Void> param) {
				final TableCell<Provider, Void> cell = new TableCell<Provider, Void>() {
					private final Button btnEdit = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/edit.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnEdit.setGraphic((new ImageView(imgShow)));
						btnEdit.setTooltip(new Tooltip("Editar"));
						btnEdit.setOnAction((ActionEvent event) -> {
							Provider provider = getTableView().getItems().get(getIndex());
							Edit editProvider = new Edit();
							try {
								Edit.setPkProvider(provider.getId());
								editProvider.showView(event);
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
							setGraphic(btnEdit);
						}
					}
				};
				return cell;
			}
		};
		tlButton.setCellFactory(cellFactory);
		options.getColumns().add(tlButton);
	}

	private void buttonDelete() {
		TableColumn<Provider, Void> tlButton = new TableColumn<Provider, Void>();
		Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>> cellFactory = new Callback<TableColumn<Provider, Void>, TableCell<Provider, Void>>() {
			@Override
			public TableCell<Provider, Void> call(final TableColumn<Provider, Void> param) {
				final TableCell<Provider, Void> cell = new TableCell<Provider, Void>() {
					private final Button btnDelete = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/trash.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnDelete.setGraphic((new ImageView(imgShow)));
						btnDelete.setTooltip(new Tooltip("Eliminar"));
						btnDelete.setOnAction((ActionEvent event) -> {
							Provider provider = getTableView().getItems().get(getIndex());
							MySQLConnection mySQL = new MySQLConnection();
							try {
								if (mySQL.destroyProvider(provider.getId()) == 1) {
									successfullyAlert();
									showView(event);
								} else {
									errorAlert();
								}
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
							setGraphic(btnDelete);
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Providers/ProvidersIndex.fxml"));
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
		alert.setContentText("Eliminado con éxito");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		alert.showAndWait();
	}

	public void errorAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("Ocurrió un error");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}
}
