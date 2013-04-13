package File;

public class DataApp {
	public static void main (String[] args) {
		int data = 11;
		Data d1 = new Data ();
		d1.set(data);
		Data d2 = d1;
		method (d1);
		data = d2.get();
		d2 = new Data ();
		d2.set(33);
		System.out.println(data + "," + d1.get() + "," + d2.get());
	}
	public static void method (Data d) {
		d.set(22);
	}
}
