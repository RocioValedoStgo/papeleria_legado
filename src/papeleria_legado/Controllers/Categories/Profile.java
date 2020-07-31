package papeleria_legado.Controllers.Categories;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Controllers.HomeController;
import papeleria_legado.Controllers.Categories.*;
import papeleria_legado.Models.Category;

import java.io.File;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Profile {

	@FXML
	private Label titleNameCategory;

	@FXML
	private Label nameCategory;

	@FXML
	private Rectangle image;

	@FXML
	private Label name;

	@FXML
	private Label father_id;

	@FXML
	private ImageView imageView;

	private static int pkCategory;

	@FXML
	void cashRegister(MouseEvent event) throws Exception {

	}

	@FXML
	void categories(MouseEvent event) throws Exception {
		Index indexCategory = new Index();
		indexCategory.showView(event);
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
	void makeSales(MouseEvent event) throws Exception {

	}

	@FXML
	void products(MouseEvent event) throws Exception {

	}

	@FXML
	void providers(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Providers.Index indexProviders = new papeleria_legado.Controllers.Providers.Index();
		indexProviders.showView(event);
	}

	@FXML
	void sells(MouseEvent event) throws Exception {

	}

	@FXML
	void users(MouseEvent event) throws Exception {
		papeleria_legado.Controllers.Users.Index indexUsers = new papeleria_legado.Controllers.Users.Index();
		indexUsers.showView(event);
	}

	@FXML
	public void initialize() {
		MySQLConnection mySQL = new MySQLConnection();
		try {
			Category category = mySQL.getCategory(getPkCategory());
			titleNameCategory.setText("Categoría: " + category.getName());
			nameCategory.setText(category.getName());
			name.setText(category.getName());
			father_id.setText(category.getFather_Id());
			String pathImg = System.getProperty("user.dir") + "\\src\\assets\\images\\categories\\"
					+ category.getImage() + ".jpg";
			File imgFile = new File(pathImg);
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			imageView.setImage(image);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void clickedCancel(MouseEvent event) throws Exception {
		Index indexCategory = new Index();
		indexCategory.showView(event);
	}

	@FXML
	void clickedEdit(MouseEvent event) throws Exception {
		Edit editCategory = new Edit();
		editCategory.setPkCategory(getPkCategory());
		editCategory.showView(event);
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Categories/CategoriesShow.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public int getPkCategory() {
		return pkCategory;
	}

	public void setPkCategory(int pkCategory) {
		Profile.pkCategory = pkCategory;
	}
}
