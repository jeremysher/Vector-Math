package io.github.jeremysher.vector;

public abstract class VectorField {
	
	private static final double delta = 0.001;

	public abstract Vector function(Vector p);
	
	//divergence of vector field at point p
	public double div(Vector p) {
		if (p.getSize() == function(p).getSize()) {
			double sum = 0;
			for (int i = 0; i < p.getSize(); i++)
				sum += part(p, i, i);
			return sum;
		} else {
			return 0;
		}
	}
	
	//curl of a vector field at point p
	public Vector curl(Vector p) {
		if (p.getSize() <= 3 && p.getSize() <= 3) {
			double x = part(p, 2, 1) - part(p, 1, 2);
			double y = part(p, 0, 2) - part(p, 2, 0);
			double z = part(p, 1, 0) - part(p, 0, 1);
			return new Vector(x, y, z);
		} else {
			return new Vector(0, 0, 0);
		}
	}
	
	//partial derivative at point p
	public double part(Vector p, int topComp, int bottomComp) {
		double pVal = p.getComponent(bottomComp);
		double p1 = pVal - delta;
		double p2 = pVal + delta;
		double f1 = function(p.replaceComponent(bottomComp, p1)).getComponent(topComp);
		double f2 = function(p.replaceComponent(bottomComp, p2)).getComponent(topComp);
		return (f2 - f1) / (2 * delta);
	}
	
}
