package io.github.jeremysher;

public class Vector {
	private double[] set;

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
	
	//vector addition; resultant vector has length of greatest dimensional vector
	public static Vector add(Vector a, Vector b) {
		int length = Math.max(a.getSize(), b.getSize());
		double[] s = new double[length];
		for (int i = 0; i < length; i++) {
			s[i] = (a.getSize() > i ? a.getComponent(i) : 0)
					+ (b.getSize() > i ? b.getComponent(i) : 0);
		}
		return new Vector(s);
	}
	
	//vector dot product
	public static double dot(Vector a, Vector b) {
		int length = Math.min(a.getSize(), b.getSize());
		double sum = 0;
		for (int i = 0; i < length; i++)
			sum = a.getComponent(i) * b.getComponent(i);
		return sum;
	}
	
	public Angle getAngle() {
		if (set.length == 2)
			return new Angle(Math.atan2(set[1], set[0]));
		else
			return null;
	}
	
	public double getComponent(int index) {
		return set[index];
	}
	
	public int getSize() {
		return set.length;
	}
	
	//want to format doubles so that I don't get ugly numbers
	public String toString() {
		String str = "[";
		for (int i = 0; i < set.length - 1; i++)
			str += set[i] + ", ";
		str += set[set.length - 1] + "]";
		return str;
	}
	
}
