package matrix;

public class SquareMatrix extends Matrix {
	
	public SquareMatrix(SquareMatrix A){
		super(A);
	}
	
	public SquareMatrix(int n, double [] cont){
		super(n, n, cont);
	}
	
	
}
