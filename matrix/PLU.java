package matrix;

public class PLU {
	Matrix P, L, U;
	int nPerm;
	
	public PLU(SquareMatrix A){
		U = A.clone();
		L = MatrixCalc.identity(A.getN());
		P = MatrixCalc.identity(A.getN());
		
		
		
		nPerm = 1;
		
		modGauss();
	}
	
	public Matrix getP() {
		return P;
	}
	public Matrix getL() {
		return L;
	}
	public Matrix getU() {
		return U;
	}
	public int getnPerm() {
		return nPerm;
	}
	

	private void modGauss(){
		
		int n = 0, m = 0;
		while(n < U.getN() && m < U.getM()){
			int tmp = fixPivot(n, m);
			if (tmp == -1){
				m++;
			}
			else {
				
				m = tmp;
				for (int y = n + 1; y < U.getN(); y++){
					double k = U.get(y, m) / U.get(n, m);
					RowOperations.add(U, n, y, -k);
					
					L.set(y, m, k);
					
				}
				n++;
				m++;
			}
		}
		
	}

	private int fixPivot(int n, int m){
		if (U.get(n, m) != 0)
			return m;
		while (m < U.getM()){
			for (int y = n + 1; y < U.getN(); y++){
				if (U.get(y, m) != 0){
					RowOperations.swap(U, y, n);

					//RowOperations.swap(L, y, n);
					RowOperations.swap(P, y, n);
					nPerm = -nPerm;
					return m;
				}
			}
			m++;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return "P " + P.toString() + "L " + L.toString() + "U " + U.toString();
	}

}
