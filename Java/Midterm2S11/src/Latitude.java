
public class Latitude {
	private int degrees;
	private int minutes;
	private int seconds;
	private String direction;
	
	public final int MAXIMUMDEGREES = 90;
	public final int MAXIMUMM_S = 59;
	
	public Latitude(){
		this.degrees = 0;
		this.minutes = 0;
		this.seconds = 0;
	}
	
	public Latitude(int degrees, int minutes, int seconds){
		this.degrees = degrees;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public void setDegrees(int degrees){
		if(degrees >= 0 && degrees <= 90){
			this.degrees = degrees;
		}
		else if(degrees < 0){
			degrees = 0 - degrees;
		}
		if(degrees > 90){
			degrees = degrees % 90;
			this.degrees = degrees;
		}
	}
	
	public void setMinutes(int minutes){
		if(minutes >= 0 && minutes <= 59){
			this.minutes = minutes;
		}
		else if(minutes < 0){
			minutes = 0 - minutes;
		}
		if(minutes > 59){
			minutes = minutes % 60;
			this.minutes = minutes;
		}
	}
	
	public void setSeconds(int seconds){
		if(seconds >= 0 && seconds <= 59){
			this.seconds = seconds;
		}
		else if(seconds < 0){
			seconds = 0 - seconds;
		}
		if(seconds > 59){
			seconds = seconds % 60;
			this.seconds = seconds;
		}		
	}
	
	public void setDirection(String direction){
		this.direction = direction;
	}
	
	public String toString(){
		return this.degrees + "`" + this.minutes + "'" + this.seconds + "''" + this.direction; 
	}
	
	public boolean equals(Latitude l1, Latitude l2){
		if(l1.degrees == l2.degrees && l1.minutes == l2.minutes && l1.seconds == l2.seconds 
				&& l1.direction.equals(l2.direction)){
			return true;
		}
		else{
			return false;
		}
	}	
}
