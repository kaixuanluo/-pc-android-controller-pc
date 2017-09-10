package socketKx;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PCServer {

	private boolean isLoop = true;
	private static final int PORT = 9003;

	public PCServer() {
		// TODO Auto-generated constructor stub
	}

//	public static void main(String[] args) {
//		new PCServer().initServerSocket();
//	}

	public void initServerSocket() {
		ServerSocket serverSocket = null;
		Socket socket = null;
		List<String> clientIpList = new ArrayList<String>();
		try {
			serverSocket = new ServerSocket(PORT);
			while (isLoop) {
				socket = serverSocket.accept();//这里会阻塞
				InetAddress inetAddress = socket.getInetAddress();
				
				DataInputStream inputStream = new DataInputStream(socket.getInputStream());
				DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
				
//				String clientInfo = "欢迎  " + inetAddress +" 给我 发消息";
////				System.err.println(clientInfo);
//				outputStream.writeUTF(clientInfo);
				
//				outputStream.flush();
				
				String msg = inputStream.readUTF();//这里会阻塞
				System.out.println("msg " + msg);
//				socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		
		} finally {
			System.out.println("destory");

			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void getMsg2() {
		new ServerThread().start();
	}

	class ServerThread extends Thread {

	        boolean isLoop = true;

	        public void setIsLoop(boolean isLoop) {
	            this.isLoop = isLoop;
	        }

	        @Override
	        public void run() {
	        	 
	  try {  
           ServerSocket server = new ServerSocket(PORT);  
           Socket client = server.accept();  
           BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));  
           System.out.println("client say : " + br.readLine());  
             
//           BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));  
//           bw.write("Hello client, this is server...");  
//           bw.write("\r\n");  
//           bw.flush();  
       } catch (IOException e) {  
           e.printStackTrace();  
       }  
	        }
	}
}
