package listeners;

import javax.swing.JPanel;

import persenter.PhoneItemPresenter;
import socketKx.PhoneItem;

public interface PhoneAllListeners {
	public void powerOffAll();
	public void reBootAll();
	public void backAll();
	public void homeAll();
	public void installAll();
	public void startMonitorAll();//¿ªÊ¼¼à¿Ø.
	public void stopMonitorAll();//Í£Ö¹¼à¿Ø.
	public void monitorScreenOffAll();//Í£Ö¹¼à¿Ø»­Ãæ,Ö»ÄÜ²Ù¿Ø.
	public void monitorScreenOnAll();//¿ªÊ¼¼à¿Ø»­Ãæ,Ò²ÄÜ²Ù¿Ø.
	public void initServerSocketAll();
	public JPanel initPhoneListAll(JPanel jpPhoneList, PhoneItemPresenter pip);
	public void removePhone(String phoneIp, PhoneItem phoneItem);
	public void addPhone(String phoneIp, PhoneItem phoneItem);
}
