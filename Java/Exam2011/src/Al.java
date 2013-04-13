import java.util.ArrayList;


public class Al {
	public static void main(String[] args){
		ArrayList<String> name = new ArrayList<String>();
		int len = name.size();
		for(int i = 0; i < len; i++){
			name.add(0, name.get(i));
			name.remove(i+1);
		}
	}
}
