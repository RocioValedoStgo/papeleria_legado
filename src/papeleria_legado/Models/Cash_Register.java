package papeleria_legado.Models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Cash_Register {
	private int id;
	private float total;
	private boolean status;
	private String close;
	private String created;

	public Cash_Register(float total, boolean status, Timestamp close) {
		this.total = total;
		this.status = status;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.close = format.format(close);
	}

	public Cash_Register(int id, float total, boolean status, Timestamp close, Timestamp created) {
		this.id = id;
		this.total = total;
		this.status = status;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		this.close = format.format(close);
		this.created = format.format(created);
	}

	public Cash_Register(int id, float total) {
		this.id = id;
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getClose() {
		return close;
	}

	public void setClose(Timestamp close) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.close = format.format(close);
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.created = format.format(created);
	}
}