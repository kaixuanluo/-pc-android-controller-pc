package utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import baseView.BaseJDialog;
import constants.ConstantsStr;
import constants.Dimens;

public class TipDialog {

	public TipDialog() {

	}

	public TipDialog(String content, ActionListener actionListenr) {

	}

	public TipDialog(String title, String content, ActionListener actionListenr) {
		// TODO Auto-generated constructor stub
	}
	
	public static void show(String title, String content,
			int dialogWidth, int dialogHeight, 
			ActionListener actionListenr) {
		show(title, content, ConstantsStr.CANCEL, ConstantsStr.OK, 
				dialogWidth, dialogHeight, actionListenr);
	}
	public static void show(String title, String content,
			ActionListener actionListenr) {
		show(title, content, ConstantsStr.CANCEL, ConstantsStr.OK,
				Dimens.DIALOG_WIDTH, Dimens.DIALOG_HEIGHT, actionListenr);
	}

	public static void show(String title, String content, String cancelStr, String OkStr, 
			ActionListener actionListenr) {
		show(title, content, cancelStr, OkStr,
				Dimens.DIALOG_WIDTH, Dimens.DIALOG_HEIGHT, actionListenr);
	}
	
	public static void show(String content, String cancelStr, String OkStr, 
			ActionListener actionListenr) {
		show(ConstantsStr.TIP, content, cancelStr, OkStr, 
				Dimens.DIALOG_WIDTH, Dimens.DIALOG_HEIGHT, actionListenr);
	}
	
	public static void show(String title, String content,
			 String cancelStr, String OkStr, int dialogWidth, int dialogHeight, 
			ActionListener actionListenr) {
		JDialog jDialog = new BaseJDialog();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 得到屏幕的尺寸
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;

		jDialog.setTitle(title);
		jDialog.setSize(dialogWidth, dialogHeight);
		jDialog.setLocation((screenWidth - jDialog.getWidth()) / 2,
				(screenHeight - jDialog.getHeight()) / 2);
		
//		jPa jLDialog = new JPanel();
//		jDialog.add(jDialog, new BoxLayout(jDialog, BoxLayout.X_AXIS);
		
		JPanel jpContent = new JPanel();
	
		jpContent.setLayout(new BoxLayout(jpContent, BoxLayout.Y_AXIS));
		
		JLabel jlContent = new JLabel(content);
		Font font = new Font("宋体", Font.BOLD, 18);
		jlContent.setFont(font);
		jlContent.setPreferredSize(new Dimension(dialogWidth, dialogHeight-50));
		jpContent.add(jlContent);
		
		JButton jbCancel = new JButton(cancelStr);
		jbCancel.setMargin(new Insets(0, 0, 0, 0));
		jbCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				  jDialog.dispose();
			}
		});
		
		JButton jbOk = new JButton(OkStr);
		jbOk.setMargin(new Insets(0, 0, 0, 0));
		jbOk.addActionListener(actionListenr);
		jbOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jDialog.dispose();
			}
		});
		
		JPanel jpBt = new JPanel();
//		jpBt.setLayout(new BoxLayout(jpBt, BoxLayout.X_AXIS));
		jpBt.setPreferredSize(new Dimension(dialogWidth, 50));
		jpBt.setLayout(new GridLayout(1, 2));
		jpBt.add(jbCancel);
		jpBt.add(jbOk);
		
		jpContent.add(jpBt);
		
		jDialog.add(jpContent);
		
		jDialog.setVisible(true);
		
	}

}
