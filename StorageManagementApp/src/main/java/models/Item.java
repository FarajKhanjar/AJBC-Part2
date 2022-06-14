package models;

import java.time.LocalDate;
import java.util.Objects;

public class Item 
{
	private int id;
	private String itemName;
	private float unitPrice;
	private String purchaseDate;
	private int quantity;
	
	public Item()
	{
		
	}
	
	public Item(String itemName, float unitPrice, String purchaseDate, int quantity) 
	{
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.purchaseDate = purchaseDate;
		this.quantity = quantity;
	}
	
	public Item(int id, String itemName, float unitPrice, String purchaseDate, int quantity) 
	{
		this.id = id;
		this.itemName = itemName;
		this.unitPrice = unitPrice;
		this.purchaseDate = purchaseDate;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", unitPrice=" + unitPrice + ", purchaseDate="
				+ purchaseDate + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, itemName, purchaseDate, quantity, unitPrice);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		return id == other.id && Objects.equals(itemName, other.itemName)
				&& Objects.equals(purchaseDate, other.purchaseDate) && quantity == other.quantity
				&& Float.floatToIntBits(unitPrice) == Float.floatToIntBits(other.unitPrice);
	}
	
	

}
