package vector;

public class Vector extends matrix.Matrix {
	public Vector(int n, double [] cont){
		super(n, 1, cont);
	}
	
	public Vector(int n){
		this(n, new double[n]);
	}
	
	public Vector(double ... ds){
		this(ds.length, ds);
	}
	
	public double get(int n){
		return cont[n];
	}
	
	public double length(){
		return Math.sqrt(VectorCalc.dot(this, this));
	}
	
	public void normalize(){
		multiply(1 / length());
	}
	
	@Override
	public Vector clone(){
		return new Vector(n, cont.clone());
	}
	
	public boolean equals(Vector v){
		return super.equals(v);
	}
	
	
	@Override
	public String toString() {
		String ret = "Vector " + getN() + "\n";
		
		for (int i = 0; i < cont.length; i++){
			ret += "| " + cont[i] + " |\n";
			
		}
		
		return ret;
	}
}
