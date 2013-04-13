import java.awt.*;
import javax.swing.*;
//import java.net.URL;
import java.awt.event.*;

public class draw_board extends JPanel
{

	private static final long serialVersionUID = 1L;
	final static int ROWS=8;                                //����ÿ����8��
	final static int COLS=8;                                //������8��
	final static int WHITE=1;                               //��ɫ�����Ӷ�ӦλΪ1
	final static int BLACK=-1;                              //��ɫ���Ӷ�Ӧλ��Ϊ-1
	final static int EMPTY=0;                               //��λ��ӦΪ0
	private int next_turn=BLACK;                            //��һ�������ӵ�һ������WHITE��BLACK���
	ai ai=new ai();
	private Point point;
	
	//���캯��
	public draw_board()
	{
		point=new Point();
		addMouseListener(new MouseAdapter() 
		{
            public void mouseClicked(MouseEvent e) 
            {
                getpoint(e.getPoint());
            }
        });
	
	}	

	protected void paintComponent(Graphics g)
	{
	     super.paintComponents(g);
	     Graphics2D g2d = (Graphics2D) g;
	     g2d.setColor(Color.LIGHT_GRAY);
	     g2d.fill3DRect(50, 50, 400, 400, true);
	     for (int i = 0; i < 8; i++) 
	     {
	          for (int j = 0; j < 8; j++) 
	          {

	    	      g2d.setColor(Color.white);
	              g2d.drawString(ai.change(i) + "", 50 * i + 75, 45); //��������
	              g2d.drawString(j + "", 40, 50 * j + 75);  //���������
	    	      g2d.setColor(Color.RED);
	              g2d.drawRect(50 + 50 * i, 50 + 50 * j, 50, 50); //�����
	              if (ai.return_chess_board()[i][j] == BLACK) 
	              {
	                  g2d.setColor(Color.BLACK);
	                  g2d.fillOval(i * 50 + 53, j * 50 + 53, 44, 44);
	              }  //������
	              if (ai.return_chess_board()[i][j] == WHITE) 
	              {
	                  g2d.setColor(Color.white);
	                  g2d.fillOval(i * 50 + 53, j * 50 + 53, 44, 44);
	              }//������
	              if (ai.return_ok_for_next()[i][j] == true) 
	              {
	                	 
	                  g2d.setColor(Color.blue);
	                   g2d.drawString("X", i * 50 + 75, j * 50 + 75);   //�����������
	              }
	          }
	          g2d.setColor(Color.WHITE); 
	          g2d.fillRect(460, 50, 50, 160);
	          g2d.drawLine(460, 130, 510, 180);
	          g2d.setColor(Color.BLACK);  //�ײ�����
	          g2d.fillOval(470, 70, 30, 30);
	          g2d.setColor(Color.BLACK);
	          g2d.fillRect(468, 148, 34, 34);
	          g2d.setColor(Color.WHITE);
	          g2d.fillOval(470, 150, 30, 30);
	                
	          g2d.setColor(Color.GRAY);
	          g2d.setColor(Color.BLACK);  //����
	          g2d.drawString("" + ai.number_of_black(), 480, 113);
	          g2d.drawString("" + ai.number_of_white(), 480, 193);
	     }
	}
	 
	//��õ������
	public void getpoint(Point p)
	{
		point=p;

    	if(next_turn==BLACK) 
    	{
    		if(p.x>50 && p.x<450 && p.y>50 && p.y<450)
    		{
    			if(ai.is_over()==2)//û�н���������
    			{
    			}
    			else if(ai.is_over()==1)
    			{
    				JOptionPane.showMessageDialog(null, "���ź�����ʧ����~\n���� : ����\n"+ai.number_of_black()+"   :  "+ai.number_of_white(), "qe", JOptionPane.INFORMATION_MESSAGE);
    			}
    			else if(ai.is_over()==-1)
    			{	
    				JOptionPane.showMessageDialog(null, "��ϲ���ʤ��\n���� : ����\n  "+ai.number_of_black()+"  :  "+ai.number_of_white(), "qe", JOptionPane.INFORMATION_MESSAGE);
    			}
    			else if(ai.is_over()==0)
    			{
    				JOptionPane.showMessageDialog(null, "ƽ��\n���� : ���� is"+ai.number_of_black()+"  :  "+ai.number_of_white(), "qe", JOptionPane.INFORMATION_MESSAGE);
    			}
    			int x=p.x/50-1;
    			int y=p.y/50-1;
    			boolean update_success=false;
    			update_success=ai.update_chess_board(next_turn, x, y);
    			next_turn=WHITE;
    			boolean ok_for_next=false;
    			ai.ok_for_next_step(next_turn);
    		  a:for(int i=0;i<8;i++)
    			{
    				for(int j=0;j<8;j++)
    				{
    					if(ai.return_ok_for_next()[i][j]==true)
    					{
    						ok_for_next=true;
    						break a;
    					}
    				}
    			}
    	
    			if(update_success && ok_for_next)
    			{
    				ai.auto_chess(next_turn);
    				next_turn=-next_turn;
    				repaint();
    			}
    			else if(update_success && !ok_for_next)
    			{    				
    				ai.ok_for_next_step(BLACK);
					repaint();
    				JOptionPane.showMessageDialog(null, "����������ߣ�����!", "message", JOptionPane.INFORMATION_MESSAGE);
					repaint();

    			}
    		//	if(ai.auto_chess(WHITE) && update_success==true)
    		//	{
    		//		repaint();
    		//		next_turn=BLACK;
    		//	}
    		}
		
    	}
    	
    	else
    	{
    		ai.auto_chess(WHITE);
    		next_turn=BLACK;
    		boolean ok_for_next=false;
    		ai.ok_for_next_step(BLACK);
  		  a:for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
					if(ai.return_ok_for_next()[i][j]==true)
					{
						ok_for_next=true;
						break a;
					}
				}
			}
    		if(ok_for_next)
    		{
    			repaint();
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "����������ߣ�����!", "message", JOptionPane.INFORMATION_MESSAGE);
    			getpoint(point);
    		}
    	}
		
		//else if(next_turn==WHITE)
		//{
		//	if(ai.auto_chess(WHITE))
		//	{
		//		repaint();
		//		next_turn=BLACK;
		//	}
		//}
		
		
		

	}
	
	public void reset()
	{
		ai.reset();
		next_turn=BLACK;
		repaint();
	}
	
	//����
	public void regret()
	{
		ai.regret(BLACK);
		repaint();
		next_turn=BLACK;
	}
	
	//���������Ϣ������Ϣ��ʾ��window�е�ĳ���ط���
    public JTextArea get_info()
    {
    	return ai.return_info();
    }
	
}