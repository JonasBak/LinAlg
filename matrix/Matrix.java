package matrix;

import java.util.Arrays;

import vector.Vector;

public class Matrix {

	protected double cont[];
	
	protected final int n, m;
	
	public Matrix(Matrix A){
		n = A.n;
		m = A.m;
		cont = A.cont.clone();
	}
	
	public Matrix(Vector v, Vector ... vecs){
		n = v.getN();
		m = vecs.length + 1;
		
		for (Vector vec : vecs)
			if (vec.getN() != n)
				throw new IllegalArgumentException("asdglajd");
		
		cont = new double[n * m];
		
		for (int i = 0; i < n * m; i++){
			if (i % m == 0)
				cont[i] = v.get(i / m);
			else
				cont[i] = vecs[i % m - 1].get(i / m);
		}
	}
	

	public Matrix(Vector [] vecs){
		this(vecs[0], (Vector [])Arrays.copyOfRange(vecs, 1, vecs.length));
	}
	
	
	public Matrix(int n, int m, double [] cont){
		if (cont.length != n * m && n * m > 0)
			throw new IllegalArgumentException("Matrix contents given does not match given size");
		
		this.n = n;
		this.m = m;
		this.cont = cont.clone();
		
	}
	
	public Matrix(int n, int m){
		this(n, m, new double [n * m]);
	}
	
	public int toIndex(int n, int m){
		return n * this.m + m;
	}
	
	public int [] toPosition(int i){
		return new int[] {i / this.m, i % this.m};
	}
	
	
	public int getN() {
		return n;
	}
	
	public int getM() {
		return m;
	}
	
	
	public double get(int n, int m){
		int i = toIndex(n, m);
		return cont[i];
	}
	
	public void set(int n, int m, double value){
		cont[toIndex(n, m)] = value;
	}
	
	public void multiply(double d){
		for (int i = 0; i < cont.length; i++)
			cont[i] = cont[i] * d;
	}
	
	public String toString() {
		String ret = "Matrix " + n + "x" + m + "\n";
		
		for (int i = 0; i < cont.length; i++){
			if (i % m == 0)
				ret += "|";
			ret += " " + cont[i] + " ";
			if ((i + 1) % m == 0)
				ret += "|\n";
			
		}
		
		return ret;
	}
	
	public Matrix clone(){
		double cont [] = this.cont.clone();
		
		return new Matrix(n, m, cont);
	}
	
	public boolean equals(Matrix A){
		if (A == null || n != A.getN() || m != A.getM())
			return false;
		
		for (int i = 0; i < cont.length; i++){
			int pos[] = toPosition(i);
			if (cont[i] != A.get(pos[0], pos[1]))
				return false;
		}
		
		return true;
	}

	public boolean isZeroRow(int n){
		for (int m = 0; m < this.m; m++)
			if (get(n, m) != 0)
				return false;
		return true;
	}

	public boolean isZeroCol(int m){
		for (int n = 0; n < this.n; n++)
			if (get(n, m) != 0)
				return false;
		return true;
	}
	
	public boolean onlyPivotRow(int n){
		int nNon = 0;
		for (int m = 0; m < this.m; m++){
			if (get(n, m) != 0)
				nNon += 1;
		}
		return nNon <= 1;
	}
	
	public static void main(String[] args) {
		Matrix m = new Matrix(2, 3, new double[] {0,1,2,3,4,5});
		
		System.out.println(m);
		
		
		System.out.println(m);
		
	}
}
