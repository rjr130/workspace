import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class Info extends JFrame implements ActionListener
{
	JPanel panel1,panel2;
	Container c;
	JTextArea InfoTextArea;
	JButton YesBtn;
	public Info()
	{
		super("���ںڰ���");
		c = getContentPane();
		c.setLayout(new BorderLayout());
		InfoTextArea= new JTextArea(3,20);
		String msg = " �ڰ���汾1.0 ����:����\n �����ַ:jimmy_0517@yahoo.com.cn";
		InfoTextArea.setText(msg);
		InfoTextArea.setLineWrap(true);   
		panel1 = new JPanel();
		panel1.add(InfoTextArea);
		YesBtn=new JButton("ȷ��");
		YesBtn.setBounds(110,250,70,20);
		panel2 = new JPanel();
		panel2.add(YesBtn);
		YesBtn.addActionListener(this);
		c.add(panel1,BorderLayout.NORTH);
		c.add(panel2,BorderLayout.SOUTH);
		setSize(300,150);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		setLocation((screenSize.width-frameSize.width)/2,
		              (screenSize.height-frameSize.height)/2);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==YesBtn)
		{
			this.dispose();
		}
			
	}
}
	