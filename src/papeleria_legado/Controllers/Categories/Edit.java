package papeleria_legado.Controllers.Categories;

import papeleria_legado.MySQLConnection;
import papeleria_legado.Models.Category;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Edit {

	@FXML
	private Button logout;

	@FXML
	private Label lblTitleCategory;

	@FXML
	private Rectangle border;

	@FXML
	private TextField name;

	@FXML
	private Label lblNameImage;

	@FXML
	private ComboBox<String> father_id;

	@FXML
	private Label lblCategory;

	@FXML
	private ImageView image;

	private static int pkCategory;
	private boolean band = false;
	private Stage stage;
	private File imgFile;
	private FileChooser fileChooser;
	private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
	private static final String NUMBER = "0123456789";
	private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
	private static SecureRandom random = new SecureRandom();
	private static String nameImage;

	@FXML
	void cashRegister(MouseEvent event) {

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
	void clickedCancel(MouseEvent event) throws Exception {
		Index indexCategory = new Index();
		indexCategory.showView(event);
	}

	@FXML
	public void initialize() {
		MySQLConnection mySQL = new MySQLConnection();
		try {
			Category category = mySQL.getCategory(getPkCategory());
			lblTitleCategory.setText("Editando categoria: " + category.getName());
			lblCategory.setText(category.getName());
			name.setText(category.getName());
			father_id.setValue(category.getFather_Id());
			father_id.getItems().addAll(mySQL.getCategories());
			String pathImg = System.getProperty("user.dir") + "\\src\\assets\\images\\categories\\"
					+ category.getImage() + ".jpg";
			nameImage = category.getImage();
			lblNameImage.setText(nameImage);
			File imgFile = new File(pathImg);
			Image img = new Image("file:" + imgFile.getAbsolutePath());
			image.setImage(img);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void clickedUpdate(MouseEvent event) throws SQLException, Exception {
		if (name.equals(null)) {
			warningAlert();
		} else {
			MySQLConnection mySQL = new MySQLConnection();
			if (band) {
				destroyImage(nameImage);
				String imageName = saveImage(imgFile);
				if (mySQL.editCategoryImage(getPkCategory(), name.getText(),
						Integer.parseInt(father_id.getValue().substring(0, 1)), imageName) == 1) {
					successfullyAlert();
					Profile profileCategory = new Profile();
					profileCategory.setPkCategory(getPkCategory());
					profileCategory.showView(event);
				} else {
					errorAlert();
				}
			} else {
				if (mySQL.editCategory(getPkCategory(), name.getText(),
						Integer.parseInt(father_id.getValue().substring(0, 1))) == 1) {
					successfullyAlert();
					Profile profileCategory = new Profile();
					profileCategory.setPkCategory(getPkCategory());
					profileCategory.showView(event);
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
		fileChooser.setTitle("Buscar imagen para la nueva categoría");
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("All Images", "*.*"));
		imgFile = fileChooser.showOpenDialog(stage);
		if (imgFile != null) {
			Image img = new Image("file:" + imgFile.getAbsolutePath());
			image.setImage(img);
			band = true;
		} else {
			warningImageAlert();
		}
	}

	private String saveImage(File imgFile) throws IOException {
		String name = generateRandomString(8);
		String pathDestination = System.getProperty("user.dir") + "\\src\\assets\\images\\categories\\" + name + ".jpg";
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
		String pathDestination = System.getProperty("user.dir") + "\\src\\assets\\images\\categories\\" + nameFile
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
			Alert alert = new Alert(AlertType.WARNING);
			alert.setHeaderText(null);
			alert.setContentText("Ocurrió un error");
			alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
			alert.showAndWait();
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
		Parent root = FXMLLoader.load(getClass().getResource("../../../views/Categories/CategoriesUpdate.fxml"));
		Scene scene = new Scene(root);
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		appStage.getIcons().add(new Image("/assets/images/legado_papeleria.jpeg"));
		appStage.setScene(scene);
		appStage.toFront();
		appStage.show();
	}

	public static int getPkCategory() {
		return pkCategory;
	}

	public static void setPkCategory(int pkCategory) {
		Edit.pkCategory = pkCategory;
	}

	public void successfullyAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setContentText("Categoria editada exitosamente");
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
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(null);
		alert.setContentText("Ocurrió un error al editar la categoría");
		alert.setGraphic(new ImageView(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(this.getClass().getResource("/assets/images/icons/error.png").toString()));
		alert.showAndWait();
	}
}
