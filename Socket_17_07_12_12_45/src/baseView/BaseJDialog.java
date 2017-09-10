package baseView;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JDialog;

public class BaseJDialog extends JDialog{

	public BaseJDialog() {
		// TODO Auto-generated constructor stub
		setTitleIcon();
//		setMouseIcon();
	}
	
	private void setTitleIcon () {
		Toolkit tk=Toolkit.getDefaultToolkit();
				Image image=tk.createImage(this.getClass()
						.getClassLoader().getResource("image/bg.jpg")); /*image.gif是你的图标*/ 
				this.setIconImage(image);
	}

	private void setMouseIcon () {
		Toolkit tk=Toolkit.getDefaultToolkit(); 
		Image img=tk.getImage(this.getClass()
				.getClassLoader().getResource("image/bg.jpg")); /*mouse.gif是你的图标*/ 
		Cursor cu=tk.createCustomCursor(img,new Point(10,10),"stick"); 
		this.setCursor(cu);
	}
}
