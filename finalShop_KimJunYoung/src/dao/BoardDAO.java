package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Board;

public class BoardDAO {
static private BoardDAO instance = new BoardDAO();
	
	static public BoardDAO getInstance () {
		return instance;
	}
	
	List<Board> bList = new ArrayList<Board>();
	void loadData(String data) {
		String[] list = data.split("\n");
		for(String temp:list) {
			String[]info = temp.split("/");
			bList.add(new Board(info[0], info[1], info[3], info[4], info[2], info[5]));
		}
//		bList.forEach(System.out::println);
	}
	
	String getData() {
		String data = "";
		for(Board b : bList) {
			data += b.getData();
		}
		return data;
	}
	
	

}
