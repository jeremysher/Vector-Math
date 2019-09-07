package io.github.jeremysher;

public class Test {

	public static void main(String[] args) {
		Vector p1 = new Vector(10, 3);
		Vector p2 = new Vector(0, 0);
		Vector u = Vector.makeUnitVector(p1, p2);
		System.out.println(u);
		
	}

}
