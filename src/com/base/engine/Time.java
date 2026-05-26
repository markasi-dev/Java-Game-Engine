package com.base.engine;

public class Time {
	public static final long SECOND = 1000000000L;
	
	private static double deltaTime;
	
	public static long getTime() {
		return System.nanoTime();
	}
	
	public static double getDeltaTime() {
		return deltaTime;
	}
	
	public static void setDeltaTime(double deltaTime) {
		Time.deltaTime = deltaTime;
	}
}