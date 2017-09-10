package socketKx;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import constants.SocketConstants;

public class PcServerMuti {

	public PcServerMuti() {
		// TODO Auto-generated constructor stub
	}

//	public static void main(String[] args) {
//		new PcServerMuti().getMsg();
//	}

	private Socket client = null;  
	
	public void getMsg() {
	    
		try {
			ServerSocket server = new ServerSocket(SocketConstants.PORT);
			while (true) {
				client = server.accept();
				// print the context that is sent by client, and say hello to
				// every client.
//				new ServerThread(client).start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
