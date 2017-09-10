package socketKx;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class PCClient {
	
	private static boolean isLooper = true;

//	public static void main(String[] args) throws IOException {
//		System.out.println("请在下面输入需要");
//		Scanner scanner = new Scanner(System.in);
//		while (isLooper) {
//			String msg = scanner.next();
//			
//			sendToast(msg);
//
////			new Thread(new Runnable() {
////				public void run() {
////					try {
////						sendToast(msg);
////					} catch (IOException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
////				}
////			}) {
////
////			}.start();
//		}
//	}

	public static void sendToast(String msg) throws IOException {
		// Socket socket = new Socket("127.0.0.1", 8000);

		Socket socket = new Socket("192.168.3.8", 8000);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeUTF(msg);
		socket.close();
	}
}
