package papeleria_legado.Controllers.Products;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.Product;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.sql.SQLException;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Edit {

	@FXML
	private Label titleProduct;

	@FXML
	private TextField name;

	@FXML
	private Label nameImage;

	@FXML
	private TextArea description;

	@FXML
	private TextField price;

	@FXML
	private TextField quantity;

	@FXML
	private ComboBox<String> provider_id;

	@FXML
	private ComboBox<String> category_id;

	@FXML
	private ImageView imageView;

	@FXML
	private Label lblNameProduct;

	private static int pkProduct;
	private boolean band = false;
	private Stage stage;
	private File imgFile;
	private FileChooser fileChooser;
	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();
	private static String nameImg;

	@FXML
	void cashRegister(MouseEvent event) {

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
		papeleria_legado.Controllers.LoginController logout = new papeleria_legado.Controllers.LoginController();
		logout.showView(event);
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
	void cancel(MouseEvent event) throws Exception {
		Index indexProducts = new Index();
		indexProducts.showView(event);
	}

	@FXML
	public void initialize() {

		MySQLConnection mySQL = new MySQLConnection();
		try {
			Product product = mySQL.getProduct(getPkProduct());
			titleProduct.setText("Editando producto: " + product.getName());
			lblNameProduct.setText(product.getName());
			name.setText(product.getName());
			description.setText(product.getDescription());
			price.setText(String.valueOf(product.getPrice()));
			quantity.setText(String.valueOf(product.getQuantity()));
			provider_id.setValue(String.valueOf(product.getProvider_Id()));
			category_id.setValue(String.valueOf(product.getCategory_Id()));

			provider_id.getItems().addAll(mySQL.getProviders());
			category_id.getItems().addAll(mySQL.getCategories());

			String pathImg = System.getProperty("user.dir") + "\\src\\assets\\images\\products\\" + product.getImage()
					+ ".jpg";
			nameImg = product.getImage();
			File imgFile = new File(pathImg);
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			imageView.setImage(image);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void update(MouseEvent event) throws IOException, Exception, SQLException {
		if (name.getText().equals(null) || price.getText().equals(null) || description.getText().equals(null)
				|| quantity.getText().equals(null) || provider_id.getValue().equals(null)) {
			warningAlert();
		} else {
			MySQLConnection mySQL = new MySQLConnection();
			if (band) {
				destroyImage(nameImg);
				String imgName = saveImage(imgFile);
				if (mySQL.editProductImage(getPkProduct(), name.getText(), description.getText(),
						Float.parseFloat(price.getText()), Integer.parseInt(quantity.getText()),
						Integer.parseInt(provider_id.getValue().substring(0, 1)),
						Integer.parseInt(category_id.getValue().substring(0, 1)), imgName) == 1) {
					successfullyAlert();
					Profile profileProduct = new Profile();
					profileProduct.setPkProduct(getPkProduct());
					profileProduct.showView(event);
				} else {
					errorAlert();
				}

			} else {
				if (mySQL.editProduct(getPkProduct(), name.getText(), description.getText(),
						Float.parseFloat(price.getText()), Integer.parseInt(quantity.getText()),
						Integer.parseInt(provider_id.getValue().substring(0, 1)),
						Integer.parseInt(category_id.getValue().substring(0, 1))) == 1) {
					successfullyAlert();
					Profile profileProduct = new Profile();
					profileProduct.setPkProduct(getPkProduct());
					profileProduct.showView(event);
				} else {
					errorAlert();
				}
			}
		}
	}

	@FXML
	void uploadImages(MouseEvent event) {
		stage = new Stage();
		fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar imagen para el producto");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.*"));
		imgFile = fileChooser.showOpenDialog(stage);
		if (imgFile != null) {
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			imageView.setImage(image);
			band = true;
		} else {
			warningImageAlert();
		}
	}

	private String saveImage(File imgFile) throws IOException {
		String name = generateRandomString(8);
		String pathDestination = System.getProperty("user.dir") + "\\src\\assets\\images\\products\\" + name + ".jpg";
		Path copiedFile = Files.copy(FileSystems.getDefault().getPath(imgFile.getAbsolutePath()),
				FileSystems.getDefault().getPath(pathDestination), StandardCopyOption.REPLACE_EXISTING);
		if (Files.exists(copiedFile)) {
			return name;
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setContentText("Ocurrió un error al guardar la imagen");
			alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
			alert.showAndWait();
		}
		return null;
	}

	private boolean destroyImage(String nameFile) throws IOException {
		String pathDestination = System.getProperty("user.dir") + "\\src\\assets\\images\\products\\" + nameFile
				+ ".jpg";
		if (Files.deleteIfExists(FileSystems.getDefault().getPath(pathDestination))) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText(null);
			alert.setContentText("Imagen eliminada");
			alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
			alert.showAndWait();
			return true;
		} else {
			return false;
		}
	}

	private static String generateRandomString(int length) {
		if (length < 1)
			throw new IllegalArgumentException();
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			// 0-62 (exclusive), random returns 0-61
			int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
			char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
			// debug
			sb.append(rndChar);
		}
		return sb.toString();
	}

	public void showView(Event event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Products/ProductsUpdate.fxml"));
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

	public static void setPkProduct(int pk) {
		Edit.pkProduct = pk;
	}

	public void successfullyAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Producto editado exitosamente");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/ok.png").toString()));
		alert.showAndWait();
	}

	public void warningAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("Los campos se encuentran vacíos, favor de llenarlos");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/warning.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/warning.png").toString()));
		alert.showAndWait();
	}

	public void warningImageAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("Debe seleccionar una imagen");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/warning.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/warning.png").toString()));
		alert.showAndWait();
	}

	public void errorAlert() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText(null);
		alert.setContentText("Ocurrió un error al editar el producto");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}
}
