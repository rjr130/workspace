import javax.swing.*;
import java.awt.*;
public class ai
{	
	final static int ROWS=8;                                //棋盘每行有8行
	final static int COLS=8;                                //棋盘有8列
	final static int WHITE=1;                               //白色的棋子对应位为1
	final static int BLACK=-1;                              //黑色棋子对应位上为-1
	final static int EMPTY=0;
	private int chess_number;
	private int [][]chess_board;
	private int steps=0;                                    //记录游戏者已经进行的步数，电脑走棋不算，游戏者走一步加1
	private int history[][][];                              //记录每一步的信息，作为悔棋使用，只记录每一次游戏者走棋之前的状态
	private boolean[][] ok_for_next;                        //下一步可以放置新的棋子的位置
	private int black_chess=2;
	private int white_chess=2;
	private JTextArea info=null;
	
	//ai的构造函数，包含的内容有：初始化棋盘，将中心的四个棋子分别置为白色和黑色。
	public ai()//初始化棋盘信息
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
		

		for(i=0;i<ROWS;i++)                                 //初始化棋盘，每个位置上都是空的EMPTY
		{ 													//每个位置上都不能放置新的棋子
			for(j=0;j<COLS;j++)
			{
				chess_board[i][j]=EMPTY;
				ok_for_next[i][j]=false;               
			}
		}
		chess_board[3][3]=BLACK;                            //初始棋盘上有两白两黑四个棋子。
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
		
	//返回棋盘的状态，以数组的形式
	public int[][] return_chess_board()
	{
		return chess_board;
	}
	
	//返回棋盘的下一时刻的可下子的位置信息
	public boolean[][] return_ok_for_next()
	{
		//ok_for_next_step(1);
		return ok_for_next;
	}
	
	//有新的子落到棋盘上，改变棋盘的状态,player为当前下子的颜色，(i,j)是下子的位置
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
			if (j != 0) {  //player不位于最上方
				if (player != chess_board[i][j - 1] && 1 == Math.abs(chess_board[i][j - 1])) 
				{
					//判断i*j位置所下的棋与i*(j-1)位置是否不同,并且i*(j-1)位置有棋
					int m = j - 1;
					while (chess_board[i][j - 1] == chess_board[i][m] && m > 0) 
					{
						m--;
						////通过变量m判断i*(j-1)上的棋是否与north方向上的棋颜色相同,相同m自减1,循环结束时有j-m个相同颜色棋,
					}
					if (chess_board[i][m] == player) {
						//判断第i,m位置的棋是否与i,j(起始位)棋颜色相同,相同的话将它们之间的棋改为player棋颜色,即吃子
						flag = true;
						for (int n = j ; n > m; n--) 
						{
							chess_board[i][n] = player;
						}
					}
				}
			}

        
			//south
			if (j != 7) {  //player不位于最下方
				if (player != chess_board[i][j + 1] && 1 == Math.abs(chess_board[i][j + 1])) 
				{
					//判断i*j位置所下的棋与i*(j+1)位置是否不同,并且i*(j+1)位置有棋
					int m = j + 1;
					while (chess_board[i][j + 1] == chess_board[i][m] && m < 7) 
					{
						m++;
						//通过变量m判断i*(j+1)上的棋是否与south方向上的棋颜色相同,相同m自增1,循环结束时有m-1个相同颜色棋,
					}
					if (chess_board[i][m] == player) 
					{ 
						//判断第i,m位置的棋是否与i,j(起始位)棋颜色相同,相同的话将它们之间的棋改为player棋颜色,即吃子
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
	
	//棋盘的信息转换
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
	
	//判断本局结束	
	//白方胜，返回1，黑方胜，返回-1，平局，返回0，游戏还没有结束，返回2
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
				return 1;         //白方胜，返回1
			else if(white<black || white==0)
				return -1;        //黑方胜，返回-1
			else
				return 0;         //平局，返回0
		}
		else
			return 2;            //游戏还没有结束，返回2
	}
	
	//更新下一步可以放子的位置信息
	//返回下面可以方子的位置
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
	
	//检查这个位置放放上新的子后需要改变的棋子的格式
	//得到该颜色的子下在这个位置上会赢的子的个数
	
	//检查这个时候的这个位置上是否可以放置sta颜色的子
	public int check(int i, int j, int sta) 
	{     

	    int flag = 0;
	    if (Math.abs(chess_board[i][j]) == 1) //这个位置上已经有子了
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

	//悔棋
	
	//悔棋，将棋盘回复为这次落子前的状态
	public void regret(int turn )
	{
		if(steps==0){}//游戏还没有开始，悔棋将不进行操作。
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
	
	//电脑走棋
	//电脑走棋。
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
	
	//得到黑子的个数
	
	//得到黑棋子的个数
	public int number_of_black()
	{
		return black_chess;
	}
	
	//得到白子的个数
	
	//得到白棋子的个数
	public int number_of_white()
	{
		return white_chess;
	}
	
	//重新初始化
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
		

		for(i=0;i<ROWS;i++)                                 //初始化棋盘，每个位置上都是空的EMPTY
		{ 													//每个位置上都不能放置新的棋子
			for(j=0;j<COLS;j++)
			{
				chess_board[i][j]=EMPTY;
				ok_for_next[i][j]=false;               
			}
		}
		chess_board[3][3]=BLACK;                            //初始棋盘上有两白两黑四个棋子。
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
	
	//得到走棋的信息
	public JTextArea return_info()
	{
		return this.info;
	}
  

}