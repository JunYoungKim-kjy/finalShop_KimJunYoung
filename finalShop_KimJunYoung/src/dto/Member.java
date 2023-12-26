package dto;

public class Member {
	private static int num = 1000;
	private int memberNum;
	private String id;
	private String pw;
	private String memberName;
	public Member(String memberNum,String id, String pw, String memberName) {
		super();
		this.memberNum = Integer.parseInt(memberNum);
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
	}
	public Member(String id, String pw, String memberName) {
		super();
		this.memberNum = ++num;
		this.id = id;
		this.pw = pw;
		this.memberName = memberName;
	}
	public static int getNum() {
		return num;
	}
	public static void setNum(int num) {
		Member.num = num;
	}
	public int getMemberNum() {
		return memberNum;
	}
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public String getMemberName() {
		return memberName;
	}
	public String getData() {
		return "%s/%s/%s/%s\n".formatted(  memberNum, id,  pw,  memberName);
	}
	@Override
	public String toString() {
		return "[%s][%8s][%8s][%8s]".formatted(memberNum,id,pw,memberName);
	}
	
	
	
}