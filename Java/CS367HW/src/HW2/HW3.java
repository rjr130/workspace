package HW2;

public class HW3 {
	public static void main (String[] args) {
		sumPoly(null, null);
	}
	
	public static int[] sumPoly (int[] poly1, int[] poly2) throws BadPolynominalException {
		if (poly1 == null || poly2 == null) {
			throw new BadPolynominalException;
		}
		else {
			int min = 0;
			int max = 0;
			int flag = 0;
			if (poly1.length > poly2.length) {
				min = poly2.length;
				max = poly1.length;
				flag = 1;
			}
			else {
				min = poly1.length;
				max = poly2.length;
				flag = 2;
			}
			int[] poly = new int[max];
			for (int i = 0; i < min; i++) {
				poly[i] = poly1[i] + poly2[i];
			}
			for (int i = min; i < max; i++) {
				if (flag == 1) {
					poly[i] = poly1[i];
				}
				else {
					poly [i] =poly2[i];
				}
			}
			return poly;
		}
	}

}
