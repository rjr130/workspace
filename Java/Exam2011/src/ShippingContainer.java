

public class ShippingContainer {
	private long count = 1;
	private long idNumber;
	private double weight;
	
	public final double MAXLOADWEIGHT;
	public final double EMPTYWEIGHT;
	
	ShippingContainer(double maxLoadWeight, double emptyWeight){
		this.MAXLOADWEIGHT = maxLoadWeight;
		this.EMPTYWEIGHT = emptyWeight;
		this.count++;
	}
	
	public long getQuantity(){
		return this.count;
	}
	
	public long getNumber(){
		return this.idNumber;
	}
	
	public double getWeight(){
		return this.weight;
	}
	
	public boolean load(double addition){
		this.weight += addition;
		if(this.weight > this.MAXLOADWEIGHT){
			return false;
		}
		return true;
	}
	
	public boolean isEmpty(){
		if(this.weight == 0){
			return true;
		}
		return false;
	}
	
	public boolean isFull(){
		if(this.weight == this.MAXLOADWEIGHT){
			return true;
		}
		return false;
	}
	
	public boolean unload(double traction){
		this.weight -= traction;
		if(this.weight < 0){
			return false;
		}
		return true;
	}
}
