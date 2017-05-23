package pro.requestmethod;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import pro.connection.ConnectServer;

//ÇëÇóµÄ¸¸Àà
public class ParentReq {
	protected ConnectServer con = null;
	protected ObjectInputStream input = null;
	protected ObjectOutputStream out = null;

	public ParentReq() {
		// TODO Auto-generated constructor stub
		con = new ConnectServer();
		input = con.getInput();
		out = con.getOut();
	}
}
