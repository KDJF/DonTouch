package pro.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
		RequestProtocol rpro = (RequestProtocol)requestStr;
		switch (rpro.getReq()) {
		case "register":
			try {
				String[] strs = (String[])rpro.getObj();
				out.writeObject(userService.register(strs[0], strs[1]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "login":
			try {
				String[] strs = (String[])rpro.getObj();
				out.writeObject(userService.login(strs[0], strs[1]));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			break;
		}
	}
}
