package dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dto.*;

public class CartDAO {
	static private CartDAO instance = new CartDAO();
	
	static public CartDAO getInstance () {
		return instance;
	}
	
	List<Cart> cList = new ArrayList<>();
	void loadData(String data) {
		if(data == null)return;
		String[] list = data.split("\n");
		for(String temp:list) {
			String[]info = temp.split("/");
			cList.add(new Cart(info[0], info[1], info[2], info[3]));
		}
	}
	String getData() {
		String data = "";
		for(Cart d : cList) {
			data += d.getData();
		}
		return data;
	}
	public void deleteListByMember(String id) {
		for(int i=0; i < cList.size();i++) {
			if(cList.get(i).getId().equals(id)) {
				cList.remove(i);
				i--;
			}
		}
		System.out.println("회원 구매 내역 삭제 완료");
	}
	public void deleteListByItem(int itemNum) {
		for(int i=0; i < cList.size();i++) {
			if(cList.get(i).getItemNum()==itemNum) {
				cList.remove(i);
				i--;
			}
		}
		System.out.println("구매내역에서 삭제 완료");
	}
	public int getCntByItem(int itemNum) {
		int cnt = 0;
		for(Cart c:cList) {
			if(c.getItemNum() == itemNum) {
				cnt += c.getItemCnt();
			}
		}
		return cnt;
	}
	public void addCart(String id, int itemNum, int buyCnt) {
		cList.add(new Cart(id, itemNum, buyCnt));
	}
	private void setNum() {
		Cart max = cList.stream()
				.max(Comparator.comparing(Cart::getCartNum))
				.get();
		Item.setNum(max.getCartNum());
	}
	public void printListMemberBuied(String id, ItemDAO iDAO) {
		List<Cart> list = getListFilterId(id);
		iDAO.printMemberBuied(list);
		
	}
	private List<Cart> getListFilterId(String id) {
		List<Cart> temp = cList.stream()
				.filter(a->a.getId().equals(id))
				.collect(Collectors.toList());
		return temp;
	}

}
