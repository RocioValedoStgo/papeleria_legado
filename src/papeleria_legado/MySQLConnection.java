package papeleria_legado;

import java.sql.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import papeleria_legado.Models.Category;
import papeleria_legado.Models.Product;
import papeleria_legado.Models.Provider;
import papeleria_legado.Models.User;

public class MySQLConnection {
	private static Connection connection = null;
	private PreparedStatement preparedStatement = null;

	// Credentials for connection to MySQL
	private static String dbName = "papelerialegado";
	private static String user = "root";
	private static String pwd = "";

	public static String UserFullName;
	public static String UserUsername;
	public static int UserRol;

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3307/" + dbName + "?user=" + user + "&password=" + pwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public boolean login(String username, String password) throws SQLException {
		connection = getConnection();
		ResultSet rs;
		String query = "SELECT * FROM papelerialegado.users WHERE username = ? and password = ?";
		preparedStatement = connection.prepareStatement(query);
		String pwd = passwordEncrypted(password);
		preparedStatement.setString(1, username);
		preparedStatement.setString(2, pwd);
		rs = preparedStatement.executeQuery();
		if (rs.next()) {
			setUserLogged(rs.getString("name"), rs.getString("last_name"), rs.getString("username"), rs.getInt("rol"));
			return true;
		} else {
			return false;
		}
	}

	// USERS

	public boolean register(String name, String last_name, String username, String phone, String turn, int rol,
			String email, String password) throws SQLException {
		connection = getConnection();
		int rolAux = 3;
		if (login(username, password) == true) {
			return false;
		} else {
			String pwd = passwordEncrypted(password);
			Timestamp created = generateTimestamp();
			Statement statement = connection.createStatement();
			String queryInsert = "INSERT INTO papelerialegado.users(name, last_name, username, phone, turn, rol, email, password, created_at)"
					+ "VALUES ('" + name + "','" + last_name + "','" + username + "','" + phone + "','" + turn + "','"
					+ rolAux + "','" + email + "','" + pwd + "','" + created + "')";
			statement.executeUpdate(queryInsert);

			return true;
		}
	}

	private void setUserLogged(String name, String last_name, String username, int rol) {
		UserFullName = name + " " + last_name;
		UserUsername = username;
		UserRol = rol;
	}

	public ObservableList<User> indexUsers() throws SQLException {
		connection = getConnection();
		ObservableList<User> listUsers = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT * FROM papelerialegado.users WHERE rol != 1";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listUsers.add(
					new User(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"), rs.getString("username"),
							rs.getString("phone"), rs.getString("turn"), rs.getInt("rol"), rs.getString("email")));
		}
		return listUsers;
	}

	public int editUser(int pk, String name, String last_name, String username, String phone, String turn, String email)
			throws SQLException {
		connection = getConnection();
		String queryUpdate = "UPDATE papelerialegado.users SET name=?, last_name=?, username=?, phone=?, turn=?, email=? WHERE id = ?";
		preparedStatement = connection.prepareStatement(queryUpdate);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, last_name);
		preparedStatement.setString(3, username);
		preparedStatement.setString(4, phone);
		preparedStatement.setString(5, turn);
		// preparedStatement.setInt(6, rol);
		preparedStatement.setString(6, email);
		preparedStatement.setInt(7, pk);
		return preparedStatement.executeUpdate();
	}

	public User getUser(int pk) throws SQLException {
		connection = getConnection();
		User user = null;
		ResultSet rs;
		Statement statement;
		String queryGet = "SELECT id, name, last_name, username, phone, turn, rol, email FROM papelerialegado.users WHERE id = "
				+ pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(queryGet);
		if (rs.next()) {
			user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("last_name"), rs.getString("username"),
					rs.getString("phone"), rs.getString("turn"), rs.getInt("rol"), rs.getString("email"));
			user.setName(rs.getString("name"));
			user.setLast_name(rs.getString("last_name"));
			user.setUsername(rs.getString("username"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
		}
		return user;
	}

	public int destroyUser(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String queryDelete = "DELETE FROM papelerialegado.users WHERE papelerialegado.users.id = " + id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(queryDelete);
	}

	private String passwordEncrypted(String password) {
		byte[] word = password.getBytes();
		String passwordEncrypt = Base64.getEncoder().encodeToString(word);
		return passwordEncrypt;
	}

	private Timestamp generateTimestamp() {
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		return ts;
	}

	// PROVIDERS

	public ObservableList<Provider> indexProviders() throws SQLException {
		connection = getConnection();
		ObservableList<Provider> listProviders = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT * FROM papelerialegado.providers";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listProviders.add(new Provider(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
					rs.getString("email"), rs.getString("phone")));
		}
		return listProviders;
	}

	public boolean saveProvider(String name, String address, String phone, String email) throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = connection.createStatement();
		String queryInsert = "INSERT INTO papelerialegado.providers(name, address, phone, email, created_at)"
				+ "VALUES ('" + name + "','" + address + "','" + phone + "','" + email + "','" + created + "')";
		statement.executeUpdate(queryInsert);

		return true;
	}

	public Provider getProvider(int pk) throws SQLException {
		connection = getConnection();
		Provider provider = null;
		ResultSet rs;
		Statement statement;
		String queryGet = "SELECT * FROM papelerialegado.providers WHERE id = " + pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(queryGet);
		if (rs.next()) {
			provider = new Provider(rs.getInt("id"), rs.getString("name"), rs.getString("address"),
					rs.getString("email"), rs.getString("phone"));
		}
		return provider;
	}

	public ArrayList<String> getProviders() throws SQLException {
		connection = getConnection();
		ArrayList<String> listProviders = new ArrayList<>();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name FROM papelerialegado.providers";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listProviders.add(String.valueOf(rs.getInt("id") + " " + rs.getString("name")));
		}
		return listProviders;
	}

	public int editProvider(int pk, String name, String address, String phone, String email) throws SQLException {
		connection = getConnection();
		String queryUpdate = "UPDATE papelerialegado.providers SET name=?, address=?, phone=?, email=? WHERE id = ?";
		preparedStatement = connection.prepareStatement(queryUpdate);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, address);
		preparedStatement.setString(3, phone);
		preparedStatement.setString(4, email);
		preparedStatement.setInt(5, pk);
		return preparedStatement.executeUpdate();
	}

	public int destroyProvider(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String queryDelete = "DELETE FROM papelerialegado.providers WHERE papelerialegado.providers.id = " + id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(queryDelete);
	}

	// CATEGORIES

	public ObservableList<Category> indexCategories() throws SQLException {
		connection = getConnection();
		ObservableList<Category> listCategories = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, father_id FROM papelerialegado.categories";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listCategories.add(new Category(rs.getInt("id"), rs.getString("name"), rs.getString("father_id")));
		}
		return listCategories;
	}

	public ArrayList<String> getCategories() throws SQLException {
		connection = getConnection();
		ArrayList<String> listCategories = new ArrayList<>();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name FROM papelerialegado.categories";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listCategories.add(String.valueOf(rs.getInt("id") + " " + rs.getString("name")));
		}
		return listCategories;
	}

	public boolean saveCategory(String name, int father_id, String image) throws SQLException {

		System.out.println(father_id);
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String queryInsert = "INSERT INTO papelerialegado.categories(name, father_id, image, created_at) VALUES" + " ('"
				+ name + "','" + father_id + "','" + image + "','" + created + "')";
		statement.executeUpdate(queryInsert);

		return true;
	}

	public int editCategoryImage(int pk, String name, int father_id, String image) throws SQLException {
		connection = getConnection();
		String queryUpdate = "UPDATE papelerialegado.categories SET name=?, father_id=?, image=? WHERE id = ?";
		preparedStatement = connection.prepareStatement(queryUpdate);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, father_id);
		preparedStatement.setString(3, image);
		preparedStatement.setInt(4, pk);
		return preparedStatement.executeUpdate();
	}

	public int editCategory(int pk, String name, int father_id) throws SQLException {
		connection = getConnection();
		String queryUpdate = "UPDATE papelerialegado.categories SET name=?, father_id=? WHERE id = ?";
		preparedStatement = connection.prepareStatement(queryUpdate);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, father_id);
		preparedStatement.setInt(3, pk);
		return preparedStatement.executeUpdate();
	}

	public Category getCategory(int pk) throws SQLException {
		connection = getConnection();
		Category category = null;
		ResultSet rs;
		Statement statement;
		String queryUpdate = "SELECT * FROM papelerialegado.categories WHERE id = " + pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(queryUpdate);
		if (rs.next()) {
			category = new Category(rs.getInt("id"), rs.getString("name"), rs.getString("father_id"));
			category.setImage(rs.getString("image"));
		}
		return category;
	}

	public int destroyCategory(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String queryDelete = "DELETE FROM papelerialegado.categories WHERE papelerialegado.categories.id = " + id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(queryDelete);
	}

	// PRODUCTS

	public ObservableList<Product> indexProducts() throws SQLException {
		connection = getConnection();
		ObservableList<Product> listProducts = FXCollections.observableArrayList();
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, price, quantity FROM papelerialegado.products";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			listProducts.add(
					new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity")));
		}
		return listProducts;
	}

	public ArrayList<String> getProducts() throws SQLException {
		connection = getConnection();
		ArrayList<String> products = new ArrayList<>();
		ResultSet rs;
		Statement statement;
		String query = "SELECT name FROM papelerialegado.products";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		while (rs.next()) {
			products.add(rs.getString("name"));
		}
		return products;
	}

	public boolean saveProduct(String name, String description, String image, float price, int quantity,
			int provider_id, int category_id) throws SQLException {
		connection = getConnection();
		Timestamp created = generateTimestamp();
		Statement statement = (Statement) connection.createStatement();
		String queryInsert = "INSERT INTO papelerialegado.products(name, description, image, price, quantity, provider_id, category_id, created_at) VALUES"
				+ " ('" + name + "','" + description + "','" + image + "','" + price + "','" + quantity + "','"
				+ provider_id + "','" + category_id + "','" + created + "')";
		statement.executeUpdate(queryInsert);
		return true;
	}

	public int editProductImage(int pk, String name, String description, float price, int quantity, int provider_id,
			int category_id, String image) throws SQLException {
		connection = getConnection();
		String queryUpdate = "UPDATE papelerialegado.products SET name=?, description=?, price=?, quantity=?, provider_id=?, category_id=?, image=? WHERE id = ?";
		preparedStatement = connection.prepareStatement(queryUpdate);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, description);
		preparedStatement.setFloat(3, price);
		preparedStatement.setInt(4, quantity);
		preparedStatement.setInt(5, provider_id);
		preparedStatement.setInt(6, category_id);
		preparedStatement.setString(7, image);
		preparedStatement.setInt(8, pk);
		return preparedStatement.executeUpdate();
	}

	public int editProduct(int pk, String name, String description, float price, int quantity, int provider_id,
			int category_id) throws SQLException {
		connection = getConnection();
		String queryUpdate = "UPDATE papelerialegado.products SET name=?, description=?, price=?, quantity=?, provider_id=?, category_id=? WHERE id = ?";
		preparedStatement = connection.prepareStatement(queryUpdate);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, description);
		preparedStatement.setFloat(3, price);
		preparedStatement.setInt(4, quantity);
		preparedStatement.setInt(5, provider_id);
		preparedStatement.setInt(6, category_id);
		preparedStatement.setInt(7, pk);
		return preparedStatement.executeUpdate();
	}

	public Product getProduct(int pk) throws SQLException {
		connection = getConnection();
		Product product = null;
		ResultSet rs;
		Statement statement;
		String query = "SELECT * FROM papelerialegado.products WHERE id = " + pk;
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next()) {
			product = new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
			product.setImage(rs.getString("image"));
			product.setDescription(rs.getString("description"));
			product.setcategory_id(rs.getInt("category_id"));
			product.setprovider_id(rs.getInt("provider_id"));
		}
		return product;
	}

	public Product getProduct(String name) throws SQLException {
		connection = getConnection();
		Product product = null;
		ResultSet rs;
		Statement statement;
		String query = "SELECT id, name, price, quantity FROM papelerialegado.products WHERE name = '" + name + "'";
		statement = (Statement) connection.createStatement();
		rs = statement.executeQuery(query);
		if (rs.next())
			product = new Product(rs.getInt("id"), rs.getString("name"), rs.getFloat("price"), rs.getInt("quantity"));
		return product;
	}

	public int destroyProduct(Integer id) throws SQLException {
		connection = getConnection();
		Statement statement;
		String query = "DELETE FROM papelerialegado.products WHERE papelerialegado.products.id = " + id;
		statement = (Statement) connection.createStatement();
		return statement.executeUpdate(query);
	}

	// SELLS

}
