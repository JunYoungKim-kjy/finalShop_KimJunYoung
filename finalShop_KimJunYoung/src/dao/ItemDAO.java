package dao;

import java.util.ArrayList;
import java.util.List;

import dto.*;

public class ItemDAO {
	static private ItemDAO instance = new ItemDAO();
	
	static public ItemDAO getInstance () {
		return instance;
	}
	
	
	List<Item> iList = new ArrayList<>();
	void loadData(String data) {
		String[] list = data.split("\n");
		for(String temp:list) {
			String[]info = temp.split("/");
			iList.add(new Item(info[0],info[1],info[2],info[3]));
		}
	}
	String getData() {
		String data = "";
		for(Item d : iList) {
			data += d.getData();
		}
		return data;
	}
}
