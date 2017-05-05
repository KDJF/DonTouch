package pro.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import pro.service.UserService;

public class RquestParse {
	private DataInputStream input = null;
	private DataOutputStream out = null;
	private UserService userService = null;

	public RquestParse(DataInputStream input, DataOutputStream out) {
		this.input = input;
		this.out = out;
		userService = new UserService();
	}

	public void parseMethod(String requestString) {
		switch (requestString.split(":")[0]) {
		case "register":
			try {
				out.writeUTF(userService.register(requestString.split(":")[1].split(",")[0],
						requestString.split(":")[1].split(",")[1]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				out.writeUTF(userService.login(requestString.split(":")[1].split(",")[0],
						requestString.split(":")[1].split(",")[1]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			break;
		}
	}
}
