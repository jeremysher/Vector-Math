package io.github.jeremysher.vector;

/**
 * This class contains methods for vector field operations, such as partial derivatives, divergence, and curl.
 * 
 * @author Jeremy Sher
 *
 */
public abstract class VectorField {
	
	// Used to compute partial derivatives.
	private static final double delta = 0.00001;

	/**
	 * The vector field. Returns a vector for any point in a space.
	 * 
	 * @param p A position vector that represents a point in space.
	 * @return The resulting vector.
	 */
	public abstract Vector function(Vector p);
	
	/**
	 * Computes the divergence of the vector field at any point.
	 * 
	 * @param p A position vector that represents a point in space.
	 * @return The divergence of the vector field at the specified point.
	 */
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
	
	/**
	 * Computes the curl of the vector field at any point.
	 * 
	 * @param p A position vector that represents a point in space.
	 * @return The curl of the vector field at the specified point.
	 */
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
	
	/**
	 * Computes the partial derivative of a specified component of the vector field with respect to
	 * a specified spacial dimension.
	 * 
	 * @param p A position vector that represents a point in space.
	 * @param topComp A component of the vector field.
	 * @param bottomComp A spacial dimension.
	 * @return The partial derivative.
	 */
	public double part(Vector p, int topComp, int bottomComp) {
		double pVal = p.getComponent(bottomComp);
		double p1 = pVal - delta;
		double p2 = pVal + delta;
		double f1 = function(p.replaceComponent(bottomComp, p1)).getComponent(topComp);
		double f2 = function(p.replaceComponent(bottomComp, p2)).getComponent(topComp);
		return (f2 - f1) / (2 * delta);
	}
	
}
