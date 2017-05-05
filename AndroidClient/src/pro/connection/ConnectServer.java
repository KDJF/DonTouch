package pro.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectServer {
	Socket socket = null;
	DataInputStream input = null;
	DataOutputStream out = null;

	public ConnectServer() {
		// TODO Auto-generated constructor stub
		try {
			this.socket = new Socket("172.27.171.228", 8888);
			this.input = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public DataInputStream getInput() {
		return input;
	}

	public DataOutputStream getOut() {
		return out;
	}
	
}
