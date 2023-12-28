package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Cart;
import dto.Member;
import util.Util;

public class MemberDAO {
	static private MemberDAO instance = new MemberDAO();
	List<Member> mList = new ArrayList<>();
	CartDAO cDAO = CartDAO.getInstance();
	
	static public MemberDAO getInstance () {
		return instance;
	}
	
	public boolean quitMember(String id) {
		System.out.println("회원탈퇴를 하시겠습니까?");
		if(!Util.getValue("[회원탈퇴] 를 입력해주세요.").equals("회원탈퇴")) {
			return false;
		}
		deleteMember(id);
		return true;
		
	}
	void loadData(String data) {
		if(data == null) {
			mList.add(new Member("1000","admin","admin","admin"));
			return;
		}
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
		if(id.equals("admin"))return false;
		Member m = getMemberById(id);
		if(m == null)return false;
		System.out.println(m);
		cDAO.deleteListByMember(id);
		mList.remove(m);
		return true;
		
	}
	public void printMe(String id) {
		for(Member m:mList) {
			if(m.getId().equals(id)) {
				System.out.println(m);
				return;
			}
		}
	}
	public void changePw(String id) {
		Member me = getMemberById(id);
		if(!me.getPw().equals(Util.getValue("패스워드 입력:"))) {
			System.out.println("비밀번호를 확인해주세요.");
			return;
		}
		while (true) {
			String pw = Util.getValue("신규 비밀번호 입력 :");
			if (me.getPw().equals(pw)) {
				System.out.println("다른 비밀번호를 입력해주세요.");
				continue;
			}
			me.setPw(pw);
			System.out.println("비밀번호 변경완료");
			break;
		}
	}
	
}
