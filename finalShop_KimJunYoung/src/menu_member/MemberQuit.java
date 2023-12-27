package menu_member;

import _mall.MenuCommand;
import controller.MallController;
import dao.*;
public class MemberQuit implements MenuCommand {
	MallController con;
	MemberDAO mDAO;
	@Override
	public void init() {
		con = MallController.getInstance();
		mDAO = MemberDAO.getInstance();
		
		System.out.println("=======[회원 탈퇴]=======");
	}

	@Override
	public boolean update() {
		if(mDAO.quitMember(con.getLoginId())){
			con.setLoginId(null);
			con.setNext("MallMain");
		}else {
			con.setNext("MemberMain");
		}
		return false;
	}

}
