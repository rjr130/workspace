
public class Email {
	public static void main(String[] args){
		String email = "rjr130@gmail.com";
		int count = 0;
		
		for(int i = 0; i < email.length(); i++){
			count = email.indexOf("@");	
			}
		String name = email.substring(count+1);
		System.out.println(name);
		for(int i = 0; i < count; i++){
			System.out.print(email.charAt(i));
		}
	}
}
