package vector;

public class VectorCalc {
	public static double dot(Vector u, Vector v){
		double s = 0;
		for (int n = 0; n < u.getN(); n++)
			s += u.get(n) * v.get(n);
		return s;
	}
	
	public static Vector [] LinIndependent(Vector ... vecs){
		
		
		return vecs;
		
	}
	
	public static Vector add(Vector v, Vector u){
		if (v.getN() != u.getN())
			throw new IllegalArgumentException("");
		
		double [] cont = new double[v.getN()];
		
		for (int i = 0; i < v.getN(); i++)
			cont[i] = v.get(i) + u.get(i);
		
		return new Vector(v.getN(), cont);
	}
	
	public static Vector multiply(Vector v, double d){
		Vector u = v.clone();
		
		u.multiply(d);
		
		return u;
	}

	public static Vector getNormal(Vector v){
		if (v.getN() != 2)
			throw new IllegalArgumentException();
		return normalize(new Vector(-v.get(1), v.get(0)));
	}
	
	public static Vector normalize(Vector v){
		Vector u = v.clone();
		u.normalize();
		return u;
	}

	public static Vector proj(Vector v, Vector u){
		return multiply(u, dot(u, v) / dot(u, u));
	}
	
	public static double dist(Vector u, Vector v){
		return distVec(u, v).length();
	}
	
	public static Vector distVec(Vector u, Vector v){
		return add(v, multiply(u, -1));
	}
}
