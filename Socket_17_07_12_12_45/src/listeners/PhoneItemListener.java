package listeners;

public interface PhoneItemListener {
	
	public void home();
	public void back();
	public void menu();
	public void powerOff();
	public void reboot();
	public void updateScreenContent();
	public void socketError();
	public void socketClose();
	public void mediaPlay();
	public void mediaStop();
	public void screenStart();
	public void screenStop();

}
