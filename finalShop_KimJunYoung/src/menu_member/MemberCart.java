package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.*;

public class MemberCart implements MenuCommand {
	MallController con;
	CartDAO cDAO;
	ItemDAO iDAO;
	MemberDAO mDAO;
	@Override
	public void init() {
		cDAO = CartDAO.getInstance();
		iDAO = ItemDAO.getInstance();
		mDAO = MemberDAO.getInstance();
		
		con = MallController.getInstance();
		System.out.println("===========[ 구매내역 ]===========");
		
	}

	@Override
	public boolean update() {
		cDAO.printListMemberBuied(con.getLoginId(),iDAO);
		
		
		
		con.setNext("MemberMain");
		return false;
	}
}
