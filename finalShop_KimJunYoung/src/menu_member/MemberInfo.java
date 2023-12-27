package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.MemberDAO;
import util.Util;

public class MemberInfo implements MenuCommand {
	MallController con;
	MemberDAO mDAO;
	@Override
	public void init() {
		con = MallController.getInstance();
		mDAO = MemberDAO.getInstance();
		System.out.println("============[ 내 정보 ]=============");
	}

	@Override
	public boolean update() {
		System.out.println("[1] 비밀번호변경");
		System.out.println("[0] 뒤로가기");
		mDAO.printMe(con.getLoginId());
		int sel = Util.getValue("메뉴 선택", 0, 1);
		if(sel == 0) {
			con.setNext("MemberMain");
		}else {
			mDAO.changePw(con.getLoginId());
		}
		return false;
	}

}
