package pro.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import pro.service.UserService;

public class RquestParse {
	private ObjectInputStream input = null;
	private ObjectOutputStream out = null;
	private UserService userService = null;

	public RquestParse(ObjectInputStream input, ObjectOutputStream out, UserService useServiceImpl) {
		this.input = input;
		this.out = out;
		userService = useServiceImpl;
	}

	public void parseMethod(Object requestStr) {
		HashMap<String, Object> rpro = (HashMap<String, Object>) requestStr;
		switch ((String) rpro.get("req")) {
		case "register":
			try {
				String[] strs = (String[]) rpro.get("value");
				out.writeObject(userService.register(strs[0], strs[1]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				String[] strs = (String[]) rpro.get("value");
				out.writeObject(userService.login(strs[0], strs[1]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "savewhitelist":
			String username = (String)rpro.get("value1");
			ArrayList<String> whitelist = (ArrayList<String>)rpro.get("value2");
			try {
				out.writeObject(userService.savawhitelist(username, whitelist));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
}
