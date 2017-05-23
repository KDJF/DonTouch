package pro.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import pro.service.serviceImpl.UserServiceImpl;
import pro.utils.RequestProtocol;
import pro.utils.RquestParse;

public class Server{
    public static final int PORT = 8888;//�����Ķ˿ں� 

    public static void main(String[] args) {
        System.out.println("����������...\n");
        Server server = new Server();
        server.init();
    }

    public void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                // һ���ж���, ���ʾ��������ͻ��˻��������  
                Socket client = serverSocket.accept();
                // �����������  
                new HandlerThread(client);
            }
        } catch (Exception e) {
            System.out.println("�������쳣: " + e.getMessage());
        }
    }

    private class HandlerThread implements Runnable {
        private Socket socket;
        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        public void run() {
            try {
                // ��ȡ�ͻ�������  
            	ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            	
                Object clientInputStrs = input.readObject();//����Ҫע��Ϳͻ����������д������Ӧ,������� EOFException
                // ����ͻ�������  
                //System.out.println("�ͻ��˷�����������:" + clientInputStrs);
                RquestParse rp = new RquestParse(input,out,new UserServiceImpl());
                rp.parseMethod(clientInputStrs);
                out.writeObject("no anwser !!!");
                out.close();
                input.close();
            } catch (Exception e) {
                System.out.println("������ run �쳣: " + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("����� finally �쳣:" + e.getMessage());
                    }
                }
            }
        }
    }
}
