package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.BoardDAO;
import util.Util;

public class MemberBoard implements MenuCommand {
	MallController con;
	BoardDAO bDAO;
	@Override
	public void init() {
		con = MallController.getInstance();
		bDAO = BoardDAO.getInstance();
		System.out.println("==========[ 게시판 ]===========");
	}

	@Override
	public boolean update() {
		printMenu();
		int sel = Util.getValue("메뉴 입력", 0, 4);
		if( sel == 0) {
			con.setNext("MallMain");
		}else if (sel == 1) {
			bDAO.printBoard();
		}else if (sel == 2) {
			System.out.println("[ 게시글 추가하기 ]");
			bDAO.addBoard(con.getLoginId());
		}else if (sel == 3) {
			bDAO.MyBoard(con.getLoginId());
		}
		return false;
	}

	private void printMenu() {
		System.out.println("[1] 게시글 보기");
		System.out.println("[2] 게시글 추가");
		System.out.println("[3] 내 게시글(삭제)");
		System.out.println("[0] 뒤로가기");
	}
}
