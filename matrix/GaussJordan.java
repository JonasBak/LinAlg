package matrix;

public class GaussJordan {
	public static void reduce(Matrix A, Matrix B){
		if (B != null && A.getN() != B.getN())
			throw new IllegalArgumentException("blablabla");
		
		gauss(A, B);
		jordan(A, B);
	}
	
	
	private static void gauss(Matrix A, Matrix B){
		int n = 0, m = 0;
		while(n < A.getN() && m < A.getM()){
			int tmp = fixPivot(n, m, A, B);
			if (tmp == -1){
				m++;
			}
			else {
				m = tmp;
				for (int y = n + 1; y < A.getN(); y++){
					double k = A.get(y, m) / A.get(n, m);
					RowOperations.add(A, n, y, -k);
					
					if (B != null)
						RowOperations.add(B, n, y, -k);
				}
				n++;
				m++;
			}
		}
	}
	
	private static void jordan(Matrix A, Matrix B){
		for (int n = A.getN() - 1; n >= 0; n--){
			int m = getPivot(n, A);
			if (m != -1){
				for (int y = n - 1; y >= 0; y--){

					double k = A.get(y, m) / A.get(n, m);
					RowOperations.add(A, n, y, -k);
					
					if (B != null)
						RowOperations.add(B, n, y, -k);
				}
				
				double k = 1 / A.get(n, m);
				
				RowOperations.multiply(A, n, k);
				
				if (B != null)
					RowOperations.multiply(B, n, k);
			}
		}
		
		
	}
	
	private static int fixPivot(int n, int m, Matrix A, Matrix B){
		if (A.get(n, m) != 0)
			return m;
		while (m < A.getM()){
			for (int y = n + 1; y < A.getN(); y++){
				if (A.get(y, m) != 0){
					RowOperations.swap(A, y, n);
					if (B != null)
						RowOperations.swap(B, y, n);
					return m;
				}
			}
			m++;
		}
		return -1;
	}
	
	private static int getPivot(int n, Matrix A){
		for (int m = 0; m < A.getM(); m++)
			if (A.get(n, m) != 0)
				return m;
		return -1;
	}
	
	public static void main(String[] args) {
		Matrix A = new Matrix(3, 2, new double[] {0, 1, 2, 3, 4, 5});
		
		System.out.println(A);
		
		reduce(A, null);
		
		System.out.println(A);
		
	}
}
