package dto;

public class Item implements Comparable<Item>{
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
	public static void setNum(int num) {
		Item.num = num;
	}
	public String getData() {
		return "%s/%s/%s/%s\n".formatted( itemNum, categoryName,  itemName,  price);
	}
	
	
	@Override
	public String toString() {
		return "[%3d][%5s][%5s][%8s]".formatted(itemNum,categoryName,itemName,price);
	}
	@Override
	public int compareTo(Item o) {
		if(this.getCategoryName().compareTo(o.getCategoryName()) > 0)
			return 1;
		else if(this.getCategoryName().compareTo(o.getCategoryName()) < 0)
			return -1;
		else if(this.itemName.compareTo(o.itemName) > 0)
			return 1;
		else if(this.itemName.compareTo(o.itemName) < 0)
			return -1;
		else return 0;
	}
	
	
	
}