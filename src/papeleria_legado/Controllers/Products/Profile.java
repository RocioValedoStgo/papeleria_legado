package papeleria_legado.Controllers.Products;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.Product;

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
import javafx.stage.Stage;

public class Profile {

	@FXML
	private Label titleNameProduct;

	@FXML
	private Label nameProducto;

	@FXML
	private Label name;

	@FXML
	private Label description;

	@FXML
	private Label price;

	@FXML
	private Label category_id;

	@FXML
	private Label quantity;

	@FXML
	private Label provider_id;

	@FXML
	private ImageView imageView;

	private static int pkProduct;

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
	void products(MouseEvent event) throws Exception {
		Index indexProducts = new Index();
		indexProducts.showView(event);
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
		Index indexProducts = new Index();
		indexProducts.showView(event);
	}

	@FXML
	void clickedEdit(MouseEvent event) throws Exception {
		Edit editProduct = new Edit();
		Edit.setPkProduct(getPkProduct());
		editProduct.showView(event);
	}

	@FXML
	public void initialize() {
		MySQLConnection mySQL = new MySQLConnection();
		try {
			Product product = mySQL.getProduct(getPkProduct());
			titleNameProduct.setText("Producto: " + product.getName());
			nameProducto.setText(product.getName());
			name.setText(product.getName());
			description.setText(product.getDescription());
			price.setText(String.valueOf(product.getPrice()));
			quantity.setText(String.valueOf(product.getQuantity()));
			provider_id.setText(String.valueOf(product.getProvider_Id()));
			category_id.setText(String.valueOf(product.getCategory_Id()));

			String pathImg = System.getProperty("user.dir") + "\\src\\assets\\images\\products\\" + product.getImage()
					+ ".jpg";
			File imgFile = new File(pathImg);
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			imageView.setImage(image);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Products/ProductsShow.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public int getPkProduct() {
		return pkProduct;
	}

	public static void setPkProduct(int pkProduct) {
		Profile.pkProduct = pkProduct;
	}
}
