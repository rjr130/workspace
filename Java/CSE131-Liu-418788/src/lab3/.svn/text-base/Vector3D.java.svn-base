package lab3;


	public class Vector3D {
		private double deltaX;
		private double deltaY;
		private double deltaZ;

		public Vector3D(double deltaX, double deltaY, double deltaZ){

			this.deltaX=deltaX;
			this.deltaY=deltaY;
			this.deltaZ=deltaZ;
		}

		public double getDeltaX(){
			return deltaX;
		}
		public double getDeltaY(){
			return deltaY;
		}
		public double getDeltaZ(){
			return deltaZ;
		}
		
		public Vector3D plus(Vector3D V){

			Vector3D Vec=new Vector3D(0,0,0);
			Vec.deltaX=this.deltaX+V.deltaX;
			Vec.deltaY=this.deltaY+V.deltaY;
			Vec.deltaZ=this.deltaZ+V.deltaZ;
			return Vec;
		}
		public Vector3D scale(double factor){

			Vector3D Vec=new Vector3D(0,0,0);
			Vec.deltaX=factor*this.deltaX;
			Vec.deltaY=factor*this.deltaY;
			Vec.deltaZ=factor*this.deltaZ;
			return Vec;
		}
		
		//return a new Vector that has the magnitude of mag
		public Vector3D rescale(double mag){
			Vector3D Vec=new Vector3D(0,0,0);
			double mag1=this.magnitude();
			if (mag1>0) {
				double scale1=mag/mag1;
				return scale(scale1);
			}
			else {
				Vec.deltaX=mag;
				Vec.deltaY=0;
				Vec.deltaZ=0;
				return Vec;
			}
		}



		public double magnitude(){
			double mag=Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ);
			return mag; 
		}

		public Vector3D deflectX(){
			Vector3D Vec=new Vector3D(0,0,0);
			Vec.deltaX=-this.deltaX;
			Vec.deltaY=this.deltaY;
			Vec.deltaZ=this.deltaZ;
			
			return Vec;
		}
		public Vector3D deflectY(){
			Vector3D Vec1=new Vector3D(0,0,0);
			Vec1.deltaX=this.deltaX;
			Vec1.deltaY=-this.deltaY;
			Vec1.deltaZ=this.deltaZ;
			return Vec1;
		}
		public Vector3D deflectZ(){
			Vector3D Vec1=new Vector3D(0,0,0);
			Vec1.deltaX=this.deltaX;
			Vec1.deltaY=this.deltaY;
			Vec1.deltaZ=-this.deltaZ;
			return Vec1;
		}	
		}
	
