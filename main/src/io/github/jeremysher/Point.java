package io.github.jeremysher;

//not sure if i'll keep this class either
public class Point extends Vector {

	public Point(double x, double y) {
		super(x, y);
	}
	
	public Point(double x, double y, double z) {
		super(x, y, z);
	}
	
	public Point(double r, Angle theta) {
		super(r, theta);
	}
	
}
