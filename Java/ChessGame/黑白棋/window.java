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
		super("�ڰ�����Ϸ");
		menubar=new JMenuBar();
		
		
		//��Ϸѡ��
		game=new JMenu("��Ϸ");
		newgame_menuitem=new JMenuItem("����Ϸ");
		initial_menuitem=new JMenuItem("���¿�ʼ");
		exit_menuitem=new JMenuItem("�˳�����");
		game.add(newgame_menuitem);
		game.add(initial_menuitem);
		game.add(exit_menuitem);
		newgame_menuitem.addActionListener(this);
		initial_menuitem.addActionListener(this);
		exit_menuitem.addActionListener(this);
		menubar.add(game);
		
		//ѡ��
		options=new JMenu("ѡ��");
		regret_menuitem=new JMenuItem("����");
		options.add(regret_menuitem);
		regret_menuitem.addActionListener(this);
		menubar.add(options);
		
		//����
		about=new JMenu("����");
		info_menuitem=new JMenuItem("���ڱ���Ϸ");
		about.add(info_menuitem);
		info_menuitem.addActionListener(this);
		menubar.add(about);
		//------------------------������ǲ˵�����ѡ����Ϣ--------------------
		setJMenuBar(menubar);
		
		//chess_board=new draw_board();
		
		
		
		main=getContentPane();
		main.setBackground(Color.GRAY);
		setSize(650, 560);
		scrollpane=new JScrollPane(chess_board.get_info());
		scrollpane.setSize(80, 560);
		scrollpane.setBackground(Color.red);
		JPanel info_panel=new JPanel(new BorderLayout());
		info_panel.add(new JLabel("������Ϣ:"),BorderLayout.NORTH);
		info_panel.add(scrollpane,BorderLayout.CENTER);
		info_panel.add(new JLabel(" ��Ϣ "),BorderLayout.SOUTH);
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
		if(e.getActionCommand()=="����Ϸ")
		{
			chess_board.reset();
		}
		else if(e.getActionCommand()=="���¿�ʼ")
		{
			chess_board.reset();
		}
		else if(e.getActionCommand()=="����")
		{
			chess_board.regret();
		}
		else if(e.getActionCommand()=="���ڱ���Ϸ")
    	{
    		new info();
    	}
		else if(e.getActionCommand()=="�˳�����")
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