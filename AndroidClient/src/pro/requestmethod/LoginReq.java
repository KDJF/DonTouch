package pro.requestmethod;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import pro.connection.ConnectServer;

public class LoginReq {
		private ConnectServer con = null;
		DataInputStream input = null;
		DataOutputStream out = null;

		public LoginReq() {
			// TODO Auto-generated constructor stub
			con = new ConnectServer();
			input = con.getInput();
			out = con.getOut();
		}
		
		public String login(String name,String passwd){
			String result = null;
			try {
				out.writeUTF("login:" + name + "," + passwd);
				result = input.readUTF();
				
			} catch (IOException e) {
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
