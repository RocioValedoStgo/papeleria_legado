package papeleria_legado.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
	private SimpleIntegerProperty id;
	private String name;
	private String last_name;
	private String username;
	private String phone;
	private SimpleStringProperty turn;
	private SimpleStringProperty rol;
	private String email;
	private String password;

	public User(int id, String name, String last_name, String username, String phone, String turn, int rol,
			String email) {
		this.id = new SimpleIntegerProperty(id);
		this.name = name;
		this.last_name = last_name;
		this.username = username;
		this.phone = phone;
		this.turn = new SimpleStringProperty(turn);
		String rolAux = null;
		if (rol == 2) {
			rolAux = "Dueño";
		} else if (rol == 3) {
			rolAux = "Cajero";
		}
		this.rol = new SimpleStringProperty(rolAux);
		this.email = email;

	}

	public int getId() {
		return id.get();
	}

	public void setId(SimpleIntegerProperty id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String string) {
		this.name = string;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTurn() {
		return turn.get();
	}

	public void setTurn(SimpleStringProperty turn) {
		this.turn = turn;
	}

	public String getRol() {
		return rol.get();
	}

	public void setRol(SimpleStringProperty rol) {
		this.rol = rol;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
