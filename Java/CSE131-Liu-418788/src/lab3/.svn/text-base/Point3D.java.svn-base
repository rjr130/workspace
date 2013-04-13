package lab3;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Point3D {
	private double x;
	private double y;
	private double z;
	
	public Point3D(double x, double y, double z){
		this.x=x;
		this.y=y;
		this.z=z;
	}

	public double getX(){
		return x;
	}

	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	
	public String tostring(){
		String s= "(" + x + "," + y + z +")";
		return s;
	}

	public Point3D plus(Vector3D v){
		Point3D ptD=new Point3D(0,0,0);
		ptD.x=this.x+v.getDeltaX();
		ptD.y=this.y+v.getDeltaY();
		ptD.z=this.z+v.getDeltaZ();
		return ptD;
	}
	public Vector3D minus(Point3D P){
		return new Vector3D(this.x - P.getX(), this.y - P.getY(), this.z-P.getZ());
	}

	public double distance(Point3D P){
		double Distance=Math.sqrt((this.x-P.x)*(this.x-P.x)+(this.y-P.y)*(this.y-P.y)+(this.z-P.z)*(this.z-P.z));
		return Distance;
	}
}
