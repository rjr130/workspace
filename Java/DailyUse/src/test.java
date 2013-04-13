
public class test {
	public static void main (String args[]) {
		int a[]={1,2,3,3,4,4,4,4,4,5,5,5,6,7,8,8,8,9,100,102};
		int n = 20;


		int i,j,flag=0;

		for(i=0;i<n;i++){
			for(j=i; j<n-1; j++){
				if( a[j+1]!=a[j] )
					break;
			}
			if(i!=j)
				flag--;

			flag++;
			a[flag]=a[j+1];
			i=j;
		}

		for(i=0;i<flag;i++) {
			System.out.println(a[i]);
		}
	}
}
