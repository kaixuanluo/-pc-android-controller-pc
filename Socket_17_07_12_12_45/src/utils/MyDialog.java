package utils;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/***
 * 定义的Dialog对话框
 * @author WXW
 *
 */
public  class MyDialog extends JDialog implements ActionListener  {
    private static final long serialVersionUID = 1L;
    
    public static void main(String[] args) {
        Map<String,String> caipinMap = new HashMap<String,String>();
        String yxrsKey = "diyidaocai";
        String yxrsVal = "1";        
        String djjdKey = "dierdaocai";
        String djjdVal = "65";        
        String fqjdKey = "disandaocai";
        String fqjdVal = "123";        
        String abcdKey = "disidaocai";
        String abcdVal = "7";        
        caipinMap.put(yxrsKey,yxrsVal);
        caipinMap.put(djjdKey,djjdVal);
        caipinMap.put(fqjdKey,fqjdVal);
        caipinMap.put(abcdKey,abcdVal);        
        
        JFrame alertFrame = new JFrame();
        MyDialog d =new MyDialog(alertFrame, true,caipinMap,360,320);
        d.setVisible(true);
    }
    
    JButton okBtn = new JButton("确定");
    JButton cancelBtn = new JButton("关闭");
   
    int x = 50;
    int y = 30;
    int width = 65;
    int height = 20;
   
    /***
     * 自定义 Dialog
     * @param parent
     *             父Frame
     * @param modal
     *             是否模式窗体
     * @param caipinMap
     *             数据Map
     * @param windowWidth
     *             宽度 需根据数据计算高度
     * @param windowHeight
     *             高度  默认320即可
     */
    public MyDialog(JFrame parent, boolean modal,Map<String,String> caipinMap,int windowWidth,int windowHeight) {
       super(parent,modal);
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //得到屏幕的尺寸 
       int screenWidth = screenSize.width;
       int screenHeight = screenSize.height;
       JButton []addBtns = new JButton[caipinMap.keySet().size()];
       JButton []mutBtns = new JButton[caipinMap.keySet().size()];
       int btnIndex = 0;
       setTitle("test");
       setSize(windowWidth,windowHeight);
       setLayout(null);
       setResizable(false);
//       this.setUndecorated(true);
//       setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
       setLocation((screenWidth - this.getWidth()) / 2, (screenHeight - this.getHeight())/2);
       
       for (String strKey : caipinMap.keySet()) {
            JLabel cpNameLBL = new JLabel(strKey+":");
            add(cpNameLBL);
            cpNameLBL.setBounds(x,y,width,height);
            cpNameLBL.setName("lbl"+strKey);
            
            JLabel noticeLbl = new JLabel();
            noticeLbl.setName("lbl"+strKey);
            noticeLbl.setBounds(x+120,y,width,height);
            
            JTextField cpCountJTF = new JTextField(caipinMap.get(strKey));
            add(cpCountJTF);
            cpCountJTF.setName("jtf"+strKey);
            cpCountJTF.setBounds(x+160,y,width,height);
            cpCountJTF.requestFocus();
//            cpCountJTF.setDocument(new NumberLenghtLimitedDmt(3));
            cpCountJTF.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    JTextField jtf = (JTextField) e.getSource();
                    String selectVal = jtf.getSelectedText();
                    int keyChar = e.getKeyChar();
                    String val = jtf.getText();
                    if(null == val || val.trim().length() <= 0 || val.replaceAll("0", "").length() <= 0){ //文本框中没有值时默认为1
                        jtf.setText("1");
                        e.consume();
                        return;
                    }
                    
                    int valLength = val.length();
                    if((null != selectVal && selectVal.length() == valLength) && keyChar == KeyEvent.VK_0){
                        jtf.setText("1");
                        e.consume();
                        return;
                    }
                    
                    if ((keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)) { // 输入的是数字
                        if(valLength > 2){ // 长度大于2
                            if(keyChar != KeyEvent.VK_BACK_SPACE && !(null != selectVal && selectVal.trim().length() > 0) ){ // 按下的不是backspace键
                                e.consume();
                            }
                        }
                        return;
                    }
                    e.consume(); // 不是数字键
//                    JOptionPane.showMessageDialog(null, "非数字");
                }
                @Override
                public void keyReleased(KeyEvent e) {}
                @Override
                public void keyPressed(KeyEvent e) {}
            });
            
            addBtns[btnIndex] = new JButton("+");
            addBtns[btnIndex].setName("btn"+strKey);
            addBtns[btnIndex].setBounds(x+260,y,width-40,height);
            addBtns[btnIndex].setBorder(new EmptyBorder(5,5,5,5));
            addBtns[btnIndex].addActionListener(this);
            
            mutBtns[btnIndex] = new JButton("-");
            mutBtns[btnIndex].setName("btn"+strKey);
            mutBtns[btnIndex].setBounds(x+80,y,width-40,height);
            mutBtns[btnIndex].setBorder(new EmptyBorder(5,5,5,5));
            mutBtns[btnIndex].addActionListener(this);
            
            add(addBtns[btnIndex]);
            add(mutBtns[btnIndex]);
            y = y + 30;
            btnIndex ++;
        }
       
        add(okBtn);
        add(cancelBtn);
        okBtn.setBounds(windowWidth - 190, windowHeight - 80, 60, 25);
        okBtn.setName("ok");
        cancelBtn.setBounds(windowWidth - 120, windowHeight - 80, 60, 25);
        cancelBtn.setName("cancel");
        okBtn.addActionListener(this);
        cancelBtn.addActionListener(this);
   }

   public void actionPerformed(ActionEvent e) {
       Component[] cmp = getContentPane().getComponents();
       JButton btn = (JButton)e.getSource();
       String jtfKey = ""+btn.getName().replaceAll("btn", "");
       if(null != jtfKey && !jtfKey.equals("ok") && !jtfKey.equals("cancel") ){
           String btnText = btn.getText();       
           for (Component c : cmp) {
               if(c instanceof JTextField){
                   JTextField tf = (JTextField) c;
                   if(c.getName().equals("jtf"+jtfKey)){
                      
                       int val = Integer.parseInt(tf.getText());
                       if(btnText.equals("+")){
                           if(val < 999){
                               val = val + 1;
                           }
                       }else{
                           val = val - 1;
                           if(val <= 0){
                               val = 1;
                           }
                       }
                       tf.setText(String.valueOf(val));
                   }
               }
           }
           return;
       }
     
       if(e.getSource()==okBtn){
           System.out.println("OK");
           for (Component c : cmp) {
               if(c instanceof JTextField){
                   JTextField js = (JTextField) c;
                   System.out.println(js.getName().replaceAll("jtf", "")+":"+js.getText());
               }
           }
           return;   
       }
       if(e.getSource()==cancelBtn){
           System.out.println("cancel");
           dispose();
           return;   
       }
   }
 
}