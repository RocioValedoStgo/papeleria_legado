package papeleria_legado.Controllers.Categories;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.Category;
import papeleria_legado.Controllers.Categories.*;

import java.net.URL;
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
	private TableView<Category> tableCategories;

	@FXML
	private TableColumn<Category, Integer> id;

	@FXML
	private TableColumn<Category, String> name;

	@FXML
	private TableColumn<Category, String> father_id;

	@FXML
	private TableColumn<Category, String> options;

	@FXML
	void cashRegister(MouseEvent event) {

	}

	@FXML
	void categories(MouseEvent event) throws Exception {
		Index indexCategories = new Index();
		indexCategories.showView(event);
	}

	@FXML
	void home(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.HomeController home = new papeleria_legado.Controllers.HomeController();
		home.showView(event);
	}

	@FXML
	void logout(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.LoginController logout = new papeleria_legado.Controllers.LoginController();
		logout.showView(event);
	}

	@FXML
	void makeSales(MouseEvent event) {

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
	void sells(MouseEvent event) {

	}

	@FXML
	void users(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Users.Index indexUsers = new papeleria_legado.Controllers.Users.Index();
		indexUsers.showView(event);
	}

	@FXML
	void clickedCreateCategory(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Categories.Save saveCategory = new papeleria_legado.Controllers.Categories.Save();
		saveCategory.showView(event);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		father_id.setCellValueFactory(new PropertyValueFactory<>("father_id"));
		buttonShow();
		buttonEdit();
		buttonDelete();
		MySQLConnection mySQL = new MySQLConnection();
		try {
			tableCategories.setItems(mySQL.indexCategories());
		} catch (Exception e) {

		}
	}

	private void buttonShow() {
		TableColumn<Category, Void> tlButton = new TableColumn<Category, Void>();
		Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = new Callback<TableColumn<Category, Void>, TableCell<Category, Void>>() {
			@Override
			public TableCell<Category, Void> call(final TableColumn<Category, Void> param) {
				final TableCell<Category, Void> cell = new TableCell<Category, Void>() {
					private final Button btnShow = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/show-eye.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnShow.setGraphic((new ImageView(imgShow)));
						btnShow.setTooltip(new Tooltip("Ver"));
						btnShow.setOnAction((ActionEvent event) -> {
							Category category = getTableView().getItems().get(getIndex());
							Profile showCategory = new Profile();
							try {
								showCategory.setPkCategory(category.getId());
								showCategory.showView(event);
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
		TableColumn<Category, Void> tlButton = new TableColumn<Category, Void>();
		Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = new Callback<TableColumn<Category, Void>, TableCell<Category, Void>>() {
			@Override
			public TableCell<Category, Void> call(final TableColumn<Category, Void> param) {
				final TableCell<Category, Void> cell = new TableCell<Category, Void>() {
					private final Button btnEdit = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/edit.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnEdit.setGraphic((new ImageView(imgShow)));
						btnEdit.setTooltip(new Tooltip("Editar"));
						btnEdit.setOnAction((ActionEvent event) -> {
							Category category = getTableView().getItems().get(getIndex());
							Edit editCategory = new Edit();
							try {
								Edit.setPkCategory(category.getId());
								editCategory.showView(event);
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
		TableColumn<Category, Void> tlButton = new TableColumn<Category, Void>();
		Callback<TableColumn<Category, Void>, TableCell<Category, Void>> cellFactory = new Callback<TableColumn<Category, Void>, TableCell<Category, Void>>() {
			@Override
			public TableCell<Category, Void> call(final TableColumn<Category, Void> param) {
				final TableCell<Category, Void> cell = new TableCell<Category, Void>() {
					private final Button btnDelete = new Button();
					URL linkShow = getClass().getResource("/assets/images/icons/trash.png");
					Image imgShow = new Image(linkShow.toString(), 15, 15, false, true);
					{
						btnDelete.setGraphic((new ImageView(imgShow)));
						btnDelete.setTooltip(new Tooltip("Eliminar"));
						btnDelete.setOnAction((ActionEvent event) -> {
							Category category = getTableView().getItems().get(getIndex());
							MySQLConnection mySQL = new MySQLConnection();
							try {
								if (mySQL.destroyCategory(category.getId()) == 1) {
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Categories/CategoriesIndex.fxml"));
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
