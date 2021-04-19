package main;
import processing.core.PApplet;
import processing.core.PConstants;

public abstract class Player {

	private int posX;
	private int posY;
	private PApplet app;
	private int distance;

	public Player(PApplet app, int posX, int posY) {

		this.posX = posX;
		this.posY = posY;
		this.app = app;
		this.distance = 200;

	}

	public void drawPlayer() {
		this.app.ellipse(posX, posY, 60, 60);

	}

	public void movePlayer() {
		this.distance--;
		
	}

}
