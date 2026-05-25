package com.base.engine;

public class Main {
	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	public static final String TITLE = "Java Game Engine";
	
	public Main() {
		
	}
	
	public void initialize() {
		update();
	}
	
	public void shutdown() {
		// TODO: Shutdown stuff here.
	}
	
	public void update() {
		while (!Window.isCloseRequested()) {
			render();
		}
	}
	
	public void render() {
		Window.render();
	}
	
	public void cleanup() {
		// TODO: Do cleanup.
	}
	
	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		
		Main game = new Main();
		
		game.initialize();
	}
}
