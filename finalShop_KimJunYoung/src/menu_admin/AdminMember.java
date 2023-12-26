package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.*;
import util.Util;

public class AdminMember implements MenuCommand {
	MallController con;
	MemberDAO mDAO;
	CartDAO cDAO;
	@Override
	public void init() {
		con = MallController.getInstance();
		mDAO = MemberDAO.getInstance();
		cDAO = CartDAO.getInstance();
		System.out.println("=========[관리자 회원관리목록]========");
	}

	@Override
	public boolean update() {
		printMenu();
		int sel = Util.getValue("메뉴 선택", 0, 2);
		if(sel == 0) {
			con.setNext("AdminMain");
		}else if(sel == 1) {
			mDAO.printAllMember();
		}else if(sel == 2) {
			System.out.println("회원 삭제시 구매 내역이 사라집니다.");
			String id = Util.getValue("삭제할 회원 아이디 입력:");
			if(mDAO.deleteMember(id)) {
				cDAO.deleteListByMember(id);
				System.out.println("삭제 완료");
			}else{
				System.out.println("삭제 실패");
			};
		}
		
		return false;
	}
	private void printMenu() {
		System.out.println("[1] 회원목록");
		System.out.println("[2] 회원삭제");
		System.out.println("[0] 뒤로가기");
	}

}
