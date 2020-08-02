package papeleria_legado.Controllers.Sells;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.Sell_Detail;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

	private static int pkSell;
	private static float auxSubtotal = 0;

	@FXML
	void cashRegister(MouseEvent event) {

	}

	@FXML
	void categories(MouseEvent event) {

	}

	@FXML
	void home(MouseEvent event) {

	}

	@FXML
	void logout(MouseEvent event) {

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
	void clickedTicketGeneration(MouseEvent event) {

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

	public static int getPkSell() {
		return pkSell;
	}

	public static void setPkSell(int pkSell) {
		Ticket.pkSell = pkSell;
	}
}
