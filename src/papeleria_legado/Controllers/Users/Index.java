package papeleria_legado.Controllers.Users;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.User;
import papeleria_legado.Controllers.Users.Profile;
import papeleria_legado.Controllers.Users.Edit;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.util.Callback;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

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
	private TableView<User> tableUsers;

	@FXML
	private TableColumn<User, Integer> id;

	@FXML
	private TableColumn<User, String> name;

	@FXML
	private TableColumn<User, String> last_name;

	@FXML
	private TableColumn<User, String> turn;

	@FXML
	private TableColumn<User, Integer> rol;

	@FXML
	private TableColumn<User, String> email;

	@FXML
	private TableColumn<User, String> options;

	@FXML
	void cashRegister(MouseEvent event) {

	}

	@FXML
	void categories(MouseEvent event) {

	}

	@FXML
	void home(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.HomeController home = new papeleria_legado.Controllers.HomeController();
		home.showView(event);
	}

	@FXML
	void logout(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.LoginController login = new papeleria_legado.Controllers.LoginController();
		login.showView(event);
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
	void createUser(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Users.Save saveUser = new papeleria_legado.Controllers.Users.Save();
		saveUser.showView(event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		last_name.setCellValueFactory(new PropertyValueFactory<>("last_name"));
		turn.setCellValueFactory(new PropertyValueFactory<>("turn"));
		rol.setCellValueFactory(new PropertyValueFactory<>("rol"));
		email.setCellValueFactory(new PropertyValueFactory<>("email"));
		buttonShow();
		buttonEdit();
		buttonDelete();
		// buttonChangePassword();
		MySQLConnection mySQL = new MySQLConnection();
		try {
			tableUsers.setItems(mySQL.indexUsers());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void buttonShow() {
		TableColumn<User, Void> tlButton = new TableColumn<User, Void>();
		Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
			@Override
			public TableCell<User, Void> call(final TableColumn<User, Void> param) {
				final TableCell<User, Void> cell = new TableCell<User, Void>() {
					private final Button btnShow = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/show-eye.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnShow.setGraphic((new ImageView(imgShow)));
						btnShow.setTooltip(new Tooltip("Ver"));
						btnShow.setOnAction((ActionEvent event) -> {
							User user = getTableView().getItems().get(getIndex());
							Profile showUser = new Profile();
							try {
								Profile.setPkUser(user.getId());
								showUser.showView(event);
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
		TableColumn<User, Void> tlButton = new TableColumn<User, Void>();
		Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
			@Override
			public TableCell<User, Void> call(final TableColumn<User, Void> param) {
				final TableCell<User, Void> cell = new TableCell<User, Void>() {
					private final Button btnEdit = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/edit.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnEdit.setGraphic((new ImageView(imgShow)));
						btnEdit.setTooltip(new Tooltip("Editar"));
						btnEdit.setOnAction((ActionEvent event) -> {
							User user = getTableView().getItems().get(getIndex());
							Edit editUser = new Edit();
							try {
								Edit.setPkUser(user.getId());
								editUser.showView(event);
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
		TableColumn<User, Void> tlButton = new TableColumn<User, Void>();
		Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
			@Override
			public TableCell<User, Void> call(final TableColumn<User, Void> param) {
				final TableCell<User, Void> cell = new TableCell<User, Void>() {
					private final Button btnDelete = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/trash.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnDelete.setGraphic((new ImageView(imgShow)));
						btnDelete.setTooltip(new Tooltip("Eliminar"));
						btnDelete.setOnAction((ActionEvent event) -> {
							User user = getTableView().getItems().get(getIndex());
							MySQLConnection mySQL = new MySQLConnection();
							try {
								if (mySQL.destroyUser(user.getId()) == 1) {
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

	private void buttonChangePassword() {
		TableColumn<User, Void> tlButton = new TableColumn<User, Void>();
		Callback<TableColumn<User, Void>, TableCell<User, Void>> cellFactory = new Callback<TableColumn<User, Void>, TableCell<User, Void>>() {
			@Override
			public TableCell<User, Void> call(final TableColumn<User, Void> param) {
				final TableCell<User, Void> cell = new TableCell<User, Void>() {
					private final Button btnChangePassword = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/resetPassword.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnChangePassword.setGraphic((new ImageView(imgShow)));
						btnChangePassword.setTooltip(new Tooltip("Editar contraseña"));
						btnChangePassword.setOnAction((ActionEvent event) -> {
							User user = getTableView().getItems().get(getIndex());

							try {

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
							setGraphic(btnChangePassword);
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Users/UserIndex.fxml"));
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
