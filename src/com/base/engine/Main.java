package com.base.engine;

public class Main {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "Java Game Engine";
	public static final double FRAME_CAP = 10000.0;
	public static final double VSYNC_FRAME_CAP = 120.0; // TODO: Make this actually VSync.
	
	private boolean isVsync;
	private boolean isRunning;
	private Game game;
	
	public Main() {
		isRunning = false;
		game = new Game();
	}
	
	public void initialize() {
		
		if (isRunning) 
			return;
		
		update();
	}
	
	public void shutdown() {
		if (!isRunning) 
			return;
		
		isRunning = false;
	}
	
	private void update() {
		isRunning = true;
		
		int frames = 0;
		long frameCounter = 0;
		
		final double frameTime;
		
		if (isVsync)
			frameTime = 1.0 / VSYNC_FRAME_CAP;
		else
			frameTime = 1.0 / FRAME_CAP;
		
		long lastTime = Time.getTime();
		double unprocessedTime = 0;
		
		while (isRunning) {
			boolean render = false;
			
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime; 
			lastTime = startTime;
			
			unprocessedTime += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;
			
			while (unprocessedTime > frameTime) {
				render = true;
				
				unprocessedTime -= frameTime;
			
				if (Window.isCloseRequested())
					shutdown();
				
				Time.setDeltaTime(frameTime);
				
				game.input();
				game.update();
				
				if (frameCounter >= Time.SECOND) {
					System.out.println("FPS: " + frames);
					frames = 0;
					frameCounter = 0;
				}
			}			
			
			if (render)
			{
				render();
				frames++;
			}
			else
			{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		cleanup();
	}
	
	private void render() {
		game.render();
		Window.render();
	}
	
	private void cleanup() {
		Window.destroy();
	}
	
	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, TITLE);
		
		Main game = new Main();
		
		game.initialize();
	}
}
