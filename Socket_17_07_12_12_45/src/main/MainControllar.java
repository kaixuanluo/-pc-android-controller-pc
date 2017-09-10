 package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import com.sun.corba.se.pept.transport.ContactInfoList;
import com.sun.jndi.toolkit.url.Uri;
import com.sun.jndi.toolkit.url.UrlUtil;

import baseView.BaseControllar;
import constants.ConstantsStr;
import constants.ControllarList;
import constants.Dimens;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import listeners.ChatItemListener;
import persenter.ChatAllPresenter;
import persenter.ChatItemPresenter;
import utils.TipDialog;
import utils.WrapFlowLayout;

public class MainControllar extends BaseControllar<ChatAllPresenter, ChatItemPresenter> implements ActionListener {
	JPanel jpController, jpPhoneList;

	WrapFlowLayout mgr = new WrapFlowLayout();

	JScrollPane jSPPhoneList, jSpController;

	List<String> mControllList;

	ChatAllPresenter preSenterAll;

	public void initAll() {

		preSenterAll = getAllPresenter();
		preSenterAll.initServerSocketAll();

		initControllerView();
		initSocket(); 
		initPhoneListView();

		preSenterAll.startMonitorAll();

		mgr.setAlignment(FlowLayout.LEFT);
		mgr.setVgap(Dimens.BOUND_20);// 设置组件之间的垂直边距
		mgr.setHgap(Dimens.BOUND_30);// 设置组件之间的水平边距

		JSplitPane jpSp = new JSplitPane();
		jpSp.add(jSpController, JSplitPane.LEFT);
		jpSp.add(jSPPhoneList, JSplitPane.RIGHT);

		this.add(jpSp);

		// 设置显示
		this.setSize(Dimens.MAIN_WIDTH, Dimens.MAIN_HEIGHT);
		// this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setTitle(ConstantsStr.MAIN_TITLE);

		// 也就是说一定要在主窗体setVisible(true)之后再使用setDividerLocation(double)才会有效。
		jpSp.setDividerLocation(0.2);

	}

	public MainControllar() {
		// TODO Auto-generated constructor stub
	}

	private void init() {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new MainControllar().initAll();
	}

	private void initControllerView() {

		jpController = new JPanel();

		mControllList = ControllarList.getControllarList();

		for (int i = 0; i < mControllList.size(); i++) {
			JButton jpControllerBt = new JButton(mControllList.get(i));
			jpControllerBt.addActionListener(this);
			jpControllerBt.setPreferredSize(new Dimension(Dimens.CONTROLLER_MENU_WIDTH, Dimens.CONTROLLER_MENU_HEIGHT));
			jpControllerBt.setMargin(new Insets(0, 0, 0, 0));
			Font font = new Font("宋体", Font.BOLD, 16);
			jpControllerBt.setForeground(Color.GRAY);
			jpControllerBt.setFont(font);
			// jpControllerBt.setMinimumSize(new Dimension(60, 60));
			jpController.add(jpControllerBt);
		}

		jpController.setLayout(mgr);

		jSpController = new JScrollPane(jpController, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	}

	private void initPhoneListView() {

		jpPhoneList = new JPanel();

		jpPhoneList.setLayout(mgr);

		getAllPresenter().initPhoneListAll(jpPhoneList, getItemPresenter());

		jSPPhoneList = new JScrollPane(jpPhoneList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		// //这样设置的水平，垂直都有滚动条

	}

	private void initSocket() {
		getAllPresenter().initServerSocketAll();
	}

	@Override
	public ChatAllPresenter initAllPresenter() {
		// TODO Auto-generated method stub
		return new ChatAllPresenter();
	}

	@Override
	public ChatItemPresenter initItemPresenter() {
		// TODO Auto-generated method stub
		return new ChatItemPresenter();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String btContent = ((JButton) e.getSource()).getText();

		switch (btContent) {
		case ConstantsStr.REBOT_ALL:
			preSenterAll.reBootAll();
			break;
		case ConstantsStr.POWER_OFF_ALL:
			preSenterAll.powerOffAll();
			break;
		case ConstantsStr.BACK_ALL:
			preSenterAll.backAll();
			break;
		case ConstantsStr.HOME_ALL:
			preSenterAll.homeAll();
			break;
		case ConstantsStr.MONITOR_START:
			preSenterAll.startMonitorAll();
			break;
		case ConstantsStr.MONITOR_STOP:
			preSenterAll.stopMonitorAll();
			break;
		case ConstantsStr.MONITOR_SCREEN_ON:
			preSenterAll.monitorScreenOnAll();
			break;
		case ConstantsStr.MONITOR_SCREEN_OFF:
			preSenterAll.monitorScreenOffAll();
			break;
		case ConstantsStr.WECHAT_AUTO_HI:
			preSenterAll.weChatHelloAll();
			break;
		case ConstantsStr.WECHAT_AUTO_REPLY:
			preSenterAll.weChatAutoReplyAll();
			break;
		case ConstantsStr.WECHAT_GROUP:
			preSenterAll.weChatGroupAll();
			break;
		case ConstantsStr.MOMO_AUTO_HI:
			preSenterAll.moMoHelloAll();
			break;
		case ConstantsStr.MOMO_AUTO_REPLY:
			preSenterAll.moMOAutoReplyAll();
			break;
		case ConstantsStr.MOMO_GROUP:
			preSenterAll.moMoGroupAll();
			break;

		default:
			break;
		}
	}

}
