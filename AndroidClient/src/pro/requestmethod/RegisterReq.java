package pro.requestmethod;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import pro.connection.ConnectServer;

public class RegisterReq {
	private ConnectServer con = null;
	DataInputStream input = null;
	DataOutputStream out = null;

	public RegisterReq() {
		// TODO Auto-generated constructor stub
		con = new ConnectServer();
		input = con.getInput();
		out = con.getOut();

	}

	public String register(String name, String passwd) {
		String result = null;
		try {
			out.writeUTF("register:" + name + "," + passwd);
			result = input.readUTF();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		RegisterReq r = new RegisterReq();
		System.out.println(r.register("shaojie", "666"));
	}
}
