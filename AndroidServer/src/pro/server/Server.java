package pro.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import pro.service.serviceImpl.UserServiceImpl;
import pro.utils.RequestProtocol;
import pro.utils.RquestParse;

public class Server{
    public static final int PORT = 8888;//监听的端口号 

    public static void main(String[] args) {
        System.out.println("服务器启动...\n");
        Server server = new Server();
        server.init();
    }

    public void init() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                // 一旦有堵塞, 则表示服务器与客户端获得了连接  
                Socket client = serverSocket.accept();
                // 处理这次连接  
                new HandlerThread(client);
            }
        } catch (Exception e) {
            System.out.println("服务器异常: " + e.getMessage());
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
                // 读取客户端数据  
            	ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            	ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            	
                Object clientInputStrs = input.readObject();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
                // 处理客户端数据  
                //System.out.println("客户端发过来的内容:" + clientInputStrs);
                RquestParse rp = new RquestParse(input,out,new UserServiceImpl());
                rp.parseMethod(clientInputStrs);
                out.writeObject("no anwser !!!");
                out.close();
                input.close();
            } catch (Exception e) {
                System.out.println("服务器 run 异常: " + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }
}
