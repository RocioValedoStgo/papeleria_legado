package papeleria_legado.Models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Sell {

	private int id;
	private float IVA;
	private float total;
	private float incoming;
	private float output;
	private int cash_id;
	private String created;

	public Sell(int id, float total, int cash_id) {
		this.id = id;
		this.total = total;
		this.cash_id = cash_id;
	}

	public Sell(int id, float total, Timestamp created) {
		this.id = id;
		this.total = total;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.created = format.format(created);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getIVA() {
		return IVA;
	}

	public void setIVA(float iVA) {
		IVA = iVA;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public float getIncoming() {
		return incoming;
	}

	public void setIncoming(float incoming) {
		this.incoming = incoming;
	}

	public float getOutput() {
		return output;
	}

	public void setOutput(float output) {
		this.output = output;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.created = format.format(created);
	}

	public int getCash_id() {
		return cash_id;
	}

	public void setCash_id(int cash_id) {
		this.cash_id = cash_id;
	}
}