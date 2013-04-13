package lab3;
//TA: add a header with your name, the name of the file (Point.java) etc.

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Point {
	private double x;
	private double y;

	public Point(double x, double y){
		this.x=x;
		this.y=y;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}

	public String tostring(){
		String s= "(" + x + "," + y + ")";
		return s;
	}

	public Point plus(Vector v){
		Point pt=new Point(0,0);
		pt.x=this.x+v.getDeltaX();
		pt.y=this.y+v.getDeltaY();
		return pt;
	}
	public Vector minus(Point p){
		return new Vector(this.x - p.getX(), this.y - p.getY());
	}

	public double distance(Point p){
		double Distance=Math.sqrt((this.x-p.x)*(this.x-p.x)+(this.y-p.y)*(this.y-p.y));
		return Distance;
	}
}			




