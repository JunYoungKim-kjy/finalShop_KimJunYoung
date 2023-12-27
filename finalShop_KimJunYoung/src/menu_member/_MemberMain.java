package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import util.Util;

public class _MemberMain implements MenuCommand {
	MallController con;
	@Override
	public void init() {
		con = MallController.getInstance();
		System.out.printf("==============[ 회원 %s님 ]============\n",con.getLoginId());
	}

	@Override
	public boolean update() {
		printMenu();
		int sel = Util.getValue("메뉴 입력", 0, 6);
		if(sel == 0) {
			con.setNext("");
		}else if(sel == 1) {
			con.setNext("MemberShopping");
		}else if(sel == 2) {
			con.setNext("MemberCart");
		}else if(sel == 3) {
			con.setNext("MemberBoard");
		}else if(sel == 4) {
			con.setNext("MemberInfo");
		}else if(sel == 5) {
			con.setNext("MemberQuit");
		}else if(sel == 6) {
			con.setLoginId(null);
			con.setNext("MallMain");
		}
		
		
		
		
		
		return false;
	}
	private void printMenu() {
		System.out.println("[1] 상품구매");
		System.out.println("[2] 구매내역");
		System.out.println("[3] 게시판");
		System.out.println("[4] 나의 정보");
		System.out.println("[5] 회원 탈퇴");
		System.out.println("[6] 로그아웃");
		System.out.println("[0] 종료");
		System.out.println("====================");
		
	}

}
