import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class window extends JFrame implements ActionListener
{
	
	private static final long serialVersionUID = 1L;
	Container main;
	JMenuBar menubar;
	JMenu game,options,about;
	JMenuItem newgame_menuitem,initial_menuitem,exit_menuitem,regret_menuitem,info_menuitem;
	draw_board chess_board=new draw_board();
	JScrollPane scrollpane;
	public window()
	{
		super("黑白棋游戏");
		menubar=new JMenuBar();
		
		
		//游戏选项
		game=new JMenu("游戏");
		newgame_menuitem=new JMenuItem("新游戏");
		initial_menuitem=new JMenuItem("重新开始");
		exit_menuitem=new JMenuItem("退出程序");
		game.add(newgame_menuitem);
		game.add(initial_menuitem);
		game.add(exit_menuitem);
		newgame_menuitem.addActionListener(this);
		initial_menuitem.addActionListener(this);
		exit_menuitem.addActionListener(this);
		menubar.add(game);
		
		//选项
		options=new JMenu("选项");
		regret_menuitem=new JMenuItem("悔棋");
		options.add(regret_menuitem);
		regret_menuitem.addActionListener(this);
		menubar.add(options);
		
		//关于
		about=new JMenu("关于");
		info_menuitem=new JMenuItem("关于本游戏");
		about.add(info_menuitem);
		info_menuitem.addActionListener(this);
		menubar.add(about);
		//------------------------上面的是菜单栏的选项信息--------------------
		setJMenuBar(menubar);
		
		//chess_board=new draw_board();
		
		
		
		main=getContentPane();
		main.setBackground(Color.GRAY);
		setSize(650, 560);
		scrollpane=new JScrollPane(chess_board.get_info());
		scrollpane.setSize(80, 560);
		scrollpane.setBackground(Color.red);
		JPanel info_panel=new JPanel(new BorderLayout());
		info_panel.add(new JLabel("走棋信息:"),BorderLayout.NORTH);
		info_panel.add(scrollpane,BorderLayout.CENTER);
		info_panel.add(new JLabel(" 信息 "),BorderLayout.SOUTH);
		//info_panel.setSize(85, 580);
		main.setLayout(new BorderLayout());
		main.add(chess_board,BorderLayout.CENTER);
		main.add(info_panel,BorderLayout.WEST);
		

		
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = this.getSize();
		setLocation((screenSize.width-frameSize.width)/2,(screenSize.height-frameSize.height)/2);
		
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand()=="新游戏")
		{
			chess_board.reset();
		}
		else if(e.getActionCommand()=="重新开始")
		{
			chess_board.reset();
		}
		else if(e.getActionCommand()=="悔棋")
		{
			chess_board.regret();
		}
		else if(e.getActionCommand()=="关于本游戏")
    	{
    		new info();
    	}
		else if(e.getActionCommand()=="退出程序")
		{
			this.dispose();
			System.exit(0);
		}
	}
	
	public static void main(String[] args)
	{
		new window();
	}
	
}