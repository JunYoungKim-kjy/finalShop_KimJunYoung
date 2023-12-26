package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileDAO {
	
	enum FileName{
		BOARD("board.txt"),MEMBER("member.txt"),ITEM("item.txt"),CART("cart.txt");
		private String name;
		FileName(String name){
			this.name = name;
		}
		public String getName() {
			return name;
		}
	}
	private File file;
	private FileDAO () {}
	private static FileDAO instance = new FileDAO();
	String path = System.getProperty("user.dir")+"\\src\\files\\";
	public static FileDAO getInstance() {
		return instance;
	}
	public void loadAllFiles(BoardDAO bDAO,CartDAO cDAO,ItemDAO iDAO,MemberDAO mDAO) {
		String bData = loadFileData(FileName.BOARD);
		String cData = loadFileData(FileName.CART);
		String iData = loadFileData(FileName.ITEM);
		String mData = loadFileData(FileName.MEMBER);
		
		bDAO.loadData(bData);
		cDAO.loadData(cData);
		iDAO.loadData(iData);
		mDAO.loadData(mData);
		System.out.println("====[데이터 로드]====");
	}
	private void creatFile(FileName name) {
		file = new File(path + name.getName());
			try {
				file.createNewFile();
			} catch (IOException e) {
				System.out.println("파일이 이미 존재함");
			}
		
	}
	private String loadFileData(FileName name) {
		String data = "";
		try(FileReader fr = new FileReader(path + name.getName());
			BufferedReader br = new BufferedReader(fr)){
			while(true) {
				String str = br.readLine();
				if(str==null) {
					break;
				}
				data += str + "\n";
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		if(data.isEmpty())return null;
		
		data = data.substring(0,data.length()-1);
		return data;
	}
	public void saveFile(BoardDAO bDAO,CartDAO cDAO,ItemDAO iDAO,MemberDAO mDAO) {
		String bData = bDAO.getData();
		String cData = cDAO.getData();
		String iData = iDAO.getData();
		String mData = mDAO.getData();
		
		saveData(FileName.BOARD,bData);
		saveData(FileName.CART,cData);
		saveData(FileName.ITEM,iData);
		saveData(FileName.MEMBER,mData);
	}
	private void saveData(FileName name,String data) {
		try {
			FileWriter fw = new FileWriter(path + name.getName());
			fw.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
