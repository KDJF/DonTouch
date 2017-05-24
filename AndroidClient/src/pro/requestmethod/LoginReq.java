package pro.requestmethod;

import java.io.IOException;
import java.util.HashMap;


//µÇÂ½ÇëÇó
public class LoginReq extends ParentReq{
		
	
		public LoginReq() {
			// TODO Auto-generated constructor stub
			super();
		}
		
		public String login(String name,String passwd){
			String result = null;
			String[] requestStr = new String[2];
			HashMap<String,Object> rpro = new HashMap<String,Object>();
			try {
				rpro.put("req", "login");
				requestStr[0]=name;
				requestStr[1]=passwd;
				rpro.put("value1", requestStr);
				out.writeObject(rpro);
				//out.writeUTF("login:" + name + "," + passwd);
				result = (String) input.readObject();
				
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return result;
		}
		
		public static void main(String[] args) {
			LoginReq lr = new LoginReq();
			System.out.println(lr.login("tom","11"));
		}
}
