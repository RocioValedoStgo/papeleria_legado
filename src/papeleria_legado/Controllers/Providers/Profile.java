package papeleria_legado.Controllers.Providers;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.Providers.Edit;
import papeleria_legado.Models.Provider;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Profile {
	@FXML
	private Button cash_register;

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
	private Button logout;

	@FXML
	private Label titleNameProvider;

	@FXML
	private Label nameProvider;

	@FXML
	private Rectangle image;

	@FXML
	private Label name;

	@FXML
	private Label address;

	@FXML
	private Label email;

	@FXML
	private Label phone;

	private static int pkProvider;

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
	void clickedCancel(MouseEvent event) throws Exception {
		Index indexProviders = new Index();
		indexProviders.showView(event);
	}

	@FXML
	void clickedEdit(MouseEvent event) throws Exception {
		Edit editProvider = new Edit();
		Edit.setPkProvider(getPkProvider());
		editProvider.showView(event);
	}

	@FXML
	public void initialize() {
		MySQLConnection mySQL = new MySQLConnection();
		try {
			Provider provider = mySQL.getProvider(getPkProvider());
			titleNameProvider.setText("Proveedor: " + provider.getName());
			nameProvider.setText(provider.getName());
			name.setText(provider.getName());
			address.setText(provider.getAddress());
			phone.setText(provider.getPhone());
			email.setText(provider.getEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Providers/ProvidersShow.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public static int getPkProvider() {
		return pkProvider;
	}

	public static void setPkUser(int pkProvider) {
		Profile.pkProvider = pkProvider;
	}
}
