package io.github.jeremysher;

public class Test {

	public static void main(String[] args) {

		Vector center = new Vector(0, 0);
		Vector p = new Vector(4, 0);
		Vector v = new Vector(0, 0.8);
		VectorField a = new VectorField() {
			public Vector function(Vector point) {
				Vector u = Vector.makeUnitVector(point, center);
				double mag = 1 / Math.pow(Vector.distance(point, center), 2);
				return u.scale(mag);
			}
		};
		double dt = 0.2;
		double tf = 200;
		
		for (double t = 0; t <= tf; t += dt) {
			System.out.println(p);
			p = Vector.sum(p, v.scale(dt));
			v = Vector.sum(v, a.function(p).scale(dt));
		}
		
	}

}
