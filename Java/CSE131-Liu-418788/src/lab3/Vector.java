package lab3;
//TA: use this header format everywhere.
/**
 * Name:Ruiqi,Liu	
 * Lab Section: A			
 * Date: 13/02/2012
 * Vector.java
 * CSE 131 Lab 3
 */

public class Vector {
	private double deltaX;
	private double deltaY;


	public Vector(double deltaX, double deltaY){

		this.deltaX=deltaX;
		this.deltaY=deltaY;

	}

	public double getDeltaX(){
		return deltaX;
	}
	public double getDeltaY(){
		return deltaY;
	}
	public Vector plus(Vector v){

		Vector vec=new Vector(0,0);
		vec.deltaX=this.deltaX+v.deltaX;
		vec.deltaY=this.deltaY+v.deltaY;
		return vec;
	}
	public Vector scale(double factor){

		Vector vec=new Vector(0,0);
		vec.deltaX=factor*this.deltaX;
		vec.deltaY=factor*this.deltaY;
		return vec;
	}
	
	//return a new Vector that has the magnitude of mag
	public Vector rescale(double mag){
		Vector vec=new Vector(0,0);
		double mag1=this.magnitude();
		if (mag1>0) {
			double scale1=mag/mag1;
			return scale(scale1);
		}
		else {
			vec.deltaX=mag;
			vec.deltaY=0;
			return vec;
		}
	}



	public double magnitude(){
		double mag=Math.sqrt(deltaX*deltaX+deltaY*deltaY);
		return mag; 
	}

	public Vector deflectX(){
		Vector vec=new Vector(0,0);
		vec.deltaX=-this.deltaX;
		vec.deltaY=this.deltaY;
		return vec;
	}
	public Vector deflectY(){
		Vector vec1=new Vector(0,0);
		vec1.deltaX=this.deltaX;
		vec1.deltaY=-this.deltaY;
		return vec1;
	}

}



