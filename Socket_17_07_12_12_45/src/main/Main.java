package main;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main extends JFrame{
    JTextField jTextField ;//定义文本框组件
    JPasswordField jPasswordField;//定义密码框组件
    JLabel jLabel1,jLabel2;
    JPanel jp1,jp2,jp3;
    JButton jb1,jb2; //创建按钮
    public void denglu(){
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(13);
        jLabel1 = new JLabel("用户名");
        jLabel2 = new JLabel("密码");
        jb1 = new JButton("确认");
        jb2 = new JButton("取消");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        
        //设置布局
        this.setLayout(new GridLayout(1,3));
        
        jp1.add(jLabel1); 
        jp1.add(jTextField);//第一块面板添加用户名和文本框 
        
        jp2.add(jLabel2);
        jp2.add(jPasswordField);//第二块面板添加密码和密码输入框
        
        jp3.add(jb1);
        jp3.add(jb2); //第三块面板添加确认和取消
        
        //        jp3.setLayout(new FlowLayout());  　　//因为JPanel默认布局方式为FlowLayout，所以可以注销这段代码.
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);  //将三块面板添加到登陆框上面
        //设置显示
        this.setSize(1500, 1000);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("凯旋群控中心");
         
    }
//    public static void main(String[] args){
//        new Main().denglu();
//    }

}
