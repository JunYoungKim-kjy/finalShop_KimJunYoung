package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.*;
import util.Util;

public class _AdminMain implements MenuCommand {
	MallController con;
	MemberDAO mDAO;	FileDAO fDAO; CartDAO cDAO;ItemDAO iDAO;BoardDAO bDAO;
	
	@Override
	public void init() {
		con = MallController.getInstance();
		mDAO = MemberDAO.getInstance();
		fDAO = FileDAO.getInstance();
		cDAO = CartDAO.getInstance();
		iDAO = ItemDAO.getInstance();
		
	}

	@Override
	public boolean update() {
		printMenu();
		int sel = Util.getValue("메뉴 입력", 0, 5);
		if(sel==0) {
			con.setNext("MallMain");
		}else if(sel==1) {
			con.setNext("AdminMember");
		}else if(sel==2) {
			con.setNext("AdminItem");
		}else if(sel==3) {
			con.setNext("AdminBoard");
		}else if(sel==4) {
			con.setLoginId(null);
			con.setNext("MallMain");
		}else if(sel==5) {
			fDAO.saveFile(bDAO,cDAO,iDAO,mDAO);
		}
		return false;
	}
	
	
	private void printMenu() {
		System.out.println("==========[ 관리자 ]==========");
		System.out.println("[1] 회원관리");
		System.out.println("[2] 상품관리");
		System.out.println("[3] 게시판관리");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 파일저장");
		System.out.println("[0] 종료");
	}

}
