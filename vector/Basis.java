package vector;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import matrix.GaussJordan;
import matrix.Matrix;

public class Basis {
	
	Vector [] basisVecs;
	
	public Basis(Vector ... vecs){
		Matrix tmp = new Matrix(vecs);
		
		GaussJordan.reduce(tmp, null);
		
		List<Integer> pivotCol = new ArrayList<Integer>();
		
		for (int n = 0; n < tmp.getN(); n++){
			for (int m = 0; n < tmp.getM(); m++){
				if (tmp.get(n, m) != 0)
					pivotCol.add(n);			}
		}
		
		Vector [] cols = new Vector[pivotCol.size()];
		
		for (int i = 0; i < cols.length; i++)
			cols[i] = vecs[pivotCol.get(i)].clone();
		
		basisVecs = cols;
	}
	
	
	
	
	
}
