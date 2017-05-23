package pro.requestmethod;

import java.io.IOException;
import pro.utils.RequestProtocol;

//×¢²áÇëÇó
public class RegisterReq extends ParentReq {

	public RegisterReq() {
		// TODO Auto-generated constructor stub
		super();
	}

	public String register(String name, String passwd) {
		String result = null;
		String[] requestStr = new String[2];
		RequestProtocol rpro = new RequestProtocol();
		try {
			rpro.setReq("register");
			requestStr[0] = name;
			requestStr[1] = passwd;
			rpro.setObj(requestStr);
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
		RegisterReq r = new RegisterReq();
		System.out.println(r.register("shaojie", "666"));
	}
}
