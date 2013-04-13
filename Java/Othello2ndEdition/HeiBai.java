import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class HeiBai extends JFrame implements ActionListener,ItemListener 
{

	Container content;
	JMenuBar MenuBar;
	JMenu GameMenu,SelectMenu,InfoMenu,GradeMenu;
	JMenuItem NewGameMenuItem,ReturnMenuItem,InfoMenuItem,
	LowMenuItem,NormalMenuItem,HighMenuItem,QuitMenuItem,VsMenuItem;
	JCheckBoxMenuItem HelpCheckBoxMenuItem;
	DrawPane drawPane;
    public HeiBai() {
    	super("�ڰ�����Ϸ");
        
    	//��Ϸ�˵�
		MenuBar=new JMenuBar();
		GameMenu=new JMenu("��Ϸ");
		GradeMenu=new JMenu("�Ѷ�");
		NewGameMenuItem=new JMenuItem("�¾�");
		VsMenuItem=new JMenuItem("���˶�ս");
		LowMenuItem=new JMenuItem("�ͼ�");
		NormalMenuItem=new JMenuItem("�м�");
		HighMenuItem=new JMenuItem("�߼�");
		QuitMenuItem=new JMenuItem("�˳�");
		GameMenu.add(NewGameMenuItem);
		GameMenu.addSeparator();
		GameMenu.add(VsMenuItem);
		GameMenu.addSeparator();
		GameMenu.add(GradeMenu);
		GameMenu.addSeparator();
		GameMenu.add(QuitMenuItem);
		GradeMenu.add(LowMenuItem);
		GradeMenu.addSeparator();
		GradeMenu.add(NormalMenuItem);
		GradeMenu.addSeparator();
		GradeMenu.add(HighMenuItem);
		NewGameMenuItem.addActionListener(this);
		VsMenuItem.addActionListener(this);
		LowMenuItem.addActionListener(this);
		NormalMenuItem.addActionListener(this);
		HighMenuItem.addActionListener(this);
		QuitMenuItem.addActionListener(this);
		MenuBar.add(GameMenu);
		
		//ѡ��˵�
		SelectMenu=new JMenu("ѡ��");
		ReturnMenuItem=new JMenuItem("����");
		SelectMenu.add(ReturnMenuItem);
		SelectMenu.addSeparator();
		HelpCheckBoxMenuItem=new JCheckBoxMenuItem("�Ƿ���ʾ",false);
		SelectMenu.add(HelpCheckBoxMenuItem);
		HelpCheckBoxMenuItem.addItemListener(this); 
		ReturnMenuItem.addActionListener(this);
		MenuBar.add(SelectMenu);
		
		//���ڲ˵�
		InfoMenu=new JMenu("����");
		InfoMenuItem=new JMenuItem("���ںڰ���");
		InfoMenu.add(InfoMenuItem);
		InfoMenuItem.addActionListener(this);
		MenuBar.add(InfoMenu);
		
        //----------------------------------
		setJMenuBar(MenuBar);
		
        drawPane = new DrawPane(getBackground());
        content = getContentPane();
        content.setLayout(new BorderLayout());
        content.add(drawPane, BorderLayout.CENTER);
        JScrollPane jsp = new JScrollPane(drawPane.getInfo());
        JPanel infoPanel = new JPanel(new BorderLayout());
        //infoPanel.setBackground(Color.BLUE);
        infoPanel.add(jsp, BorderLayout.CENTER);

        infoPanel.add(new JLabel(" ������Ϣ "), BorderLayout.NORTH);
        infoPanel.add(new JLabel("  "), BorderLayout.SOUTH);
        content.add(jsp,BorderLayout.EAST);
        setSize(600, 580);
        //f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //f.setUndecorated(true);
        //f.setAlwaysOnTop(true);
        //f.setLocationByPlatform(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		setLocation((screenSize.width-frameSize.width)/2,
		              (screenSize.height-frameSize.height)/2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setBackground(Color.PINK);
        setResizable(false);
       setVisible(true);
    }
    
    
     public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.DESELECTED) {
            	 drawPane.help(0);
            }
            else {
                drawPane.help(1);
            }

        }

    
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getActionCommand()=="���ںڰ���")
    	{
    		Info info = new Info();
    	    info.show();
    	}
    	
    	else if(e.getActionCommand()=="�¾�")
    	{
    		
    		drawPane.initChess();
    	}
    	else if(e.getActionCommand()=="����")
    	{
    		drawPane.regret();
    		
    	}
    	else if(e.getActionCommand()=="�ͼ�")
    	{
    		 drawPane.level = 1;
    		 drawPane.vs(0);
    		 drawPane.initChess();
    	}
    	else if(e.getActionCommand()=="�м�")
    	{
    		 drawPane.level = 2;
    		 drawPane.vs(0);
    		 drawPane.initChess();
    	}
    	else if(e.getActionCommand()=="�߼�")
    	{
    		 drawPane.level = 3;
    		 drawPane.vs(0);
    		 drawPane.initChess();
    	}
    	else if(e.getActionCommand()=="�˳�")
    	{
    		this.dispose();
    		System.exit(0);
    	}
    	else if(e.getActionCommand()=="���˶�ս")
    	{
    		drawPane.vs(1);
    	}
    }

    public static void main(String[] args) {
        new HeiBai();
    }
}


