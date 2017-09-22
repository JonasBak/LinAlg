package matrix;

public class RowOperations {
	public static void add(Matrix A, int from, int to, double k){
		for (int m = 0; m < A.getM(); m++){
			A.set(to, m, A.get(to, m) + k * A.get(from, m));
		}
	}
	
	public static void swap(Matrix A, int a, int b){
		for (int m = 0; m < A.getM(); m++){
			double tmp = A.get(a, m);
			A.set(a, m, A.get(b, m));
			A.set(b, m, tmp);
		}
	}
	
	public static void multiply(Matrix A, int a, double k){
		for (int m = 0; m < A.getM(); m++){
			A.set(a, m, k * A.get(a, m));
		}
	}
	public static void main(String[] args) {
		Matrix A = new Matrix(3, 2, new double[] {0, 1, 2, 3, 4, 5});
		
		System.out.println(A);
	
		swap(A, 0, 1);
		
		System.out.println(A);
		
		add(A, 1, 2, 2);
		
		System.out.println(A);
	}
}
