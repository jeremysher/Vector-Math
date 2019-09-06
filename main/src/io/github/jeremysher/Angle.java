package io.github.jeremysher;

public class Angle {
	
	double theta;

	//angle in radians
	public Angle(double theta) {
		this.theta = restrictRange(theta);
	}
	
	public static double restrictRange(double theta) {
		return (theta % (2 * Math.PI) + 2 * Math.PI) % (2 * Math.PI);
	}
	
	public double sin() {
		return Math.sin(theta);
	}
	
	public double cos() {
		return Math.cos(theta);
	}
	
	public double tan() {
		return Math.tan(theta);
	}
	
	public double getRadians() {
		return theta;
	}
	
	public double getDegrees() {
		return theta * 180 / Math.PI;
	}
	
	//format to truncate decimal later
	public String toString() {
		return theta + "";
	}
	
}
