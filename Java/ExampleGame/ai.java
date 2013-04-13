import javax.swing.*;
import java.awt.*;
public class ai
{	
	final static int ROWS=8;                                //����ÿ����8��
	final static int COLS=8;                                //������8��
	final static int WHITE=1;                               //��ɫ�����Ӷ�ӦλΪ1
	final static int BLACK=-1;                              //��ɫ���Ӷ�Ӧλ��Ϊ-1
	final static int EMPTY=0;
	private int chess_number;
	private int [][]chess_board;
	private int steps=0;                                    //��¼��Ϸ���Ѿ����еĲ������������岻�㣬��Ϸ����һ����1
	private int history[][][];                              //��¼ÿһ������Ϣ����Ϊ����ʹ�ã�ֻ��¼ÿһ����Ϸ������֮ǰ��״̬
	private boolean[][] ok_for_next;                        //��һ�����Է����µ����ӵ�λ��
	private int black_chess=2;
	private int white_chess=2;
	private JTextArea info=null;
	
	//ai�Ĺ��캯���������������У���ʼ�����̣������ĵ��ĸ����ӷֱ���Ϊ��ɫ�ͺ�ɫ��
	public ai()//��ʼ��������Ϣ
	{
		int i=0,j=0,k=0;
		steps=0;
		info =new JTextArea();
		info.setBackground(Color.WHITE);
		info.setForeground(Color.BLACK);
		String msg="chess_board initialed\n";
		info.setText(msg);
		chess_board=new int[8][8];
		ok_for_next=new boolean[8][8];
		history=new int[64][8][8];
		for(i=0;i<32;i++)
		{
			for(j=0;j<8;j++)
			{
				for(k=0;k<8;k++)
				{
					history[i][j][k]=EMPTY;
				}
			}
		}
		chess_number=4;	
		

		for(i=0;i<ROWS;i++)                                 //��ʼ�����̣�ÿ��λ���϶��ǿյ�EMPTY
		{ 													//ÿ��λ���϶����ܷ����µ�����
			for(j=0;j<COLS;j++)
			{
				chess_board[i][j]=EMPTY;
				ok_for_next[i][j]=false;               
			}
		}
		chess_board[3][3]=BLACK;                            //��ʼ�����������������ĸ����ӡ�
		chess_board[4][4]=BLACK;
		chess_board[3][4]=WHITE;
		chess_board[4][3]=WHITE;
		for(i=0;i<ROWS;i++)
		{
			for(j=0;j<COLS;j++)
			{
				history[0][i][j]=chess_board[i][j];
			}
		}
		ok_for_next_step(BLACK);
	}
		
	//�������̵�״̬�����������ʽ
	public int[][] return_chess_board()
	{
		return chess_board;
	}
	
	//�������̵���һʱ�̵Ŀ����ӵ�λ����Ϣ
	public boolean[][] return_ok_for_next()
	{
		//ok_for_next_step(1);
		return ok_for_next;
	}
	
	//���µ����䵽�����ϣ��ı����̵�״̬,playerΪ��ǰ���ӵ���ɫ��(i,j)�����ӵ�λ��
	public boolean update_chess_board(int player,int i,int j)
	{
		boolean flag=false;

		if(Math.abs(chess_board[i][j])==1)
		{
			flag=false;
		}
		else
		{
			//north
			if (j != 0) {  //player��λ�����Ϸ�
				if (player != chess_board[i][j - 1] && 1 == Math.abs(chess_board[i][j - 1])) 
				{
					//�ж�i*jλ�����µ�����i*(j-1)λ���Ƿ�ͬ,����i*(j-1)λ������
					int m = j - 1;
					while (chess_board[i][j - 1] == chess_board[i][m] && m > 0) 
					{
						m--;
						////ͨ������m�ж�i*(j-1)�ϵ����Ƿ���north�����ϵ�����ɫ��ͬ,��ͬm�Լ�1,ѭ������ʱ��j-m����ͬ��ɫ��,
					}
					if (chess_board[i][m] == player) {
						//�жϵ�i,mλ�õ����Ƿ���i,j(��ʼλ)����ɫ��ͬ,��ͬ�Ļ�������֮������Ϊplayer����ɫ,������
						flag = true;
						for (int n = j ; n > m; n--) 
						{
							chess_board[i][n] = player;
						}
					}
				}
			}

        
			//south
			if (j != 7) {  //player��λ�����·�
				if (player != chess_board[i][j + 1] && 1 == Math.abs(chess_board[i][j + 1])) 
				{
					//�ж�i*jλ�����µ�����i*(j+1)λ���Ƿ�ͬ,����i*(j+1)λ������
					int m = j + 1;
					while (chess_board[i][j + 1] == chess_board[i][m] && m < 7) 
					{
						m++;
						//ͨ������m�ж�i*(j+1)�ϵ����Ƿ���south�����ϵ�����ɫ��ͬ,��ͬm����1,ѭ������ʱ��m-1����ͬ��ɫ��,
					}
					if (chess_board[i][m] == player) 
					{ 
						//�жϵ�i,mλ�õ����Ƿ���i,j(��ʼλ)����ɫ��ͬ,��ͬ�Ļ�������֮������Ϊplayer����ɫ,������
						flag = true;
						for (int n = j ; n < m; n++) 
						{
							chess_board[i][n] = player;
						}
					}

				}
			}

        
			//west
			if (i != 0) 
			{
				if (player != chess_board[i - 1][j] && 1 == Math.abs(chess_board[i - 1][j])) 
				{
					int m = i - 1;
					while (chess_board[i - 1][j] == chess_board[m][j] && m > 0) 
					{
						m--;   
					}
					if (chess_board[m][j] == player) 
					{
						flag = true;
						for (int n = i ; n > m; n--) 
						{
							chess_board[n][j] = player;
						}
					}
				}
			}
        
        
			//east
			if (i != 7) {
				if (player != chess_board[i + 1][j] && 1 == Math.abs(chess_board[i + 1][j])) {
					int m = i + 1;
					while (chess_board[i + 1][j] == chess_board[m][j] && m < 7) 
					{
						m++;
					}
					if (chess_board[m][j] == player) 
					{
						flag = true;
						for (int n = i ; n < m; n++) 
						{
							chess_board[n][j] = player;
						}
					}
				}
			}
        
        
			//northwest
			if (j != 0 && i != 0) {
				if (player != chess_board[i - 1][j - 1]
                    && 1 == Math.abs(chess_board[i - 1][j - 1])) {
					int m = i - 1;
					int n = j - 1;
					while (chess_board[i - 1][j - 1] == chess_board[m][n] && m > 0 && n > 0) 
					{
						m--;
						n--;
					}
					if (chess_board[m][n] == player) 
					{
						flag = true;
						for (int x = i , y = j ; x > m; x--, y--)
						{
							chess_board[x][y] = player;
						}
					}
				}
			}
        
        
			//southeast
			if (j != 7 && i != 7) {
				if (player != chess_board[i + 1][j + 1]&& 1 == Math.abs(chess_board[i + 1][j + 1])) 
				{
					int m = i + 1;
					int n = j + 1;
					while (chess_board[i + 1][j + 1] == chess_board[m][n] && m < 7 && n < 7) 
					{
						m++;
						n++;
					}
					if (chess_board[m][n] == player) 
					{
						flag = true;
						for (int x = i , y = j ; x < m; x++, y++) 
						{
							chess_board[x][y] = player;
						}
					}
				}
			}
        
        
			//northeast
			if (j != 0 && i != 7) {
				if (player != chess_board[i + 1][j - 1]
				                                 && 1 == Math.abs(chess_board[i + 1][j - 1])) {
					int m = i + 1;
					int n = j - 1;
					while (chess_board[i + 1][j - 1] == chess_board[m][n] && m < 7 && n > 0) {
						m++;
						n--;              
					}
					if (chess_board[m][n] == player) 
					{
						flag = true;
						for (int x = i , y = j ; x < m; x++, y--) 
						{
							chess_board[x][y] = player;
						}
					}
				}
			}
        
        
			//southwest
			if (j != 7 && i != 0) 
			{
				if (player != chess_board[i - 1][j + 1]
                    && 1 == Math.abs(chess_board[i - 1][j + 1])) 
				{
					int m = i - 1;
					int n = j + 1;
					while (chess_board[i - 1][j + 1] == chess_board[m][n] && m > 0 && n < 7) 
					{
						m--;
						n++;
					}
					if (chess_board[m][n] == player) 
					{
						flag = true;
						for (int x = i , y = j ; x > m; x--, y++) 
						{
                    	chess_board[x][y] = player;
						}
					}
				}
			}
		}
        if(flag)
        {
        	int black=0,white=0;
        	if(player==WHITE)
        	{
        		info.append(chess_number-3+":white("+change(i)+","+j+")\n");
        	}
        	else if(player==BLACK)
        	{
        		info.append(chess_number-3+":black("+change(i)+","+j+")\n");
        	}
        	if(player==WHITE)
        	{
        		steps++;
        	}
        	for(int m=0;m<8;m++)
        	{
        		for(int n=0;n<8;n++)
        		{
        			if(player==WHITE)
        			{
        				history[steps][m][n]=chess_board[m][n];
        			}
        			if(chess_board[m][n]==WHITE)
        			{
        				white++;
        			}
        			else if(chess_board[m][n]==BLACK)
        			{
        				black++;
        			}
        		}
        	}
        	this.black_chess=black;
        	this.white_chess=white;
        	chess_number=white+black;
        	ok_for_next_step(-player);
        }
		return flag;		
	}
	
	//���̵���Ϣת��
	public char change(int a)
	{
		switch(a)
		{
			case 0: return 'A';
			case 1: return 'B';
			case 2: return 'C';
			case 3: return 'D';
			case 4: return 'E';
			case 5: return 'F';
			case 6: return 'G';
			case 7: return 'H';
			default:return'X';
		}
	}
	
	//�жϱ��ֽ���	
	//�׷�ʤ������1���ڷ�ʤ������-1��ƽ�֣�����0����Ϸ��û�н���������2
	public int is_over()
	{
		int white=0,black=0;
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(chess_board[i][j]==WHITE)
				{
					white++;
				}
				else if(chess_board[i][j]==BLACK)
				{
					black++;
				}
			}
		}
		
		if(black+white==64|| black==0 || white==0)
		{
			if(white>black || black==0)
				return 1;         //�׷�ʤ������1
			else if(white<black || white==0)
				return -1;        //�ڷ�ʤ������-1
			else
				return 0;         //ƽ�֣�����0
		}
		else
			return 2;            //��Ϸ��û�н���������2
	}
	
	//������һ�����Է��ӵ�λ����Ϣ
	//����������Է��ӵ�λ��
	public void ok_for_next_step(int color)
	{
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(color==WHITE)
				{
					if(check(i,j,WHITE)>0)
					{
						ok_for_next[i][j]=true;
					}
					else
					{
						ok_for_next[i][j]=false;
					}
				}
				else if(color==BLACK)
				{
					if(check(i,j,BLACK)>0)
					{
						ok_for_next[i][j]=true;
					}
					else
					{
						ok_for_next[i][j]=false;
					}
				}
			}
		}
	}
	
	//������λ�÷ŷ����µ��Ӻ���Ҫ�ı�����ӵĸ�ʽ
	//�õ�����ɫ�����������λ���ϻ�Ӯ���ӵĸ���
	
	//������ʱ������λ�����Ƿ���Է���sta��ɫ����
	public int check(int i, int j, int sta) 
	{     

	    int flag = 0;
	    if (Math.abs(chess_board[i][j]) == 1) //���λ�����Ѿ�������
	    {
	        return 0;
	    }

	    //north
	    if (j != 0) 
	    {
	        if (sta != chess_board[i][j - 1] && 1 == Math.abs(chess_board[i][j - 1])) 
	        {
	           int m = j - 1;
	           while (chess_board[i][j - 1] == chess_board[i][m] && m > 0) 
	           {
	                m--;    
	           }
	           if (chess_board[i][m] == sta) 
	           {
	               flag = flag + (j - 1 - m);
	                    
	           }
	        }
	    }

	    //south
	    if (j != 7) 
	    {
	        if (sta != chess_board[i][j + 1] && 1 == Math.abs(chess_board[i][j + 1])) 
	        {
	            int m = j + 1;
	            while (chess_board[i][j + 1] == chess_board[i][m] && m < 7) 
	            {
	                m++;  
	            }
	            if (chess_board[i][m] == sta) 
	            {
	                 flag = flag + (m - j - 1);
	            }
	        }
	    }

	    //west
	    if (i != 0) 
	    {
	        if (sta != chess_board[i - 1][j] && 1 == Math.abs(chess_board[i - 1][j])) 
	        {
	            int m = i - 1;
	            while (chess_board[i - 1][j] == chess_board[m][j] && m > 0) 
	            {
	                m--;
	            }
	            if (chess_board[m][j] == sta) 
	            {
	                flag = flag + (i - 1 - m);
	            }
	        }
	    }

	    //east
	    if (i != 7) 
	    {
	        if (sta != chess_board[i + 1][j] && 1 == Math.abs(chess_board[i + 1][j])) {
	            int m = i + 1;
	            while (chess_board[i + 1][j] == chess_board[m][j] && m < 7) 
	            {
	               m++;  
	            }
	            if (chess_board[m][j] == sta) 
	            {
	                flag = flag + (m - i - 1);
	            }
	        }
	    }

	    //northwest
	    if (j != 0 && i != 0) 
	    {
	        if (sta != chess_board[i - 1][j - 1]&& 1 == Math.abs(chess_board[i - 1][j - 1]))
	        {
	            int m = i - 1;
	            int n = j - 1;
	            while (chess_board[i - 1][j - 1] == chess_board[m][n] && m > 0 && n > 0) 
	            {
	                m--;
	                n--;
	            }
	            if (chess_board[m][n] == sta) 
	            {
	                flag = flag + (i - 1 - m);
	            }
	        }
	    }
	    //southeast
	    if (j != 7 && i != 7) 
	    {
	        if (sta != chess_board[i + 1][j + 1]&& 1 == Math.abs(chess_board[i + 1][j + 1]))
	        {
	            int m = i + 1;
	            int n = j + 1;
	            while (chess_board[i + 1][j + 1] == chess_board[m][n] && m < 7 && n < 7) 
	            {
	                m++;
	                n++;
	            }
	            if (chess_board[m][n] == sta) 
	            {
	                flag = flag + (m - i - 1);
	            }
	        }
	    }

	    //northeast
	    if (j != 0 && i != 7) 
	    {
	        if (sta != chess_board[i + 1][j - 1]&& 1 == Math.abs(chess_board[i + 1][j - 1]))
	        {
	            int m = i + 1;
	            int n = j - 1;
	            while (chess_board[i + 1][j - 1] == chess_board[m][n] && m < 7 && n > 0)
	            {
	                m++;
	                n--; 
	             }
	            if (chess_board[m][n] == sta) 
	            {
	                flag = flag + (j - 1 - n);
	            }
	        }
	    }

	    //southwest
	    if (j != 7 && i != 0) 
	    {
	        if (sta != chess_board[i - 1][j + 1]&& 1 == Math.abs(chess_board[i - 1][j + 1])) 
	        {
	            int m = i - 1;
	            int n = j + 1;
	            while (chess_board[i - 1][j + 1] == chess_board[m][n] && m > 0 && n < 7) 
	            {
	                m--;
	                n++;
	            }
	            if (chess_board[m][n] == sta) 
	            {
	                flag = flag + (i - 1 - m);

	            }
	        }
	    }
	    return flag;

	}

	//����
	
	//���壬�����̻ظ�Ϊ�������ǰ��״̬
	public void regret(int turn )
	{
		if(steps==0){}//��Ϸ��û�п�ʼ�����彫�����в�����
		else
		{
			steps--;
			for(int i=0;i<8;i++)
			{
				for(int j=0;j<8;j++)
				{
				//	JOptionPane.showMessageDialog(null, history[steps][7][7],"ah",JOptionPane.INFORMATION_MESSAGE);
					chess_board[i][j]=history[steps][i][j];
				}
			}
			ok_for_next_step(turn);
		}
	}
	
	//��������
	//�������塣
	public boolean auto_chess(int turn)
	{
		int x=0,y=0;
		ok_for_next_step(turn);
		for(int i=0;i<8;i++)
		{
			for(int j=0;j<8;j++)
			{
				if(check(i,j,turn)>check(x,y,turn))
				{
					x=i;y=j;
				}
			}
		}
		//JOptionPane.showMessageDialog(null,x+"khkj"+y,"asdf",JOptionPane.INFORMATION_MESSAGE);
		return update_chess_board(WHITE,x,y);
	}
	
	//�õ����ӵĸ���
	
	//�õ������ӵĸ���
	public int number_of_black()
	{
		return black_chess;
	}
	
	//�õ����ӵĸ���
	
	//�õ������ӵĸ���
	public int number_of_white()
	{
		return white_chess;
	}
	
	//���³�ʼ��
	public void reset()
	{
		int i=0,j=0,k=0;
		info.removeAll();
		steps=0;
		info.setBackground(Color.WHITE);
		info.setForeground(Color.BLACK);
		String msg="chess_board initialed\n";
		info.setText(msg);
		chess_board=new int[8][8];
		ok_for_next=new boolean[8][8];
		history=new int[64][8][8];
		for(i=0;i<32;i++)
		{
			for(j=0;j<8;j++)
			{
				for(k=0;k<8;k++)
				{
					history[i][j][k]=EMPTY;
				}
			}
		}
		chess_number=4;	
		

		for(i=0;i<ROWS;i++)                                 //��ʼ�����̣�ÿ��λ���϶��ǿյ�EMPTY
		{ 													//ÿ��λ���϶����ܷ����µ�����
			for(j=0;j<COLS;j++)
			{
				chess_board[i][j]=EMPTY;
				ok_for_next[i][j]=false;               
			}
		}
		chess_board[3][3]=BLACK;                            //��ʼ�����������������ĸ����ӡ�
		chess_board[4][4]=BLACK;
		chess_board[3][4]=WHITE;
		chess_board[4][3]=WHITE;
		for(i=0;i<ROWS;i++)
		{
			for(j=0;j<COLS;j++)
			{
				history[0][i][j]=chess_board[i][j];
			}
		}
		ok_for_next_step(BLACK);
		
	}
	
	//�õ��������Ϣ
	public JTextArea return_info()
	{
		return this.info;
	}
  

}