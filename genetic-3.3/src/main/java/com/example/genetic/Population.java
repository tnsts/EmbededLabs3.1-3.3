package com.example.genetic;

public class Population {
	int x1, x2, x3, x4;

	public Population(int x1, int x2, int x3, int x4) {
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
	}

	@Override
	public String toString() {
		return "x1 = " + x1 + "\nx2 = " + x2 + "\nx3 = " + x3 + "\nx4 = " + x4;
	}
	
	
}
