package persenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import constants.ConstantsStr;
import constants.Dimens;
import listeners.PhoneAllListeners;
import socketKx.PCServer;
import socketKx.PCSocketServer2;
import socketKx.PhoneItem;
import utils.TipDialog;

public abstract class PhoneAllPresenter implements PhoneAllListeners{

	private PCSocketServer2 mPcServer;
	
	public PCSocketServer2 getmPcServer() {
		return mPcServer;
	}

	public void setmPcServer(PCSocketServer2 mPcServer) {
		this.mPcServer = mPcServer;
	}
	
	@Override
	public void initServerSocketAll() {
		// TODO Auto-generated method stub
		mPcServer = new PCSocketServer2();
	}

	public PhoneAllPresenter() {
		// TODO Auto-generated constructor stub
		
	}
	
	@Override
	public void powerOffAll() {
		// TODO Auto-generated method stub
		mPcServer.powerOffAll();
	}

	@Override
	public void reBootAll() {
		// TODO Auto-generated method stub
		mPcServer.reBootAll();
	}

	@Override
	public void backAll() {
		// TODO Auto-generated method stub
		mPcServer.backAll();
	}

	@Override
	public void homeAll() {
		// TODO Auto-generated method stub
		mPcServer.homeAll();
	}

	@Override
	public void installAll() {
		// TODO Auto-generated method stub
		mPcServer.installAll();
	}
	
	@Override
	public void startMonitorAll() {
		// TODO Auto-generated method stub
		mPcServer.startMonitorAll();
	}

	@Override
	public void stopMonitorAll() {
		// TODO Auto-generated method stub
		mPcServer.stopMonitorAll();
	}

	@Override
	public void monitorScreenOffAll() {
		// TODO Auto-generated method stub
		mPcServer.monitorScreenOffAll();
	}

	@Override
	public void monitorScreenOnAll() {
		// TODO Auto-generated method stub
		mPcServer.monitorScreenOnAll();
	}
	

	@Override
	public JPanel initPhoneListAll(JPanel jpPhoneList, PhoneItemPresenter pip) {
		// TODO Auto-generated method stub
		
		return mPcServer.initPhoneListAll(jpPhoneList, pip);

	}
	
	@Override
	public void addPhone(String phoneIp, PhoneItem phoneItem) {
		// TODO Auto-generated method stub
		mPcServer.addPhone(phoneIp, phoneItem);
	}
	
	@Override
	public void removePhone(String phoneIp, PhoneItem phoneItem) {
		// TODO Auto-generated method stub
		mPcServer.removePhone(phoneIp, phoneItem);
	}

}
