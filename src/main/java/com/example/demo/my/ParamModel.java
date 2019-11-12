package com.example.demo.my;

public class ParamModel {
	private int intVal;
	private double doubleVal;
	public int getIntVal() {
		return intVal;
	}
	public void setIntVal(int intVal) {
		this.intVal = intVal;
	}
	public double getDoubleVal() {
		return doubleVal;
	}
	public void setDoubleVal(double doubleVal) {
		this.doubleVal = doubleVal;
	}
	@Override
	public String toString() {
		return "ParamModel [intVal=" + intVal + ", doubleVal=" + doubleVal + "]";
	}
	
}
