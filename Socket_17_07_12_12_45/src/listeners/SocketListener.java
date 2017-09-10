package listeners;

public interface SocketListener {

	public void initServerSocketAll();
	public void socketConnected();
	public void socketError();
	public void socketClose();

}
