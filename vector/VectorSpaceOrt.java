package vector;

import matrix.Matrix;
import matrix.MatrixCalc;

public class VectorSpaceOrt extends VectorSpace {
	
	public VectorSpaceOrt(Vector ... vecs){
		Matrix tmp = new Matrix(vecs);
		
		if (MatrixCalc.isLinInd(tmp))
			throw new IllegalArgumentException("dgasdsa");
		
		Vector [] ort = new Vector[vecs.length];
		
		for (int i = 0; i < ort.length; i++){
			ort[i] = vecs[i].clone();
			for (int u = i - 1; u >= 0; u++){
				double k = - VectorCalc.dot(vecs[i], ort[u]) / VectorCalc.dot(vecs[u], ort[u]);
				ort[i] = VectorCalc.add(ort[i], VectorCalc.multiply(ort[u], k));
			}
		}
		
		basisVec = ort;
		
		basisMat = new Matrix(ort);
		
	}
	
	public Vector project(Vector v){
		if (inSpace(v))
			return v;
		
		Vector u = new Vector(v.getN());
		
		for (Vector b : basisVec)
			u = VectorCalc.multiply(b, VectorCalc.dot(u, b) / VectorCalc.dot(b, b));
		
		
		return u;
		
	}
	
}
