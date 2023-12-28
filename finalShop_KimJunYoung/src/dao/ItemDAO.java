package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dto.*;
import util.Util;

class SellCnt implements Comparable<SellCnt>{
	int num;
	String cate;
	String name;
	int price;
	int cnt;
	public SellCnt(int num, String cate, String name, int price, int cnt) {
		super();
		this.num = num;
		this.cate = cate;
		this.name = name;
		this.price = price;
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "[%3d][%5s][%5s][%7d원][%d개]".formatted(num,cate,name,price,cnt);
	}
	@Override
	public int compareTo(SellCnt o) {
		if(this.cnt < o.cnt) {
			return 1;
		}else if(this.cnt > o.cnt) {
			return -1;
		}

		return 0;
	}
	
	
}

public class ItemDAO {
	static private ItemDAO instance = new ItemDAO();
	static public ItemDAO getInstance () {
		return instance;
	}
	
	List<String> cateList = new ArrayList<String>();
	List<Item> iList = new ArrayList<>();
	private void init() {
		
	}
	private void setNum() {
		Item max = iList.stream()
				.max(Comparator.comparing(Item::getItemNum))
				.get();
		Item.setNum(max.getItemNum());
	}
	void loadData(String data) {
		if(data == null)return;
		String[] list = data.split("\n");
		for(String temp:list) {
			String[]info = temp.split("/");
			iList.add(new Item(info[0],info[1],info[2],info[3]));
			cateList.add(info[1]);
		}
		cateList = cateList.stream()
				.distinct()
				.collect(Collectors.toList());
	}
	String getData() {
		String data = "";
		for(Item d : iList) {
			data += d.getData();
		}
		return data;
	}
	public void addItem() {
		printSrotedCate();
		String name = Util.getValue("아이템 입력:");
		if(hasName(name)) {
			System.out.println("이미 존재하는 카테고리/아이템 이름 입니다");
			return;
		}
		String cate = Util.getValue("카테고리 입력:");
		int price = Util.getValue("가격", 100, 10000000);
		iList.add(new Item(cate, name, price+""));
		cateList.add(cate);
		System.out.println("아이템 추가 완료");
	}
	private void printSrotedCate() {
		System.out.println("=========카테고리 아이템 목록========");
		iList.stream()
		.sorted()
		.forEach(System.out::println);
	}
	public int getDeleteItemNum() {
		printSrotedCate();
		System.out.println("[ 아이템 삭제시 구매 내역이 사라집니다 [0.뒤로가기]");
		int itemNum = Util.getValue("삭제할 아이템 번호 입력",0,iList.size());
		if(itemNum ==0) {
			return 0;
		}
		for(Item delItem : iList) {
			if(delItem.getItemNum() == itemNum) {
				iList.remove(delItem);
				return itemNum;
			}
		}
		return 0;
		
	}
	public void printSellCnt(CartDAO cDAO) {
		List<SellCnt> scList = new ArrayList<SellCnt>();
		int num = 1;
		for(Item list : iList) {
			int cnt = cDAO.getCntByItem(list.getItemNum());
			scList.add(new SellCnt(list.getItemNum(), list.getCategoryName(), list.getItemName(), list.getPrice(), cnt));
		}
		scList.stream().sorted().forEach(System.out::println);
		
	}
	private boolean hasName(String name) {
		if(cateList.stream().filter(a->a.equals(name)).count()>=1
		|| iList.stream().filter(a->a.getItemName().equals(name)).count()>=1) {
			return true;
		}else
			return false;

	}
	public void shopping(String id,CartDAO cDAO) {
//		cateList.forEach(System.out::println)
		printCate();
		System.out.println("[0] 뒤로가기");
		int idx = Util.getValue("메뉴 입력", 0, cateList.size())-1;
		if(idx == -1)return;
		printItemByCate(cateList.get(idx).toString());
		while (true) {
			String name = Util.getValue("구매 아이템 이름 입력 :");
			Item buyItem = getItemByName(name);
			if (buyItem == null) {
				System.out.println("아이템 이름 오류 다시 입력 해주세요.");
				continue;
			}
			if(!buyItem.getCategoryName().equals(cateList.get(idx).toString())) {
				System.out.println("해당 카테고리에 없는 아이템입니다. 다시 입력해주세요.");
				continue;
			}
			int buyCnt = Util.getValue("아이템 구매 수량", 1, 100);
			
			cDAO.addCart(id,buyItem.getItemNum(),buyCnt);
			System.out.printf("   %s %d개 구매 완료\n",name,buyCnt);
			break;
		}
	}
	private Item getItemByName(String name) {
		for(Item i : iList) {
			if(i.getItemName().equals(name))
				return i;
		}
		return null;
	}
	private void printItemByCate(String cate) {
		System.out.printf("[ %s의 아이템 목록 ]\n",cate);
		int num = 1;
		for(Item i : iList) {
			if(i.getCategoryName().equals(cate)) {
				System.out.printf("[%d] %s %d원\n",num++,i.getItemName(),i.getPrice());
			}
		}
	}
	private void printCate() {
		int num = 1;
		for(String cate : cateList) {
			System.out.printf("[%d] %s\n",num++,cate);
		}
	}
	public void printMemberBuied(List<Cart> cList) {
		int num=1;
		int totalSum=0;
		int totalCnt=0;
		for(Item i: iList) {
			int cnt = 0;
			int sum = 0;
			for(Cart c : cList) {
				if(i.getItemNum() == c.getItemNum()) {
					cnt += c.getItemCnt();
				}
			}
			if(cnt == 0 )continue;
			sum += i.getPrice() * cnt;
			System.out.printf("[%3d]\t%s(%d원) %d개 총 %d원 \n",num++,i.getItemName(),i.getPrice(),cnt,sum);
			totalSum += sum;
			totalCnt += cnt;
		}
		System.out.println("==============");
		System.out.printf("총 %d개 ( %d원 )\n",totalCnt,totalSum);
	}
}
