package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class AdminBoard implements MenuCommand {
	BoardDAO bDAO;
	MallController con;
	
	@Override
	public void init() {
		bDAO = BoardDAO.getInstance();
		con = MallController.getInstance();
		System.out.println("=============[ 관리자 게시판 ]============");
	}
	private void printMenu() {
		System.out.println("[1] 게시글목록");
		System.out.println("[2] 게시글삭제");
		System.out.println("[0] 뒤로가기");
	}

	@Override
	public boolean update() {
		printMenu();
		int sel = Util.getValue("메뉴 선택", 0, 2);
		if(sel==0) {
			con.setNext("AdminMain");
		}else if(sel == 1) {
			bDAO.printBoard();
		}else if(sel == 2) {
			bDAO.deleteBoard(con.getLoginId());
		}
		return false;
	}

}
