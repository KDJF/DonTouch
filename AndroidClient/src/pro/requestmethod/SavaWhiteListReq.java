package pro.requestmethod;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SavaWhiteListReq extends ParentReq{
	
	public SavaWhiteListReq() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	public String savewhitelist(String username,ArrayList<String> whitelist){
		String result = null;
		HashMap<String, Object> rpro = new HashMap<String, Object>();
		try {
			rpro.put("req", "savewhitelist");
			rpro.put("value1", username);
			rpro.put("value2", whitelist);
			out.writeObject(rpro);
			// out.writeUTF("register:" + name + "," + passwd);
			result = (String) input.readObject();

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static void main(String[] args) {
		SavaWhiteListReq swr = new SavaWhiteListReq();
		ArrayList<String> whitelist = new ArrayList<String>();
		whitelist.add("1");
		whitelist.add("2");
		System.out.println(swr.savewhitelist("shaojie", whitelist));
	}
}
