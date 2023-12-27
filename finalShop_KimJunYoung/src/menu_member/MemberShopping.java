package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.CartDAO;
import dao.ItemDAO;

public class MemberShopping implements MenuCommand {
	MallController con;
	ItemDAO iDAO;
	CartDAO cDAO;
	@Override
	public void init() {
		iDAO = ItemDAO.getInstance();
		cDAO = CartDAO.getInstance();
		con = MallController.getInstance();
		System.out.println("============ 쇼핑몰에 오신것을 환영합니다 =============");
	}

	@Override
	public boolean update() {
		iDAO.shopping(con.getLoginId(),cDAO);
		con.setNext("MemberMain");
		return false;
	}

}
