package io.github.jeremysher;

public class Vector {
	private double[] set;

	public Vector(double x, double y) {
		set = new double[] {x, y};
	}
	
	public Vector(double x, double y, double z) {
		set = new double[] {x, y, z};
	}
	
	//make Angle class to implement this constructor
	/*public Vector(double m, double theta) {
		set = new double[] {m * Math.cos(theta), m * Math.sin(theta)};
	}*/
	
	public Vector(double[] set) {
		this.set = set;
	}
	
	public static Vector add(Vector a, Vector b) {
		int length = Math.max(a.getSize(), b.getSize());
		double[] s = new double[length];
		for (int i = 0; i < length; i++) {
			s[i] = (a.getSize() > i ? a.getComponent(i) : 0)
					+ (b.getSize() > i ? b.getComponent(i) : 0);
		}
		return new Vector(s);
	}
	
	public static Vector dot(Vector a, Vector b) {
		int length = Math.min(a.getSize(), b.getSize());
		double[] s = new double[length];
		for (int i = 0; i < length; i++) {
			s[i] = a.getComponent(i) * b.getComponent(i);
		}
		return new Vector(s);
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
