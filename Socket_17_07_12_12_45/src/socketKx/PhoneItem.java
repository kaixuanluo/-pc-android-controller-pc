package socketKx;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bean.MouseBean;
import bean.ScreenBean;
import constants.ConstantsStr;
import constants.Dimens;
import constants.SocketConstants;
import listeners.PhoneItemListener;
import main.PlayerPanel;
import utils.MouseJsonGenerator;
import utils.ScreenJsonGenerator;
import utils.TipDialog;

public class PhoneItem extends Thread implements PhoneItemListener {

	private InputStream mIs = null;
	private OutputStream mOs = null;
	private Socket mSocket = null;
	private DataInputStream mDis;
	private DataOutputStream mDos;

	private boolean mIsLoop = true;

	private JPanel mJpItem;
	private JLabel mJlImg;
	private PlayerPanel mJpPlayer;
	private PhoneAllSocket mPas;
	private String mIp = "";
	// private boolean isWriteOk = true;

	private JLabel jpInfo = new JLabel();

	// private PrintStream mPos;

	public JPanel getmJpItem() {
		return mJpItem;
	}

	public void setmJpItem(JPanel mJpItem) {
		this.mJpItem = mJpItem;
	}

	public String getmIp() {
		return mIp;
	}

	public void setmIp(String mIp) {
		this.mIp = mIp;
	}

	private int i = 0;

	public PhoneItem(Socket client, PhoneAllSocket pas) {
		// TODO Auto-generated constructor stub
		this.mPas = pas;
		try {
			this.mIs = client.getInputStream();
			this.mOs = client.getOutputStream();
			this.mDis = new DataInputStream(mIs);
			this.mDos = new DataOutputStream(mOs);
			this.mSocket = client;
			this.mSocket.setKeepAlive(true);
			this.mIp = mSocket.getInetAddress().getHostAddress();
			// this.mPos = new PrintStream(mOs, true);
		} catch (IOException e) {
			e.printStackTrace();
			socketError();
		}

		updateScreenContent();
	}

	@Override
	public void run() {
		// try {
		// while (mIsLoop) {
		// if (i == 0) {
		// i++;
		//// String readUTF = mDis.readUTF();
		//// Gson gson = new Gson();
		//// PhoneInfoBean info = gson.fromJson(readUTF, PhoneInfoBean.class);
		//// String content = " "+ info.getName()+" "+ info.getVersion()+ " " +
		// mIp;
		//// jpInfo.setText(content);
		//// System.out.println("内容 ");
		//// jpInfo.updateUI();
		// } else {
		// int len = mDis.available();
		// byte[] data = new byte[len];
		// mDis.read(data);
		//
		// ByteArrayOutputStream outPut = new ByteArrayOutputStream();
		// BufferedImage read = ImageIO.read(mDis);
		// if (read == null) {
		// // 没有连接了.掉线了.
		// socketError();
		// return;
		// }
		// ImageIcon icon = new ImageIcon(read);
		// icon.setImage(icon.getImage().getScaledInstance(mJlImg.getWidth(),
		// mJlImg.getHeight(),
		// Image.SCALE_DEFAULT));
		// mJlImg.setIcon(icon);
		// mJlImg.updateUI();
		// }
		// }
		// } catch (IOException e) {
		// e.printStackTrace();
		// socketError();
		// }
	}

	private float downxDp, downyDp, upxDp, upyDp;

	public class MyMouseEvent implements MouseListener {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

			int x = e.getX();
			int y = e.getY();
			float xDp = (float) x / (float) Dimens.PHONE_WIDTH;
			float yDp = (float) y / (float) Dimens.PHONE_HEIGHT;

			if (e.getButton() == e.BUTTON1) {
				// outStr = "左键";

				upxDp = xDp;
				upyDp = yDp;

				if (Math.abs(xDp - downxDp) > 0.2 || Math.abs(yDp - downyDp) > 0.2) {
					sendMsg(MouseJsonGenerator.generateMouse(MouseBean.LEFT_SWIPE, downxDp, downyDp, xDp, yDp));
				}

			} else if (e.getButton() == e.BUTTON3) {

			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX();
			int y = e.getY();
			float xDp = (float) x / (float) Dimens.PHONE_WIDTH;
			float yDp = (float) y / (float) Dimens.PHONE_HEIGHT;

			downxDp = xDp;
			downyDp = yDp;

			if (e.getButton() == e.BUTTON1) {

			} else if (e.getButton() == e.BUTTON3) {

			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			int x = e.getX();
			int y = e.getY();
			float xDp = (float) x / (float) Dimens.PHONE_WIDTH;
			float yDp = (float) y / (float) Dimens.PHONE_HEIGHT;
			if (e.getButton() == e.BUTTON1) {
				// outStr = "左键";
				if (Math.abs(xDp - downxDp) > 0.05 || Math.abs(yDp - downyDp) > 0.05) {
				} else {
					System.out.println("左键 点击的x " + x + " 点击的 y  " + y + "  xdp  " + xDp + " ydp " + yDp);
					sendMsg(MouseJsonGenerator.generateMouse(MouseBean.LEFT_CLICK, xDp, yDp));
				}
			} else if (e.getButton() == e.BUTTON3) {
				// outStr = "右键";
				System.out.println(" 右键 点击的x " + x + " 点击的 y  " + y + "  xdp  " + xDp + " ydp " + yDp);
				sendMsg(MouseJsonGenerator.generateMouse(MouseBean.RIGHT_CLICK, xDp, yDp));
			}
		}

		public void mouseDragged(MouseEvent e) {
			System.out.println("x坐标:" + e.getX() + "  y坐标:" + e.getY());
		}
	}

	int touch = 0;

	public class MyMouseMotionEvent extends MouseMotionAdapter {
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseDragged(e);
			int x = e.getX();
			int y = e.getY();
			float xDp = (float) x / (float) Dimens.PHONE_WIDTH;
			float yDp = (float) y / (float) Dimens.PHONE_HEIGHT;

//			touch++;
//			if (touch == 5) {
//				sendMsg(MouseJsonGenerator.generateMouse(MouseBean.LEFT_TOUCH, xDp, yDp));
//				touch = 0;
//			}
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			super.mouseMoved(e);

		}

	}

	public void sendMsg(String msg) {
		if (mDos == null) {
			// initServerSocket();
			System.out.println("mdos is null");
			return;
		}
		// if (!isWriteOk) {
		// System.err.println("还未写完,请稍后...");
		// return;
		// }

		// new Thread(new Runnable() {
		// // java.lang.NegativeArraySizeException 长度为-1导致
		// @Override
		// public void run() {
		// // TODO Auto-generated method stub
		try {
			// isWriteOk = false;
			mDos.writeUTF(msg);
			mDos.flush();
			System.out.println("发送消息给客户 " + msg);

			// System.out.println("客户端返回的消息:"+mDis.readUTF());
			// sleep(300);
			// isWriteOk = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.err.println("发送消息错误...");
			socketError();
		}
		// catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// }
		// }).start();
	}

	public void stopSocket() {
		mIsLoop = false;
		// mJpPhoneList.remove(mJpItem);
		// mJpPhoneList.updateUI();
		try {
			mPas.removePhone(mIp, this);
			if (mSocket != null) {
				mSocket.close();
			}
			if (mDis != null) {
				mDis.close();
			}
			if (mDos != null) {
				mDos.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// socketError();
		}
	}

	@Override
	public void home() {
		// TODO Auto-generated method stub
		sendMsg(MouseJsonGenerator.generateMouse(MouseBean.HOME, 0, 0));
	}

	@Override
	public void back() {
		// TODO Auto-generated method stub
		sendMsg(MouseJsonGenerator.generateMouse(MouseBean.BACK, 0, 0));
	}

	@Override
	public void powerOff() {
		// TODO Auto-generated method stub
		sendMsg(MouseJsonGenerator.generateMouse(MouseBean.POWER_OFF, 0, 0));
	}

	@Override
	public void reboot() {
		// TODO Auto-generated method stub
		sendMsg(MouseJsonGenerator.generateMouse(MouseBean.REBORT, 0, 0));
	}

	@Override
	public void updateScreenContent() {
		// TODO Auto-generated method stub
		URL bgUrl = (this.getClass().getClassLoader().getResource("image/bg.jpg"));

		ImageIcon bgimg = new ImageIcon(bgUrl);
		int phoneWidth = Dimens.PHONE_WIDTH;
		int phoneHeight = Dimens.PHONE_HEIGHT;

		// JButton jBt = new JButton("第 " + i + " 个");
		// jBt.setPreferredSize(new Dimension(270, 480));
		mJpItem = new JPanel();
		mJpItem.setBackground(Color.white);
		mJpItem.setLayout(new BoxLayout(mJpItem, BoxLayout.Y_AXIS));
		JPanel jpPhone = new JPanel();
		jpPhone.setPreferredSize(new Dimension(phoneWidth, phoneHeight));
		// jpPhone.setBackground(Color.blue);
		mJlImg = new JLabel(bgimg);
		mJlImg.setPreferredSize(new Dimension(phoneWidth, phoneHeight));
		// 把标签的大小位置设置为图片刚好填充整个面板
		mJlImg.setBounds(0, 0, phoneWidth, phoneHeight);
		// // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明
		// JPanel jp_window = (JPanel) this.getContentPane();
		// jp_window.setOpaque(false);// 设置面板的透明度
		mJlImg.addMouseListener(new MyMouseEvent());
		mJlImg.addMouseMotionListener(new MyMouseMotionEvent());

		jpPhone.add(mJlImg);

		jpPhone.setLayout(new BorderLayout());
		jpPhone.setVisible(true);
		// mJpItem.add(jpPhone);

		mJpItem.setVisible(true);
		mJpPlayer = new PlayerPanel(mSocket.getInetAddress().getHostAddress(), SocketConstants.PORT_VIDEO);
		mJpPlayer.setVisible(true);
		mJpPlayer.getPlayCanvas().addMouseListener(new MyMouseEvent());
		mJpPlayer.getPlayCanvas().addMouseMotionListener(new MyMouseMotionEvent());

		mJpItem.add(mJpPlayer);

		Font font = new Font("宋体", Font.BOLD, 16);
		jpInfo.setForeground(Color.GRAY);
		jpInfo.setFont(font);
		jpInfo.setSize(new Dimension(phoneWidth, Dimens.MAIN_INFO_LABLE));
		jpInfo.setVisible(true);
		mJpItem.add(jpInfo);

		JPanel jpMenu = new JPanel();
		jpMenu.setPreferredSize(new Dimension(phoneWidth, Dimens.PHONE_MENU_HEIGHT));
		List<String> jpMenuList = new ArrayList<String>();
		jpMenuList.add(ConstantsStr.POWER_OFF);
		jpMenuList.add(ConstantsStr.REBOT);
		jpMenuList.add(ConstantsStr.MENU);
		jpMenuList.add(ConstantsStr.HOME);
		jpMenuList.add(ConstantsStr.BACK);
		jpMenu.setLayout(new GridLayout(1, jpMenuList.size()));
		for (int j = 0; j < jpMenuList.size(); j++) {
			String btStr = jpMenuList.get(j);
			JButton jbMenu = new JButton(btStr);
			jbMenu.setMargin(new Insets(0, 0, 0, 0));
			jbMenu.setForeground(Color.GRAY);
			jbMenu.setFont(new Font("宋体", Font.BOLD, 16));
			jbMenu.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					switch (btStr) {

					case ConstantsStr.POWER_OFF:
						TipDialog.show("关机后需要手动开机", ConstantsStr.CANCEL, ConstantsStr.POWER_OFF, new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								powerOff();
							}
						});
						break;
					case ConstantsStr.REBOT:
						TipDialog.show("是否重启", ConstantsStr.CANCEL, ConstantsStr.REBOT, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								reboot();
							}
						});
						break;
					case ConstantsStr.MENU:
						menu();
						break;
					case ConstantsStr.BACK:
						back();
						break;
					case ConstantsStr.HOME:
						home();
						break;
					default:
						break;
					}

				}
			});
			jpMenu.add(jbMenu);
		}
		jpMenu.setBackground(Color.black);
		mJpItem.add(jpMenu);
		mJpItem.setBounds(new Rectangle(Dimens.BOUND_10, Dimens.BOUND_10));

		mJpItem.addComponentListener(new ComponentListener() {

			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void componentShown(ComponentEvent e) {
				// TODO Auto-generated method stub
				screenStart();// 判断当前item是否可见...
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				screenStop();
			}
		});

		mPas.addPhone(mIp, this);
	}

	@Override
	public void menu() {
		// TODO Auto-generated method stub
		sendMsg(MouseJsonGenerator.generateMouse(MouseBean.RIGHT_CLICK, 0, 0));
	}

	@Override
	public void socketError() {
		// TODO Auto-generated method stub
		System.err.println("socket is error...");
		stopSocket();
	}

	@Override
	public void socketClose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mediaPlay() {
		// TODO Auto-generated method stub
		if (mJpPlayer != null) {
			mJpPlayer.playMedia();
		}
	}

	@Override
	public void mediaStop() {
		// TODO Auto-generated method stub
		if (mJpPlayer != null) {
			mJpPlayer.stopMedia();
		}
	}

	@Override
	public void screenStart() {
		// TODO Auto-generated method stub
		sendMsg(ScreenJsonGenerator.generateScrreenJson(ScreenBean.START));
	}

	@Override
	public void screenStop() {
		// TODO Auto-generated method stub
		sendMsg(ScreenJsonGenerator.generateScrreenJson(ScreenBean.STOP));
	}
}
