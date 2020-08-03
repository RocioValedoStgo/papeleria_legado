package papeleria_legado.Models;

public class Sell_Detail {

	private int id;
	private Product product_id;
	private String name;
	private int quantity;
	private float subtotal;
	private int sell_id;
	private float price;
	private float total;

	public Sell_Detail(Product product_id, int quantity) {
		this.product_id = product_id;
		this.quantity = quantity;
	}

	public Sell_Detail(String name, float price, int quantity, float subtotal, float total) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.total = total;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = product_id.getPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_id() {
		return product_id.getName();
	}

	public void setProduct_id(Product product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(int quantity) {
		this.subtotal = product_id.getPrice() * quantity;
	}

	public int getSell_id() {
		return sell_id;
	}

	public void setSell_id(int sell_id) {
		this.sell_id = sell_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
}