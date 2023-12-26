package dto;

public class Item {
	private static int num;
	private int itemNum;
	private String categoryName;
	private String itemName;
	private int price;
	public Item(String itemNum,String categoryName, String itemName, String price) {
		super();
		this.itemNum = Integer.parseInt(itemNum);
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = Integer.parseInt(price);
	}
	public Item(String categoryName, String itemName, String price) {
		super();
		this.itemNum = ++num;
		this.categoryName = categoryName;
		this.itemName = itemName;
		this.price = Integer.parseInt(price);
	}
	public int getItemNum() {
		return itemNum;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public String getItemName() {
		return itemName;
	}
	public int getPrice() {
		return price;
	}
	public String getData() {
		return "%s/%s/%s/%s\n".formatted( itemNum, categoryName,  itemName,  price);
	}
	
	
	
}