package com.github.racc.tscg.test;


public class NestedPojo {
	private int nestInt;
	private double nestDouble;
	private String nestString;

	public NestedPojo() {}
	
	public NestedPojo(
		int testInt,	
		double testDouble,
		String testString
	) {
		this.setNestInt(testInt);
		this.setNestDouble(testDouble);
		this.setNestString(testString);
	}

	public int getNestInt() {
		return nestInt;
	}

	public void setNestInt(int nestInt) {
		this.nestInt = nestInt;
	}

	public double getNestDouble() {
		return nestDouble;
	}

	public void setNestDouble(double nestDouble) {
		this.nestDouble = nestDouble;
	}

	public String getNestString() {
		return nestString;
	}

	public void setNestString(String nestString) {
		this.nestString = nestString;
	}
}
