package io.github.jeremysher;

public abstract class VectorField {
	
	private static final double delta = 0.001;

	public abstract Vector function(Vector p);
	
	//divergence of vector field at point p
	public double div(Vector p) {
		Vector f = function(p);
		if (p.getSize() == f.getSize()) {
			double sum = 0;
			for (int i = 0; i < p.getSize(); i++) {
				double pVal = p.getComponent(i);
				double p1 = pVal - delta;
				double p2 = pVal +  delta;
				double f1 = function(p.replaceComponent(i, p1)).getComponent(i);
				double f2 = function(p.replaceComponent(i, p2)).getComponent(i);
				sum += (f2 - f1) / (2 * delta);
			}
			return sum;
		} else {
			return 0;
		}
	}
	
}
