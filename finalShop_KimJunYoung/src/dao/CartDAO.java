package dao;

import java.util.ArrayList;
import java.util.List;

import dto.*;

public class CartDAO {
	static private CartDAO instance = new CartDAO();
	
	static public CartDAO getInstance () {
		return instance;
	}
	
	List<Cart> cList = new ArrayList<>();
	void loadData(String data) {
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
}
