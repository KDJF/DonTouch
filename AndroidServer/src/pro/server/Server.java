package pro.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import pro.serviceImpl.UserServiceImpl;
import pro.utils.RquestParse;

public class Server {
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
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                String clientInputStr = input.readUTF();//����Ҫע��Ϳͻ����������д������Ӧ,������� EOFException
                // ����ͻ�������  
                System.out.println("�ͻ��˷�����������:" + clientInputStr);
                RquestParse rp = new RquestParse(input,out,new UserServiceImpl());
                rp.parseMethod(clientInputStr);
                
                out.writeUTF("no anwser");
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
