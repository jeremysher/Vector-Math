package io.github.jeremysher.vector;

/**
 * This class contains methods for vector operations.
 * 
 * @author Jeremy Sher
 *
 */
public class Vector {
	// The set of numbers that make up the vector.
	private final double[] set;
	
	/**
	 * Creates a new 3D vector of zero magnitude.
	 */
	public Vector() {
		set = new double[] {0, 0, 0};
	}

	/**
	 * Creates a new 2D vector.
	 * 
	 * @param x The x component of the vector.
	 * @param y The y component of the vector.
	 */
	public Vector(double x, double y) {
		set = new double[] {x, y};
	}
	
	/**
	 * Creates a new 3D vector.
	 * 
	 * @param x The x component of the vector.
	 * @param y The y component of the vector.
	 * @param z The z component of the vector.
	 */
	public Vector(double x, double y, double z) {
		set = new double[] {x, y, z};
	}
	
	/**
	 * Creates a new 2D vector with a magnitude and a direction.
	 * 
	 * @param mag The magnitude of the vector.
	 * @param theta The {@link Angle} of the vector in degrees.
	 */
	public Vector(double mag, Angle theta) {
		double angle = theta.getRadians();
		set = new double[] {mag * Math.cos(angle), mag * Math.sin(angle)};
	}
	
	/**
	 * Creates a new n-dimensional vector.
	 * 
	 * @param set The set of n components of the vector.
	 */
	public Vector(double[] set) {
		this.set = set;
	}
	
	/**
	 * Returns this vector multiplied by a scalar value.
	 * 
	 * @param s The scalar value to multiply the vector.
	 * @return The scaled vector.
	 */
	public Vector scale(double s) {
		double[] newComps = new double[set.length];
		for (int i = 0; i < set.length; i++)
			newComps[i] = set[i] * s;
		return new Vector(newComps);
	}
	
	/**
	 * Returns the sum of two vectors. If the vectors are not same-dimensional, then the resultant vector
	 * has an amount of dimensions equal to the greatest-dimensional vector.
	 * 
	 * @param a The first vector to add.
	 * @param b The second vector to add.
	 * @return The resultant vector.
	 */
	public static Vector sum(Vector a, Vector b) {
		int length = Math.max(a.getSize(), b.getSize());
		double[] s = new double[length];
		for (int i = 0; i < length; i++)
			s[i] = a.getComponent(i) + b.getComponent(i);
		return new Vector(s);
	}
	
	/**
	 * Returns the sum of n vectors. If the vectors are not same-dimensional, then the resultant vector
	 * has an amount of dimensions equal to the greatest-dimensional vector.
	 * 
	 * @param vectors The set of vectors to add.
	 * @return The resultant vector.
	 */
	public static Vector sum(Vector[] vectors) {
		int length = 0;
		for (Vector v : vectors)
			length = Math.max(length, v.getSize());
		double[] s = new double[length];
		for (int i = 0; i < length; i++) {
			int sum = 0;
			for (Vector v : vectors)
				sum += v.getComponent(i);
			s[i] = sum;
		}
		return new Vector(s);
	}
	
	/**
	 * Returns the difference between two vectors; specifically, the second minus the first.
	 * 
	 * @param a The first vector.
	 * @param b The second vector.
	 * @return The difference between the two vectors.
	 */
	public static Vector difference(Vector a, Vector b) {
		return sum(b, a.scale(-1));
	}
	
	/**
	 * Returns the dot product of two vectors.
	 * 
	 * @param a The first vector.
	 * @param b The second vector.
	 * @return The result of the dot product.
	 */
	public static double dot(Vector a, Vector b) {
		int length = Math.min(a.getSize(), b.getSize());
		double sum = 0;
		for (int i = 0; i < length; i++)
			sum += a.getComponent(i) * b.getComponent(i);
		return sum;
	}

	/**
	 * Returns the cross product of two vectors, only for vectors of 3 dimensions or less.
	 * 
	 * @param a The first vector.
	 * @param b The second vector.
	 * @return The result of the cross product.
	 */
	public static Vector cross(Vector a, Vector b) {
		int length = Math.min(a.getSize(), b.getSize());
		if (length <= 3) {
			return new Vector(a.getComponent(1) * b.getComponent(2)
					- a.getComponent(2) * b.getComponent(1),
					a.getComponent(2) * b.getComponent(0)
					- a.getComponent(0) * b.getComponent(2),
					a.getComponent(0) * b.getComponent(1)
					- a.getComponent(1) * b.getComponent(0)
					);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a unit vector that points from position p to position q.
	 * Instead of this method, use {@link #normalize()} to make a unit vector.
	 * 
	 * @param p Point 1.
	 * @param q Point 2.
	 * @return The unit vector from point 1 to point 2.
	 */
	@Deprecated
	public static Vector makeUnitVector(Vector p, Vector q) {
		Vector displacement = Vector.difference(p, q);
		return displacement.scale(1 / displacement.getMagnitude());
	}
	
	/**
	 * Normalize this vector.
	 * 
	 * @return The resulting unit vector.
	 */
	public Vector normalize() {
		return scale(1 / getMagnitude());
	}
	
	/**
	 * Clone this vector.
	 * 
	 * @return An identical vector.
	 */
	public Vector clone() {
		return new Vector(set);
	}
	
	/**
	 * Returns the distance between two position vectors.
	 * 
	 * @param p The first position.
	 * @param q The second position.
	 * @return The distance.
	 */
	public static double distance(Vector p, Vector q) {
		return difference(p, q).getMagnitude();
	}
	
	/**
	 * Returns this vector with a component replaced.
	 * 
	 * @param index The index of the component.
	 * @param n The replacement value of the component.
	 * @return The vector with a replaced component.
	 */
	public Vector replaceComponent(int index, double n) {
		double[] s = new double[set.length];
		for (int i = 0; i < set.length; i++) {
			if (i == index) s[i] = n;
			else s[i] = set[i];
		}
		return new Vector(s);
	}
	
	/**
	 * Get the direction of the vector as an {@link Angle}. Only for 2D vectors.
	 * 
	 * @return The angle of the vector.
	 */
	public Angle getAngle() {
		if (set.length == 2)
			return new Angle(Math.atan2(set[1], set[0]));
		else
			return null;
	}
	
	/**
	 * Get the magnitude of the vector.
	 * 
	 * @return The magnitude of the vector.
	 */
	public double getMagnitude() {
		double sum = 0;
		for (double comp : set)
			sum += Math.pow(comp, 2);
		return Math.sqrt(sum);
	}
	
	/**
	 * Get a component of the vector.
	 * 
	 * @param index The index of the component.
	 * @return The component at the specified index.
	 */
	public double getComponent(int index) {
		return set.length > index ? set[index] : 0;
	}
	
	/**
	 * Get the x component of the vector.
	 * 
	 * @return The x component of the vector.
	 */
	public double x() {
		return getComponent(0);
	}
	
	/**
	 * Get the y component of the vector.
	 * 
	 * @return The y component of the vector.
	 */
	public double y() {
		return getComponent(1);
	}
	
	/**
	 * Get the z component of the vector.
	 * 
	 * @return The z component of the vector.
	 */
	public double z() {
		return getComponent(2);
	}
	
	/**
	 * Get the amount of dimensions of the vector.
	 * 
	 * @return The amount of dimensions.
	 */
	public int getSize() {
		return set.length;
	}
	
	/**
	 * Returns the array of vector components.
	 * 
	 * @return The array of vector components.
	 */
	public double[] getArray() {
		return set.clone();
	}
	
	/**
	 * Returns the String representation of the vector.
	 * 
	 * @return The String representation of the vector.
	 */
	public String toString() {
		String str = "(";
		for (int i = 0; i < set.length - 1; i++)
			str += set[i] + ", ";
		str += set[set.length - 1] + ")";
		return str;
	}
	
}
