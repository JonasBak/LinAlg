package matrix;

public class Decomposition {
	public static Matrix[] PLU(Matrix A){
		
		
		return new Matrix[] {};
	}
	
	private static Matrix modGauss(Matrix A){
		Matrix L = MatrixCalc.identity(A.getN());
		Matrix P = MatrixCalc.identity(A.getN());
		
		int p = 1;
		
		int n = 0, m = 0;
		while(n < A.getN() && m < A.getM()){
			int tmp = fixPivot(n, m, A, P);
			if (tmp == -1){
				m++;
			}
			else {
				
				m = tmp;
				for (int y = n + 1; y < A.getN(); y++){
					double k = A.get(y, m) / A.get(n, m);
					RowOperations.add(A, n, y, -k);
					
					L.set(y, m, k);
					
				}
				n++;
				m++;
			}
		}
		
		return L;
		
		
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
}
