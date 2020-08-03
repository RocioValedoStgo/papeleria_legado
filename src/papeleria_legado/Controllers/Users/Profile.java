package papeleria_legado.Controllers.Users;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.User;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
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
	private Label titleNameUser;

	@FXML
	private Label nameUser;

	@FXML
	private Rectangle image;

	@FXML
	private Label name;

	@FXML
	private Label phone;

	@FXML
	private Label email;

	@FXML
	private Label turn;

	@FXML
	private Label username;

	@FXML
	private Label rol;

	private static int pkUser;

	@FXML
	void cancel(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Users.Index indexUsers = new papeleria_legado.Controllers.Users.Index();
		indexUsers.showView(event);
	}

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
		papeleria_legado.Controllers.HomeController home = new papeleria_legado.Controllers.HomeController();
		home.showView(event);
	}

	@FXML
	void logout(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.LoginController login = new papeleria_legado.Controllers.LoginController();
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
	void edit(MouseEvent event) throws Exception {
		Edit editUser = new Edit();
		Edit.setPkUser(getPkUser());
		editUser.showView(event);
	}

	@FXML
	public void initialize() {
		MySQLConnection mySQL = new MySQLConnection();
		try {
			User user = mySQL.getUser(getPkUser());
			titleNameUser.setText("Usuario: " + user.getName());
			nameUser.setText(user.getName() + " " + user.getLast_name());
			name.setText(user.getName() + " " + user.getLast_name());
			username.setText(user.getUsername());
			email.setText(user.getEmail());
			rol.setText(user.getRol());
			phone.setText(user.getPhone());
			turn.setText(user.getTurn());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Users/UserShow.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public static int getPkUser() {
		return pkUser;
	}

	public static void setPkUser(int pkUser) {
		Profile.pkUser = pkUser;
	}
}
