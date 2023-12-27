package dto;

public class Board {
	private static int num;
	private int boardNum;
	private String title;
	private String id;
	private String date;
	private String contents;
	private int hits;
	
	public Board() {}

	public Board(String boardNum, String title, String id, String date, String contents, String hits) {
		super();
		this.boardNum = Integer.parseInt(boardNum);
		this.title = title;
		this.id = id;
		this.date = date;
		this.contents = contents;
		this.hits = Integer.parseInt(hits);
	}
	public Board(String title, String id, String date, String contents, String hits) {
		super();
		this.boardNum = ++num;
		this.title = title;
		this.id = id;
		this.date = date;
		this.contents = contents;
		this.hits = Integer.parseInt(hits);
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public static int getNum() {
		return num;
	}

	public int getBoardNum() {
		return boardNum;
	}

	public String getTitle() {
		return title;
	}

	public String getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getContents() {
		return contents;
	}
	public String getData() {
		return "%s/%s/%s/%s/%s/%s\n".formatted( boardNum, title,  id,  date,  contents,  hits);
	}
	@Override
	public String toString() {
		return "[%d][제목:%s   작성자:%s  날짜 : %s  조회수 %d]".formatted(boardNum,title,id,date,hits);
		
		}
	
	
	
	
}
