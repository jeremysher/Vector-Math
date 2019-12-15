package io.github.jeremysher.vector;

public class Test {

	public static void main(String[] args) {
		
		Vector p1 = new Vector(-1, 0);
		Vector p2 = new Vector(1, 0);

		VectorField f = new VectorField() {
			public Vector function(Vector point) {
				Vector u1 = Vector.makeUnitVector(point, p1);
				Vector u2 = Vector.makeUnitVector(point, p2);
				double mag1 = -1 / Math.pow(Vector.distance(point, p1), 2);
				double mag2 = 1 / Math.pow(Vector.distance(point, p2), 2);
				Vector v1 = u1.scale(mag1);
				Vector v2 = u2.scale(mag2);
				return Vector.sum(v1, v2);
			}
		};
		//orbit();
		System.out.println(f.div(new Vector(-0.5, 0.5)));
		
	}
	
	public static void orbit() {
		Vector center = new Vector(0, 0);
		Vector p = new Vector(4, 0);
		Vector v = new Vector(0, 0.3);
		VectorField a = new VectorField() {
			public Vector function(Vector point) {
				Vector u = Vector.makeUnitVector(point, center);
				double mag = 1 / Math.pow(Vector.distance(point, center), 2);
				return u.scale(mag);
			}
		};
		double dt = 0.05;
		double tf = 30;
		
		for (double t = 0; t <= tf; t += dt) {
			System.out.println(p);
			p = Vector.sum(p, v.scale(dt));
			v = Vector.sum(v, a.function(p).scale(dt));
		}
		
	}

}
