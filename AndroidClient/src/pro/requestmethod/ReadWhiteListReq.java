package pro.requestmethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadWhiteListReq extends ParentReq{
	public ReadWhiteListReq() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<String> readwhitelist(String name){
		ArrayList<String> whitelist = null;
		HashMap<String, Object> rpro = new HashMap<String, Object>();
		rpro.put("req", "readwhitelist");
		rpro.put("value1", name);
		try {
			out.writeObject(rpro);
			whitelist = (ArrayList<String>) input.readObject();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return whitelist;
	}
	
	public static void main(String[] args) {
		ReadWhiteListReq rwlr = new ReadWhiteListReq();
		ArrayList<String> list = rwlr.readwhitelist("tpc");
		System.out.println(list);
	}
}
