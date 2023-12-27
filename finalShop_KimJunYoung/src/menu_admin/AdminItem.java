package menu_admin;

import _mall.MenuCommand;
import controller.MallController;
import dao.*;
import util.Util;

public class AdminItem implements MenuCommand {
	ItemDAO iDAO; CartDAO cDAO;
	MallController con;
	@Override
	public void init() {
		iDAO = ItemDAO.getInstance();
		cDAO = CartDAO.getInstance();
		con = MallController.getInstance();
		System.out.println("================[ 관리자 쇼핑몰관리 ]=============");
		System.out.println("카테고리 순으로 정렬 카테고리가 같으면 아이템 이름순으로 정렬");
	}
	private void printMenu() {
		System.out.println("[1] 아이템 추가");
		System.out.println("[2] 아이템 삭제");
		System.out.println("[3] 총 매출 아이템 갯수 출력(판매량 높은순으로)");
		System.out.println("[0] 뒤로가기");
	}

	@Override
	public boolean update() {
		printMenu();
		int sel = Util.getValue("메뉴 선택", 0, 3);
		if(sel == 0) {
			con.setNext("AdminMain");
		}else if(sel == 1) {
			iDAO.addItem();
		}else if(sel == 2) {
			int itemNum=iDAO.getDeleteItemNum();
			if(itemNum!=0) {
				cDAO.deleteListByItem(itemNum);
				System.out.println("아이템 삭제 완료");
			}else {
				System.out.println("뒤로가기");
			}
		}else if(sel == 3) {
			iDAO.printSellCnt(cDAO);
		}
		return false;
	}

}
