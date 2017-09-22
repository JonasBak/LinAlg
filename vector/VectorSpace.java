package vector;

import matrix.Matrix;
import matrix.MatrixCalc;


public class VectorSpace {
	protected Matrix basisMat;
	protected Vector [] basisVec;
	
	private VectorSpace(Matrix A){
		if (!MatrixCalc.isLinInd(A))
			throw new IllegalArgumentException("uhouhdfoa");
		
		basisMat = A.clone();
	}
	
	public VectorSpace(Vector ... vecs){
		this(new Matrix(vecs));
		
		basisVec = vecs.clone();
	}
	
	public boolean inSpace(Vector v){
		return MatrixCalc.Axb(basisMat, v) != null;
	}
	
	public VectorSpaceOrt getOrthogonal(){
		return new VectorSpaceOrt(basisVec);
	}
	
}
