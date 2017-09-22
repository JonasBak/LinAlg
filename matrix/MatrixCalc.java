package matrix;

import vector.Vector;
import vector.VectorSpace;

public class MatrixCalc {
	
	public static Matrix add(Matrix A, Matrix B){
		if (A.getN() != B.getN() || A.getM() != B.getM())
			throw new IllegalArgumentException("asgdad");
		
		double cont[] = new double[A.getN() * A.getM()];
		
		for (int n = 0; n < A.getN(); n++){
			for (int m = 0; m < A.getM(); m++){
				cont[A.toIndex(n, m)] = A.get(n, m) + B.get(n, m);
			}
		}
		
		return new Matrix(A.getN(), A.getM(), cont);
	}
	
	public static Matrix multiply(Matrix A, double d){
		Matrix B = A.clone();
		
		B.multiply(d);
		
		return B;
	}
	
	public static Matrix multiply(Matrix A, Matrix B){
		if (A.getM() != B.getN())
			throw new IllegalArgumentException("Arguments cannot be multiplied");
		
		
		int n = A.getN();
		int m = B.getM();
		
		double cont[] = new double[n * m];
		
		for (int y = 0; y < n; y++){
			for (int x = 0; x < m; x++){
				for (int t = 0; t < A.getM(); t++){
					cont[y * m + x] += A.get(y, t) * B.get(t, x);
					
				}
			}
		}
		
		return new Matrix(n, m, cont);
		
	}

	public static double determinant(SquareMatrix A){
		PLU plu = new PLU(A);
		
		double det = 1;
		
		System.out.println(plu);
		
		for (int i = 0; i < A.getN(); i++){
			det = det * plu.getL().get(i, i) * plu.getU().get(i, i);
			System.out.println(det);
		}
		
		det = det * plu.getnPerm();
		
		return det;
		
	}
	
	public static SquareMatrix identity(int n){
		double cont[] = new double[n*n];
		for (int i = 0; i < n; i++){
			cont[i * n + i] = 1;
		}
		
		return new SquareMatrix(n, cont);
	}
	
	public static SquareMatrix inverse(SquareMatrix A){
		//if (determinant(A) == 0)
		//	throw new IllegalArgumentException("lasdjflas");
		
		SquareMatrix ret = identity(A.getN());
		
		GaussJordan.reduce(A, ret);
		
		return ret;
	} 
	

	public static Matrix transpose(Matrix A){
		
		double cont[] = new double[A.getN() * A.getM()];
		for (int i = 0; i < cont.length; i++){
			int pos[] = A.toPosition(i);
			cont[pos[1] * A.getN() + pos[0]] = A.get(pos[0], pos[1]);
			
		}
		return new Matrix(A.getM(), A.getN(), cont);
	}
	
	
	public static Vector Axb(Matrix A, Vector b){
		if (A.getM() > A.getN())
			return null;
		
		Vector x = b.clone();
		
		Matrix B = A.clone();
		
		GaussJordan.reduce(B, x);
		
		for (int n = 0; n < B.getN(); n++)
			if (B.isZeroRow(n) && x.get(n) != 0 || !B.onlyPivotRow(n))
				return null;
		
		double [] cont = new double [A.getM()];
		
		for (int i = 0; i < cont.length; i++)
			cont[i] = x.get(i);
		
		return new Vector(cont);
	}
	
	public VectorSpace AxbSpace(Matrix A, Vector b){
		
		return null;
	}
	
	public static boolean isLinInd(Vector ... vecs){
		return isLinInd(new Matrix(vecs));
	}
	
	public static boolean isLinInd(Matrix A){
		return (new Vector(A.getM())).equals(Axb(A, new Vector(A.getN())));
	}
	
	public static void main(String[] args) {
		Matrix A = new Matrix(3, 2, new double[]{1, 0, 2, 3, 0, 2});
		
		Vector b = new Vector(1, 8, 4);
		
		Vector x = Axb(A, b);

		System.out.println(" A " + A);
		System.out.println(" x " + x);
		System.out.println(" b " + b);


		System.out.println(isLinInd(
				new Vector(1, 0, 0),
				new Vector(0, 1, 0),
				new Vector(1, 1, 0)));
		//System.out.println(multiply(A, x));
		
	}
}
