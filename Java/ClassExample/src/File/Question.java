package File;

public class Question {
	public final double A = 11.22;
	private double b;
	private double c;
	public void one () {
		this.b = 1.0;
	}
	public double two (double d) {
		return this.c + d;
	}
	public void three (double e) {
		this.c = e;
	}
	
	public static void main (String[] args) {
		Question q = new Question();
		//q.A = 1.1;
		//q.b = 1.1;
		//q.one(q.A);
		//q.two(q.A);
		q.three(q.A);
		System.out.println();
	}
}
