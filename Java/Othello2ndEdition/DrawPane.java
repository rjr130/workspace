import java.awt.*;

import java.awt.event.*;

import javax.swing.*;
class DrawPane extends JPanel {
    private Insets insets;

    final static int BLACK = -1;

    final static int WHITE = 1;

    private Point point = null;

    private int colorStatus;

    private int[][] status = null;
    
    private int[][]statusOld = null;
    
    private int[][] statusLast = null;

    private int count;

    private int chessNum;

    private JTextArea infoText = null;

    private int[][] statusW = null;

    public int level=1 ;

    private int whiteCount=0;

    private int blackCount=0;
    
    private Color bgColor=null;
    
    private int oldCount=0;
    
    private int help1=0;
    
    private int vs =0;
    
    String help;
    
    
    
   
    public DrawPane(Color c) {
    	statusLast = new int[8][8];
    	help = new String();
        initComponent();
        initChess();
        bgColor=c;
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if( vs==0){
            	response(e.getPoint());
                }
                else{
                	response2(e.getPoint());
                }
            }
        });
        setFocusable(true);
        addKeyListener(new myKeyListener());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (insets == null) {
            insets = getInsets();
        }
        super.paintComponents(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.ORANGE);
        g2d.fill3DRect(50, 50, 400, 400, true);  //����,����͹
        g2d.setColor(Color.RED);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	
                g2d.drawString(i + "", 50 * i + 75, 45); //��������
                g2d.drawString(j + "", 40, 50 * j + 75);  //���������
                g2d.drawRect(50 + 50 * i, 50 + 50 * j, 50, 50); //�����
                if (status[i][j] == BLACK) {
                    g2d.setColor(Color.BLACK);
                    g2d.fillOval(i * 50 + 53, j * 50 + 53, 44, 44);
                }  //������
                if (status[i][j] == WHITE) {
                    g2d.setColor(Color.WHITE);
                    g2d.fillOval(i * 50 + 53, j * 50 + 53, 44, 44);
                }//������
                if (statusLast[i][j]==1){
                	 g2d.setColor(Color.GREEN);
                	 g2d.fillOval(i * 50 + 53, j * 50 + 53, 8, 8);
                }//���һ����־
                if (status[i][j] > 1) {
                	 
                	if(help1 == 0){
                                       g2d.setColor(Color.BLUE);
                    g2d.drawString("X", i * 50 + 75, j * 50 + 75);   //�����������
                	}
                	else{g2d.setColor(Color.BLUE);
                	g2d.drawString("" + (status[i][j] - 1), i * 50 + 75,
                            j * 50 + 75);   //�ɳ��Ӵ���ʾ�ɳ��ӵĸ���
                }
                
                }

                g2d.setColor(Color.BLACK);  //�ײ�����
                g2d.fillOval(200, 470, 30, 30);

                g2d.setColor(Color.WHITE);
                g2d.fillOval(300, 470, 30, 30);

                g2d.setColor(bgColor);
                g2d.fillRect(240, 470, 60, 30);
                g2d.fillRect(340, 470, 60, 30);
                g2d.setColor(Color.RED);  //����
                g2d.drawString("" + blackCount, 240, 490);
                g2d.drawString("" + whiteCount, 340, 490);

            }
        }
        //jug(colorStatus);
    }

    public void initComponent() {        
        infoText = new JTextArea();      //������Ϣ  
        setLayout(new BorderLayout());
        infoText.setEditable(false);
        infoText.setBackground(Color.PINK);
        infoText.setForeground(Color.BLACK);
        

      
    }

    public void initChess() {
        
       
        chessNum = 4;
        count = 0;
        colorStatus = BLACK;
        status = new int[8][8];
        status[3][3] = BLACK;
        status[4][4] = BLACK;
        status[3][4] = WHITE;
        status[4][3] = WHITE;
        statusW = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                statusW[i][j] = 2;
            }
        }

        for (int i = 0; i < 4; i++) {
            statusW[i + 2][0] = 3;
            statusW[i + 2][1] = 1;
            statusW[i + 2][7] = 3;
            statusW[i + 2][6] = 1;
            statusW[0][i + 2] = 3;
            statusW[1][i + 2] = 1;
            statusW[7][i + 2] = 3;
            statusW[6][i + 2] = 1;
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                statusW[i][j] = 0;
                statusW[7 - i][j] = 0;
                statusW[i][7 - j] = 0;
                statusW[7 - i][7 - j] = 0;
            }
        }
        statusW[0][0] = 4;
        statusW[0][7] = 4;
        statusW[7][0] = 4;
        statusW[7][7] = 4;

        jug(colorStatus);
        String msg="  F1:   ����1\n  F2:   ����2\n  F3:   ����3\n" +
        		"  F4:   ����\n  F5:   ���¿�ʼ";
        infoText.setText(msg+"             \n");
        repaint();
    }

    public void response(Point p) {
        boolean interrupt = false;
        point = setPoint(p);
        if (point != null) {
            int x = point.x / 50 - 1;
            int y = point.y / 50 - 1;
     
            if (check(x, y, colorStatus) > 0) {

                manulChess(x, y, BLACK);
                if (level == 1) {
                    autoChess(WHITE);
                } 
                else if(level ==2){
                    autoChess(WHITE, 2);
                }
                else {
                	autoChess(WHITE,3);
                }
                if (blPass()) {
                	 if (!blPass()) {
                        repaint();
                    } else {
                        interrupt = true;
                    }
                }
                if (blOver(interrupt)) {
                    initChess();
                }
            }

        }

    }

    public void response2(Point p) {
        boolean interrupt = false;
        point = setPoint(p);
        if (point != null) {
            int x = point.x / 50 - 1;
            int y = point.y / 50 - 1;
     
            if (check(x, y, colorStatus) > 0) {

                manulChess(x, y, BLACK);
                if (check(x, y, colorStatus) > 0) {
                manulChess2(x, y, WHITE);
                }
                   
                if (blPass()) {
                    if (!blPass()) {
                        repaint();
                    } else {
                        interrupt = true;
                    }
                }
                if (blOver(interrupt)) {
                    initChess();
                }
            }

        }

    }
    
    public Point setPoint(Point p) {
        if (p.x < 50 || p.x > 450 || p.y < 50 || p.y > 450) {
            return null;
        } else {
            p.x = (p.x / 50) * 50;
            p.y = (p.y / 50) * 50;
            return p;
        }
    }

    public boolean change(int i, int j, int sta) {  //staΪ��ǰ���������ɫ
    	
        boolean flag = false;

        //north
        if (j != 0) {  //sta��λ�����Ϸ�
            if (sta != status[i][j - 1] && 1 == Math.abs(status[i][j - 1])) {
            	
            	//�ж�i*jλ�����µ�����i*(j-1)λ���Ƿ�ͬ,����i*(j-1)λ������
                int m = j - 1;
                while (status[i][j - 1] == status[i][m] && m > 0) {
                    m--;
                    ////ͨ������m�ж�i*(j-1)�ϵ����Ƿ���north�����ϵ�����ɫ��ͬ,��ͬm�Լ�1,ѭ������ʱ��j-m����ͬ��ɫ��,
                }
                if (status[i][m] == sta) {
                	//�жϵ�i,mλ�õ����Ƿ���i,j(��ʼλ)����ɫ��ͬ,��ͬ�Ļ�������֮������Ϊsta����ɫ,������
                    flag = true;
                    for (int n = j - 1; n > m; n--) {
                        status[i][n] = sta;
                        
                    }
                }
            }
        }

        //south
        if (j != 7) {  //tag��λ�����·�
            if (sta != status[i][j + 1] && 1 == Math.abs(status[i][j + 1])) {   
            	
            	//�ж�i*jλ�����µ�����i*(j+1)λ���Ƿ�ͬ,����i*(j+1)λ������
                int m = j + 1;
                while (status[i][j + 1] == status[i][m] && m < 7) {
                    m++;
                    //ͨ������m�ж�i*(j+1)�ϵ����Ƿ���south�����ϵ�����ɫ��ͬ,��ͬm����1,ѭ������ʱ��m-1����ͬ��ɫ��,
                }
                if (status[i][m] == sta) { 
                	//�жϵ�i,mλ�õ����Ƿ���i,j(��ʼλ)����ɫ��ͬ,��ͬ�Ļ�������֮������Ϊsta����ɫ,������
                    flag = true;
                    for (int n = j + 1; n < m; n++) {
                        status[i][n] = sta;
                        
                        
                    }
                }

            }
        }

        //west
        if (i != 0) {
            if (sta != status[i - 1][j] && 1 == Math.abs(status[i - 1][j])) {
                int m = i - 1;
                while (status[i - 1][j] == status[m][j] && m > 0) {
                    m--;
                    
                }
                if (status[m][j] == sta) {
                    flag = true;
                    for (int n = i - 1; n > m; n--) {
                        status[n][j] = sta;
                        
                   
                    }
                }
            }
        }

        //east
        if (i != 7) {
            if (sta != status[i + 1][j] && 1 == Math.abs(status[i + 1][j])) {
                int m = i + 1;
                while (status[i + 1][j] == status[m][j] && m < 7) {
                    m++;
                    
                }
                if (status[m][j] == sta) {
                    flag = true;
                    for (int n = i + 1; n < m; n++) {
                        status[n][j] = sta;
                        
                    }
                }

            }
        }

        //northwest
        if (j != 0 && i != 0) {
            if (sta != status[i - 1][j - 1]
                    && 1 == Math.abs(status[i - 1][j - 1])) {
                int m = i - 1;
                int n = j - 1;
                while (status[i - 1][j - 1] == status[m][n] && m > 0 && n > 0) {
                    m--;
                    n--;
                
                }
                if (status[m][n] == sta) {
                    flag = true;
                    for (int x = i - 1, y = j - 1; x > m; x--, y--) {
                        
                        status[x][y] = sta;
                        

                    }
                }
            }
        }

        //southeast
        if (j != 7 && i != 7) {
            if (sta != status[i + 1][j + 1]
                    && 1 == Math.abs(status[i + 1][j + 1])) {
                int m = i + 1;
                int n = j + 1;
                while (status[i + 1][j + 1] == status[m][n] && m < 7 && n < 7) {
                    m++;
                    n++;
                   
                }
                if (status[m][n] == sta) {
                    flag = true;
                    for (int x = i + 1, y = j + 1; x < m; x++, y++) {
                        
                        status[x][y] = sta;
                       

                    }
                }
            }
        }

        //northeast
        if (j != 0 && i != 7) {
            if (sta != status[i + 1][j - 1]
                    && 1 == Math.abs(status[i + 1][j - 1])) {
                int m = i + 1;
                int n = j - 1;
                while (status[i + 1][j - 1] == status[m][n] && m < 7 && n > 0) {
                    m++;
                    n--;
                   
                }
                if (status[m][n] == sta) {
                    flag = true;
                    for (int x = i + 1, y = j - 1; x < m; x++, y--) {
                        
                        status[x][y] = sta;
                        

                    }
                }
            }
        }

        //southwest
        if (j != 7 && i != 0) {
            if (sta != status[i - 1][j + 1]
                    && 1 == Math.abs(status[i - 1][j + 1])) {
                int m = i - 1;
                int n = j + 1;
                while (status[i - 1][j + 1] == status[m][n] && m > 0 && n < 7) {
                    m--;
                    n++;
                 
                }
                if (status[m][n] == sta) {
                    flag = true;

                    for (int x = i - 1, y = j + 1; x > m; x--, y++) {
                       

                        status[x][y] = sta;
                      
                    }
                }
            }
        }
        return flag;

    }

    public int check(int i, int j, int sta) {     

        int flag = 0;
        if (Math.abs(status[i][j]) == 1) {
            return 0;
        }

        //north
        if (j != 0) {
            if (sta != status[i][j - 1] && 1 == Math.abs(status[i][j - 1])) {
                int m = j - 1;
                while (status[i][j - 1] == status[i][m] && m > 0) {
                    m--;
                    
                    
                }
                if (status[i][m] == sta) {
                    flag = flag + (j - 1 - m);
                    
                }
            }
        }

        //south
        if (j != 7) {
            if (sta != status[i][j + 1] && 1 == Math.abs(status[i][j + 1])) {
                int m = j + 1;
                while (status[i][j + 1] == status[i][m] && m < 7) {
                    m++;
                  
                }
                if (status[i][m] == sta) {
                    flag = flag + (m - j - 1);
                }

            }
        }

        //west
        if (i != 0) {
            if (sta != status[i - 1][j] && 1 == Math.abs(status[i - 1][j])) {
                int m = i - 1;
                while (status[i - 1][j] == status[m][j] && m > 0) {
                    m--;
                    
                }
                if (status[m][j] == sta) {
                    flag = flag + (i - 1 - m);

                }
            }
        }

        //east
        if (i != 7) {
            if (sta != status[i + 1][j] && 1 == Math.abs(status[i + 1][j])) {
                int m = i + 1;
                while (status[i + 1][j] == status[m][j] && m < 7) {
                    m++;
                   
                }
                if (status[m][j] == sta) {
                    flag = flag + (m - i - 1);

                }

            }
        }

        //northwest
        if (j != 0 && i != 0) {
            if (sta != status[i - 1][j - 1]
                    && 1 == Math.abs(status[i - 1][j - 1])) {
                int m = i - 1;
                int n = j - 1;
                while (status[i - 1][j - 1] == status[m][n] && m > 0 && n > 0) {
                    m--;
                    n--;
                    
                }
                if (status[m][n] == sta) {
                    flag = flag + (i - 1 - m);

                }
            }
        }

        //southeast
        if (j != 7 && i != 7) {
            if (sta != status[i + 1][j + 1]
                    && 1 == Math.abs(status[i + 1][j + 1])) {
                int m = i + 1;
                int n = j + 1;
                while (status[i + 1][j + 1] == status[m][n] && m < 7 && n < 7) {
                    m++;
                    n++;
                    
                }
                if (status[m][n] == sta) {
                    flag = flag + (m - i - 1);

                }
            }
        }

        //northeast
        if (j != 0 && i != 7) {
            if (sta != status[i + 1][j - 1]
                    && 1 == Math.abs(status[i + 1][j - 1])) {
                int m = i + 1;
                int n = j - 1;
                while (status[i + 1][j - 1] == status[m][n] && m < 7 && n > 0) {
                    m++;
                    n--;
                    
                }
                if (status[m][n] == sta) {
                    flag = flag + (j - 1 - n);

                }
            }
        }

        //southwest
        if (j != 7 && i != 0) {
            if (sta != status[i - 1][j + 1]
                    && 1 == Math.abs(status[i - 1][j + 1])) {
                int m = i - 1;
                int n = j + 1;
                while (status[i - 1][j + 1] == status[m][n] && m > 0 && n < 7) {
                    m--;
                    n++;
                    
                }
                if (status[m][n] == sta) {
                    flag = flag + (i - 1 - m);

                }
            }
        }
        return flag;

    }

    public void release() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (status[i][j] > 1) {
                    status[i][j] = 0;
                }
            }
        }
    }

    public boolean jug(int color) {
        
        int flag = 0;
        int isPass = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (status[i][j] == 0) {
                    flag = check(i, j, color);
                    {
                        if (flag != 0) {
                            status[i][j] = flag + 1;
                            isPass++;
                        }
                    }

                } else {
                    flag = 0;
                }

            }
        }
        if (isPass > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean blOver(boolean interrupt) {
        String msg = "";
        whiteCount=0;
        blackCount=0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	
                if (status[i][j] == BLACK) {
                    blackCount++;
                }
                if (status[i][j] == WHITE) {
                    whiteCount++;
                }
            }
        }
        if ((whiteCount + blackCount) == 64 || blackCount == 0
                || whiteCount == 0 || interrupt) {

            if (whiteCount > blackCount) {
                msg = "�׷�ʤ���׷�:�ڷ�=" + whiteCount + ":" + blackCount;
            } else {
                msg = "�ڷ�ʤ���ڷ�:�׷�" + blackCount + ":" + whiteCount;
            }
            JOptionPane.showConfirmDialog(this, msg, "Game Over",
                    JOptionPane.CLOSED_OPTION);
            return true;
        } else {
            return false;
        }

    }

    public boolean blPass() {
        if (chessNum >= 64) {
            return false;
        }
        if (!jug(colorStatus)) {
            JOptionPane.showMessageDialog(null, "���������,��������");
            colorStatus = -colorStatus;
            count++;
            infoText.append(count + ":pass\n");
            return true;
        } else {
            return false;
        }
    }

    public void manulChess(int x, int y, int color) {
    	oldCount = count;
    	
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	statusLast[i][j] = 0;
            }}
            statusLast[x][y]=1;
            statusOld = new int [8][8];
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                    statusOld[i][j] = status[i][j];
                }
            }
    	if (colorStatus == color) {
        	  release();
            if (status[x][y] == 0) {
                status[x][y] = colorStatus;
             }
                
            change(x, y, colorStatus);
            repaint();
            count++;
            chessNum++;
            String value = "" + x + "*" + y;
            infoText.append(count + ":����  " + value + "\n");  //��ʾ�岽
            colorStatus = -colorStatus;
        }
    }

    public void manulChess2(int x, int y, int color) {
    	oldCount = count;
    	
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	statusLast[i][j] = 0;
            }}
            statusLast[x][y]=1;
            statusOld = new int [8][8];
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
                    statusOld[i][j] = status[i][j];
                }
            }
    	if (colorStatus == color) {
        	  release();
            if (status[x][y] == 0) {
                status[x][y] = colorStatus;
             }
                
            change(x, y, colorStatus);
            repaint();
            count++;
            chessNum++;
            String value = "" + x + "*" + y;
            infoText.append(count + ":����  " + value + "\n");  //��ʾ�岽
            colorStatus = -colorStatus;
        }
    }
    
    public void autoChess(int color) {  //�����ȼ�1,��1���ڳ�������λ��
    	
    	if (colorStatus == color) {
            int flag = 0;
            int x = 0;
            int y = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 7; j >= 0; j--) {
                    int temp = check(i, j, colorStatus);   
                    //ͨ��check��������ÿ�����µ�λ���ܳԶ��ٸ���,����flag��ʾi,jλ���ܳԵ���,
                    if (temp > flag) { //���ܳԵ������ʱ,�����λ�õ�����i,j��ֵ��x,y
                        flag = temp;
                        x = i;
                        y = j;
                    }
                }
            }
            if (flag > 0) {   
            	for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                    	statusLast[i][j] = 0;
                    }}
            	statusLast[x][y]=1;
                   
                if (status[x][y] == 0) {
                    status[x][y] = color;

                }
                change(x, y, colorStatus);
                repaint();
                colorStatus = -colorStatus;
                count++;
                chessNum++;
                String value = "" + x + "*" + y;
                infoText.append(count + ":�ͼ�  " + value + "\n");
            } else {
                colorStatus = -colorStatus;
            }
        }
    }

    public void autoChess(int color, int level) {
    	
    	if (colorStatus == color && level == 2) {
    		int flag = -1;
            int x = -1;
            int y = -1;
            for (int i = 0; i < 8; i++) {
                for (int j = 7; j >= 0; j--) {
                    if (status[i][j] == 0 && statusW[i][j] > flag) {
                        if (check(i, j, colorStatus) > 0) {
                            flag = statusW[i][j];
                            x = i;
                            y = j;
                        }
                    }

                }
            }
            if (flag >= 0 && x > -1 && y > -1) {
            	 for (int i = 0; i < 8; i++) {
                     for (int j = 0; j < 8; j++) {
                     	statusLast[i][j] = 0;
                     }}
            	 statusLast[x][y]=1;
                if (status[x][y] == 0) {
                    status[x][y] = color;

                }
               
            	statusLast[x][y]=1;
                change(x, y, colorStatus);
                repaint();
                colorStatus = -colorStatus;
                count++;
                chessNum++;
                String value = "" + x + "*" + y;
                infoText.append(count + ":�м�  " + value + "\n");
            } else {
                colorStatus = -colorStatus;
            }
        }
    	
    	
    	//�ȼ�3
    	else if (colorStatus == color && level == 3) {
            int flag = 0;
            int tag1 =0;
            int tag2 =0;
            int tag3 =0;
            int tag4 =0;
            int tag5 =0;
            int tag6 =0;
            int x = 0;
            int y = 0;
          a:  for (int i = 0; i < 8; i++) {
                for (int j = 0; j <8; j++) {
         
               //�ж��Ƿ�Ϊ�ĸ����� 	
               if(angle(i,j,colorStatus)) 
               {
            	int temp = check(i, j, colorStatus);
              	if (temp > flag) {    
            	x=i;
               	y=j;
               	flag = check(i,j,colorStatus);
               	
               	tag1 =1;
               	tag2 =1;
               	tag3 =1;
               	tag4 =1;
               	tag5 =1;
               	tag6 =1;
               	
               	break a; 
               }
               }
                }//for2
          }//for1
       /*         	
               //�ж��Ƿ���ռ��
            if(tag1==0){ 
            	 a:  for (int i = 0; i < 8; i++) {
                     for (int j = 0; j <8; j++) {
             if(border(i,j,colorStatus))
                {
                    if(wborder(i,j,colorStatus)){
                    int temp = check(i, j, colorStatus);
                    if (temp > flag) { 
                    x=i;
                	y=j;
                	flag = check(i,j,colorStatus);
                	
                	tag2=1;
                	tag3=1;
                	tag4=1;
                	tag5=1;
                	tag6=1;
                	break a;	}
                    }
                	if(hborder(i,j,colorStatus)){
                		 int temp = check(i, j, colorStatus);
                         if (temp > flag) { 
                    	x=i;
                    	y=j;
                    	flag = check(i,j,colorStatus);
                    	tag2=1;
                    	tag3=1;
                    	tag4=1;
                    	tag5=1;
                    	tag6=1;
                    	break a;	}
                	}
                }
                     }//for2
            	 }//for1
            }//end if
            */
            
            
               if(tag2==0){
            	   a:  for (int i = 0; i < 8; i++) {
                       for (int j = 0; j <8; j++) {
                // �ж�(22,25,52,55)�ĸ������Ƿ����,����ռ��
                 if (firstAngle(i,j,colorStatus)){
                	 int temp = check(i, j, colorStatus);
                	 if (temp > flag) { 
                	 x=i;
            	     y=j;
            	     flag = check(i,j,colorStatus);
            	     tag3=1;
            	     tag4=1;
            	     tag5=1;
            	     tag6=1;
            	     break a;
                	 }               
                	 }
                       }//for2
            	   }//for1
               }//end if
                	
               if(tag3==0){
            	   a:  for (int i = 0; i < 8; i++) {
                       for (int j = 0; j <8; j++) {
                    	   //��ռ��ij2-5  4*4��,����ռ��
                 if(i>=2&&i<=5&&j>=2&&j<=5)
                	
                	 {
            	int temp = check(i, j, colorStatus);   
                
                if (temp > flag) { 
                    flag = temp;
                    x = i;
                    y = j;
                    tag4=1;
                    tag5=1;
                    tag6=1;
                    break a;
                }
               }
                }   //�ڶ���for    
          }//��һ��for
    }//end if  
            if(tag4 == 0){
            	 a:  for (int i = 0; i < 8; i++) {
                     for (int j = 0; j <8; j++) {
            	  	
               //�������ʱ�̲���11,16,61,66
                	if((i==1&&j==1)||(i==1&&j==6)||(i==6&&j==1)||
                	(i==6&&j==6))
                	{
                		continue;
                	}
                	else{	int temp = check(i, j, colorStatus);   
                        
                        if (temp > flag) { 
                            x = i;
                            y = j;
                            flag = temp;
                            tag5 = 1;
                            tag6 = 1;
                            break a;
                	}
                }     	
                     }//for2
            	 }//for1    
            }//end if
           
        /*    if (tag5 ==0){
            	a:  for (int i = 0; i < 8; i++) {
                    for (int j = 0; j <8; j++) {
                    	if( last(i,j,colorStatus)){
        			int temp = check(i, j, colorStatus);   
                  
                        x = i;
                        y = j;
                        flag = temp;
                        tag6=1;
                        
                        break a;
                    
                    	}
                    	
        		
                    }//for2
            	}//for1
       }//end if
          */  
            
            if (tag6 ==0){
            	a:for (int i = 0; i < 8; i++) {
                    for (int j = 0; j <8; j++) {

                    		int temp = check(i, j, colorStatus);   
                            
                            if (temp > flag) { 
                                x = i;
                                y = j;
                                flag = temp;
                                
                                
                                break a;
                            }
                            	
                            	  
                    }//for2
            }//for1
            }
    	
    
    
    	 if (flag > 0) {   
    		 for (int i = 0; i < 8; i++) {
                 for (int j = 0; j < 8; j++) {
                 	statusLast[i][j] = 0;
                 }}
    		 statusLast[x][y]=1;
             if (status[x][y] == 0) {
                 status[x][y] = color;

             }
             tag1=0;
             tag2=0;
             tag3=0;
             tag4=0;
             tag5=0;
             tag6=0;
             change(x, y, colorStatus);
             repaint();
             colorStatus = -colorStatus;
             count++;
             chessNum++;
             String value = "" + x + "*" + y;
             infoText.append(count + ":�߼�  " + value + "\n");
         }//end if 
    	 else {
             colorStatus = -colorStatus;
             }
       }
    }	
     	
    //�ж�ˮƽ����߽��ϸ�һλ�ǶԷ���
    public boolean wborder(int i,int j,int c){
    	if((i!=0&&i!=7)&&(j==0||j==7)){
    		colorStatus = c;
    		
    		if ((status[i+1][j] == -1)||
    		     (status[i-1][j]==-1)){
    		    	 
    		    return false;
    		     }
    		     else{
    		    	 return true	;
    		    	  } 
    		
    	}	
    	return false;
    }
    
//  �ж��ݷ���߽��ϸ�һλ�Ƿ��жԷ���
    public boolean hborder(int i,int j,int c){
    	if((j!=1&&j!=6)&&(i==0||i==7)){
    		colorStatus = c;
    		
    		if ((status[i][j+1] == -1)||
    		     (status[i][j-1]== -1)){
    				 
    			return false;
    		     }
    		     else{
    		    	 return true;
    		    	  }  
    		
    		
    	}	
    	return false;
    }
    
	public boolean border(int i,int j,int c)
	{
    colorStatus = c;
	 if(i==0||j==0||i==7||j==7){
		 
		return true;
            }
	 else{
		 return false;
	 }
	
	}
    
	
	
	public boolean angle(int i,int j,int c)
    //�ж����������ĸ������Ƿ����,����ռ��
    {colorStatus= c;
     if((i==0&&j==0)||(i==0&&j==7)||(i==7&&j==0)||(i==7&&j==7)){
   	  
    	 return true;
     }
     else{
    	 return false;
     }
    
   	 }
	
	public void vs(int i)
	{
	 vs = i;	
	 initChess();
	}
	
    public boolean firstAngle(int i,int j,int c)
    //�ж�(22,25,52,55)�ĸ������Ƿ����,����ռ��
    {colorStatus = c;
     if((i==2&&j==2)||(i==2&&j==5)||(i==5&&j==2)||(i==5&&j==5)){
    	 if(check(i,j,colorStatus)>0){
    		 return true;
    	 }
     else{
    	 return false;
     }
   	 }  return false;
    }
    
    public boolean last(int i,int j,int c)
    {
    	colorStatus = c;
    	if((i==0&&j==0)||(i==0&&j==7)||(i==7&&j==0)||(i==7&&j==7))
    	{
    		if(check(i,j,colorStatus)>0)
    		{
    		a:for(int a=0;a<8;a++){
    			for(int b=0;b<8;b++){
    				if(Math.abs(status[a][b])!=1){
    					return false;
    				}
    				else{
    					return true;
    				}
    			}//for2
    		}//for1
    		}
    		else{
    			return false;
    		}
    	}
    	else{
    		return false;
    	}return false;
    		}
    
    public void help(int x)
    {
    	help1=x;
        repaint();
    }
    
    public JTextArea getInfo(){
    	return this.infoText;
    }
    
    public void regret()
    {
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                
            	status[i][j] = statusOld[i][j];
                }
            }
    	count = oldCount;
    	infoText.append("���˵���"+oldCount+"��\n");
    	repaint();
    }

    class myKeyListener extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            //System.out.println(e.getKeyChar());
            if (e.getKeyCode() == KeyEvent.VK_F5) {
                initChess();

            }
            if (e.getKeyCode() == KeyEvent.VK_F1) {
                level = 1;
                initChess();
            }
            if (e.getKeyCode() == KeyEvent.VK_F2) {
                level = 2;
                initChess();
            }
            if (e.getKeyCode() == KeyEvent.VK_F3) {
                level = 3;
                initChess();
            }
            if (e.getKeyCode() == KeyEvent.VK_F4) {
                regret();
            }
        }
    }
}