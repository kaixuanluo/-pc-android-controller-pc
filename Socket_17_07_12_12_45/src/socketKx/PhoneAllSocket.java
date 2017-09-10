package socketKx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.ConstantsStr;
import constants.Dimens;
import constants.SocketConstants;
import listeners.PhoneAllListeners;
import listeners.SocketListener;
import persenter.PhoneItemPresenter;
import utils.TipDialog;

public class PhoneAllSocket extends PhoneAll implements SocketListener {

	private ServerSocket mServerSocket = null;
	private Socket mSocket = null;

	// private InputStreamReader mIsr;

	public ServerSocket getServerSocket() {
		return mServerSocket;
	}

	public void setServerSocket(ServerSocket serverSocket) {
		this.mServerSocket = serverSocket;
	}

	public PhoneAllSocket() {
		// TODO Auto-generated constructor stub
	}

	public Socket getSocket() {
		return mSocket;
	}

	public void setSocket(Socket socket) {
		this.mSocket = socket;
	}

	public void initServerSocket() {

		if (mServerSocket != null && mServerSocket.isBound()) {
			System.out.println("serverSocke is already runing ");
			return;
		}

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					mServerSocket = new ServerSocket(SocketConstants.PORT);
					while (true) {
						mSocket = mServerSocket.accept();
						System.out.println("接收到了一个 socket " + mSocket);
						PhoneItem serverThread = new PhoneItem(mSocket, PhoneAllSocket.this);
						serverThread.start();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}).start();
	}

	public void socketsendMsgAll(String msg) {
		for (int i = 0; i < phoneItemList.size(); i++) {
			PhoneItem serverThread = phoneItemList.get(i);
			serverThread.sendMsg(msg);
		}
	}

	public void socketStopAll() {
		for (int i = 0; i < phoneItemList.size(); i++) {
			PhoneItem serverThread = phoneItemList.get(i);
			serverThread.stopSocket();
		}
	}

	private void socketBackAll() {
		for (int i = 0; i < phoneItemList.size(); i++) {
			PhoneItem serverThread = phoneItemList.get(i);
			serverThread.back();
		}
	}

	private void socketMenuAll() {
		for (int i = 0; i < phoneItemList.size(); i++) {
			PhoneItem serverThread = phoneItemList.get(i);
			serverThread.menu();
		}
	}

	private void socketRebootAll() {
		TipDialog.show("是否重启", ConstantsStr.CANCEL, ConstantsStr.REBOT, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < phoneItemList.size(); i++) {
					PhoneItem serverThread = phoneItemList.get(i);
					serverThread.reboot();
				}
			}
		});
	}
	
	private void socketPowerOffAll() {
		TipDialog.show("关机后需要手动开机", ConstantsStr.CANCEL, ConstantsStr.POWER_OFF, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				for (int i = 0; i < phoneItemList.size(); i++) {
					PhoneItem serverThread = phoneItemList.get(i);
					serverThread.powerOff();
				}
			}
		}
		);
	}
	
	private void socketHomeAll() {
		for (int i = 0; i < phoneItemList.size(); i++) {
			PhoneItem serverThread = phoneItemList.get(i);
			serverThread.home();
		}
	}
	
	private void socketScreenStart() {
		for (int i = 0; i < phoneItemList.size(); i++) {
			PhoneItem serverThread = phoneItemList.get(i);
			serverThread.screenStart();
		}
	}
	
	private void socketScreenStop() {
		for (int i = 0; i < phoneItemList.size(); i++) {
			PhoneItem serverThread = phoneItemList.get(i);
			serverThread.screenStop();
		}
	}

	@Override
	public void powerOffAll() {
		// TODO Auto-generated method stub
		socketPowerOffAll();
	}

	@Override
	public void reBootAll() {
		// TODO Auto-generated method stub
		socketRebootAll();
	}

	@Override
	public void backAll() {
		// TODO Auto-generated method stub
		socketBackAll();
	}

	@Override
	public void homeAll() {
		// TODO Auto-generated method stub
		socketHomeAll();
	}

	@Override
	public void installAll() {
		// TODO Auto-generated method stub
		socketsendMsgAll("安装");
	}

	@Override
	public void startMonitorAll() {
		// TODO Auto-generated method stub
		System.out.println("监控全部开始 ...");
		initServerSocket();
	}

	@Override
	public void stopMonitorAll() {
		// TODO Auto-generated method stub
		// sendAllMsg("停止监控");这个地方不能加。否则会
	}

	@Override
	public void monitorScreenOffAll() {
		// TODO Auto-generated method stub
		socketScreenStop();
	}

	@Override
	public void monitorScreenOnAll() {
		// TODO Auto-generated method stub
		socketScreenStart();
	}

	@Override
	public void socketConnected() {
		// TODO Auto-generated method stub

	}

	@Override
	public void socketError() {
		// TODO Auto-generated method stub

	}

	@Override
	public void socketClose() {
		// TODO Auto-generated method stub
		// stopAllSocket();
	}

}
