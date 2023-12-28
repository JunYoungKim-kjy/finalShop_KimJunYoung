package dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.MallController;
import dto.Board;
import util.Util;

public class BoardDAO {
static private BoardDAO instance = new BoardDAO();
	
//	MallController con = MallController.getInstance();
	private int curPageNum;		//현재페이지 번호
	private int pageSize;		//한 페이지에 보여줄 게시글 수
	private int startRow;		//현재 페이지의 게시글 시작 번호
	private int endRow;			//현재 페이지의 게시글 마지막 번호
	private int pageCount;		//전체 페이지 개수
	private int count;			//전체 게시글 수
	
	public void printBoard() {
		curPageNum = 1;
		pageSize = 5;
		count = bList.size();
		while(true) {
			pageCount= count / pageSize;
			if(count % pageSize > 0 || count ==0) {
				pageCount+=1;
			}
			startRow = (curPageNum -1) * pageSize;
			endRow = startRow + pageSize;
			if(endRow > count) {
				endRow = count;
			}
			System.out.printf("총 게시글 %d개\n",count);
			System.out.printf("현재 페이지 [%d / %d]\n",curPageNum,pageCount);
			for(int i=startRow; i < endRow;i+=1) {
				System.out.printf("(%3d) [ 제목 : %s\t작성자 : %s\t작성일 : %s\t조회수 : %d]\n",i+1,bList.get(i).getTitle(),bList.get(i).getId(),bList.get(i).getDate(),bList.get(i).getHits());
			}
			printBoardMenu();
			int sel = Util.getValue("메뉴 입력", 0, 3);
			if(sel == 0) {
				return;
			}else if(sel == 1) {
				if(curPageNum == 1) {
					System.out.println("첫 페이지 입니다.");
					continue;
				}
				curPageNum -=1;
			}else if(sel == 2) {
				if(curPageNum == pageCount) {
					System.out.println("마지막 페이지 입니다.");
					continue;
				}
				curPageNum +=1;
			}else if(sel == 3) {
				int view = Util.getValue("게시글번호 입력:", startRow+1, endRow)-1;
					viewBoard(bList.get(view));
					bList.get(view).setHits(bList.get(view).getHits()+1);
					continue;
				
			}
		}
	}
	static public BoardDAO getInstance () {
		return instance;
	}
	private void printBoardMenu() {
		System.out.println("[1] 이전");
		System.out.println("[2] 이후");
		System.out.println("[3] 게시글보기");
		System.out.println("[0] 뒤로가기");
	}
	List<Board> bList = new ArrayList<Board>();
	void loadData(String data) {
		if(data == null)return;
		String[] list = data.split("\n");
		for(String temp:list) {
			String[]info = temp.split("/");
			bList.add(new Board(info[0], info[1], info[3], info[4], info[2], info[5]));
		}
//		bList.forEach(System.out::println);
	}
	
	String getData() {
		String data = "";
		for(Board b : bList) {
			data += b.getData();
		}
		return data;
	}
	private Board getBoardByBoardNum(int boardNum) {
		for(Board b : bList) {
			if(b.getBoardNum()==boardNum) {
				return b;
			}
		}
		return null;
	}
	public void deleteBoard(String id) {
		int deleteNum = Util.getValue("삭제 게시글번호 입력[0.뒤로가기]:", 0, bList.size());
		if(deleteNum==0)return;
		Board delBoard = getBoardByBoardNum(deleteNum);
		if(delBoard == null) {
			System.out.println("게시글이 존재하지 않습니다.");
			return;
		}
		if(!id.equals("admin")&&!delBoard.getId().equals(id)) {
			System.out.println("본인 게시글만 삭제할 수 있습니다.");
			return;
		}
		bList.remove(delBoard);
		System.out.println("게시글 삭제 완료");
	}
	public void addBoard(String id) {
		LocalDate date = LocalDate.now();
		String title = Util.getValue("게시글 제목 입력:");
		String contents = Util.getValue("게시글 내용 입력:");
		Board newBoard = new Board(title, id, date.toString(), contents, "0");
		bList.add(newBoard);
		System.out.println(newBoard);
	}
	private void printMyBoardList(String id) {
		System.out.println("=======내 게시글 목록=======");
		for(Board b : bList) {
			if(b.getId().equals(id)) {
				System.out.println(b);
				System.out.println("==================================");
			}
		}
	}
	public void MyBoard(String id) {
		printMyBoardList(id);
		System.out.println("[1] 삭제");
		System.out.println("[0] 뒤로가기");
		int sel = Util.getValue("선택", 0, 1);
		if(sel == 0) {
			return;
		}
		deleteBoard(id);
	}
	public void viewBoard(Board b) {
		String data = "[번호:%d] 제목 [            %s            ]\n작성자 : %s \t작성일 :%s \t조회수:%d\n===================================\n내용 : %s\n===================================\n".formatted(b.getBoardNum(),b.getTitle(),b.getId(),b.getDate(),b.getHits(),b.getContents());
		System.out.println(data);
		
	}
	

}
