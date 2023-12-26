package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Cart;
import dto.Member;
import util.Util;

public class MemberDAO {
	static private MemberDAO instance = new MemberDAO();
	
	static public MemberDAO getInstance () {
		return instance;
	}
	
	
	List<Member> mList = new ArrayList<>();
	void loadData(String data) {
		String[] list = data.split("\n");
		for(String temp:list) {
			String[]info = temp.split("/");
			mList.add(new Member(info[0], info[1], info[2], info[3]));
		}
	}
	public void printAllMember() {
		mList.forEach(System.out::println);
	}
	
	public Member getMemberById(String id) {
		for(Member list : mList) {
			if(list.getId().equals(id))
				return list;
		}
		return null; 
	}
	public boolean insertMember(String id,String pw,String name) {
		int cnt = mList.size();
		mList.add(new Member(id, pw, name));
		if(cnt == mList.size())return false;
		else return true;
	}
	public boolean isValidMember(String id, String pw) {
		Member tryLoginMember = getMemberById(id);
		if(tryLoginMember == null || !(tryLoginMember.getPw().equals(pw))) {
			return false;
		}
		return true;
	}
	String getData() {
		String data = "";
		for(Member d : mList) {
			data += d.getData();
		}
		return data;
	}
	public boolean deleteMember(String id) {
		Member m = getMemberById(id);
		if(m == null)return false;
		System.out.println(m);
		mList.remove(m);
		return true;
		
	}
	
}
