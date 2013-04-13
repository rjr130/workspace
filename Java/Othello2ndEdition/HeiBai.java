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
    	super("黑白棋游戏");
        
    	//游戏菜单
		MenuBar=new JMenuBar();
		GameMenu=new JMenu("游戏");
		GradeMenu=new JMenu("难度");
		NewGameMenuItem=new JMenuItem("新局");
		VsMenuItem=new JMenuItem("两人对战");
		LowMenuItem=new JMenuItem("低级");
		NormalMenuItem=new JMenuItem("中级");
		HighMenuItem=new JMenuItem("高级");
		QuitMenuItem=new JMenuItem("退出");
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
		
		//选项菜单
		SelectMenu=new JMenu("选项");
		ReturnMenuItem=new JMenuItem("悔棋");
		SelectMenu.add(ReturnMenuItem);
		SelectMenu.addSeparator();
		HelpCheckBoxMenuItem=new JCheckBoxMenuItem("是否提示",false);
		SelectMenu.add(HelpCheckBoxMenuItem);
		HelpCheckBoxMenuItem.addItemListener(this); 
		ReturnMenuItem.addActionListener(this);
		MenuBar.add(SelectMenu);
		
		//关于菜单
		InfoMenu=new JMenu("关于");
		InfoMenuItem=new JMenuItem("关于黑白棋");
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

        infoPanel.add(new JLabel(" 走棋信息 "), BorderLayout.NORTH);
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
    	if(e.getActionCommand()=="关于黑白棋")
    	{
    		Info info = new Info();
    	    info.show();
    	}
    	
    	else if(e.getActionCommand()=="新局")
    	{
    		
    		drawPane.initChess();
    	}
    	else if(e.getActionCommand()=="悔棋")
    	{
    		drawPane.regret();
    		
    	}
    	else if(e.getActionCommand()=="低级")
    	{
    		 drawPane.level = 1;
    		 drawPane.vs(0);
    		 drawPane.initChess();
    	}
    	else if(e.getActionCommand()=="中级")
    	{
    		 drawPane.level = 2;
    		 drawPane.vs(0);
    		 drawPane.initChess();
    	}
    	else if(e.getActionCommand()=="高级")
    	{
    		 drawPane.level = 3;
    		 drawPane.vs(0);
    		 drawPane.initChess();
    	}
    	else if(e.getActionCommand()=="退出")
    	{
    		this.dispose();
    		System.exit(0);
    	}
    	else if(e.getActionCommand()=="两人对战")
    	{
    		drawPane.vs(1);
    	}
    }

    public static void main(String[] args) {
        new HeiBai();
    }
}


