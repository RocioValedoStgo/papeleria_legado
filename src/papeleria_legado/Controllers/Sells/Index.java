package papeleria_legado.Controllers.Sells;

import papeleria_legado.Models.Sell;
import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.HomeController;
import papeleria_legado.Controllers.LoginController;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

public class Index implements Initializable {

	@FXML
	private TableView<Sell> tableSells;

	@FXML
	private TableColumn<Sell, Integer> id;

	@FXML
	private TableColumn<Sell, Integer> cash_id;

	@FXML
	private TableColumn<Sell, Float> totalSell;

	@FXML
	private TableColumn<Sell, String> options;

	@FXML
	private Label date;

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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MMMMM-yyyy");
		String dateString = format.format(new Date());
		MySQLConnection mySQL = new MySQLConnection();
		try {
			id.setCellValueFactory(new PropertyValueFactory<>("id"));
			totalSell.setCellValueFactory(new PropertyValueFactory<>("total"));
			cash_id.setCellValueFactory(new PropertyValueFactory<>("cash_id"));
			tableSells.setItems(mySQL.indexSells());
			buttonShow();
			date.setText(dateString.toString());
		} catch (Exception e) {
			e.getStackTrace();
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Sells/SellsIndex.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}
}
