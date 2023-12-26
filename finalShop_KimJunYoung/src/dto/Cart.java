package dto;

public class Cart {
	private static int num;
	private int cartNum;
	private String id;
	private int itemNum;
	private int itemCnt;
	
	public Cart(String cartNum, String id, String itemNum, String itemCnt) {
		super();
		this.cartNum = Integer.parseInt(cartNum);
		this.id = id;
		this.itemNum = Integer.parseInt(itemNum);
		this.itemCnt = Integer.parseInt(itemCnt);
	}
	public Cart(String id,int itemNum) {
		this.cartNum = ++num;
		this.id = id;
		this.itemNum = itemNum;
	}
	public static int getNum() {
		return num;
	}
	public static void setNum(int num) {
		Cart.num = num;
	}
	public int getItemCnt() {
		return itemCnt;
	}
	public void setItemCnt(int itemCnt) {
		this.itemCnt = itemCnt;
	}
	public int getCartNum() {
		return cartNum;
	}
	public String getId() {
		return id;
	}
	public int getItemNum() {
		return itemNum;
	}
	public String getData() {
		return "%s/%s/%s/%s\n".formatted(  cartNum,  id,  itemNum,  itemCnt);
	}
	
	
	
	
	
	
	
}
