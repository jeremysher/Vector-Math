package io.github.jeremysher;

public class Vector {
	private double[] set;
	
	public Vector() {
		set = new double[] {0, 0, 0};
	}

	//2-d vector with 2 components
	public Vector(double x, double y) {
		set = new double[] {x, y};
	}
	
	//3-d vector with 3 components
	public Vector(double x, double y, double z) {
		set = new double[] {x, y, z};
	}
	
	//2-d vector from magnitude and angle
	public Vector(double m, Angle theta) {
		double angle = theta.getRadians();
		set = new double[] {m * Math.cos(angle), m * Math.sin(angle)};
	}
	
	//n-dimensional vector from length n set of components
	public Vector(double[] set) {
		this.set = set;
	}
	
	public Vector scale(double s) {
		double[] newComps = new double[set.length];
		for (int i = 0; i < set.length; i++)
			newComps[i] = set[i] * s;
		return new Vector(newComps);
	}
	
	//vector addition; resultant vector has length of greatest dimensional vector
	public static Vector sum(Vector a, Vector b) {
		int length = Math.max(a.getSize(), b.getSize());
		double[] s = new double[length];
		for (int i = 0; i < length; i++)
			s[i] = a.getComponent(i) + b.getComponent(i);
		return new Vector(s);
	}
	
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
	
	public static Vector difference(Vector a, Vector b) {
		return(sum(b, a.scale(-1)));
	}
	
	//vector dot product
	public static double dot(Vector a, Vector b) {
		int length = Math.min(a.getSize(), b.getSize());
		double sum = 0;
		for (int i = 0; i < length; i++)
			sum += a.getComponent(i) * b.getComponent(i);
		return sum;
	}
	
	//only for vectors 3-d or lower, returns 3d vector
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
	
	//unit vector from point p to point q
	public static Vector makeUnitVector(Vector p, Vector q) {
		Vector displacement = Vector.difference(p, q);
		return displacement.scale(1 / displacement.getMagnitude());
	}
	
	//distance from point p to point q
	public static double distance(Vector p, Vector q) {
		return difference(p, q).getMagnitude();
	}
	
	public Vector replaceComponent(int index, double n) {
		double[] s = new double[set.length];
		for (int i = 0; i < set.length; i++) {
			if (i == index) s[i] = n;
			else s[i] = set[i];
		}
		return new Vector(s);
	}
	
	//only for 2-d vectors
	public Angle getAngle() {
		if (set.length == 2)
			return new Angle(Math.atan2(set[1], set[0]));
		else
			return null;
	}
	
	public double getMagnitude() {
		double sum = 0;
		for (double comp : set)
			sum += Math.pow(comp, 2);
		return Math.sqrt(sum);
	}
	
	public double getComponent(int index) {
		return set.length > index ? set[index] : 0;
	}
	
	public int getSize() {
		return set.length;
	}
	
	//returns an array of vector components
	public double[] getArray() {
		return set;
	}
	
	//want to format doubles so that I don't get ugly numbers
	public String toString() {
		String str = "(";
		for (int i = 0; i < set.length - 1; i++)
			str += set[i] + ", ";
		str += set[set.length - 1] + ")";
		return str;
	}
	
}
