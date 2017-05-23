package pro.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectServer {
	private Socket socket = null;
	private ObjectInputStream input = null;
	private ObjectOutputStream out = null;

	public ConnectServer() {
		// TODO Auto-generated constructor stub
		try {
			this.socket = new Socket("localhost", 8888);
			this.out = new ObjectOutputStream(socket.getOutputStream());
			this.input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Á¬½Ó³¬Ê±:" + e.getMessage());
		}
	}

	public boolean closecon() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ObjectInputStream getInput() {
		return input;
	}

	public ObjectOutputStream getOut() {
		return out;
	}
	
}
