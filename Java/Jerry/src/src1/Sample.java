package src1;

public class Sample 
{
	public static void main(String[] args) 
	{
		double c = 0.05;
		double temp = 1;
		int i = 0;
		for(i = 0; i < 299; i++)
		{
			temp = temp + c;
			System.out.print("sin("+temp+"*x)+");
		}
		
	}
}
