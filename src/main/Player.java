package main;
import processing.core.PApplet;
import processing.core.PConstants;

public abstract class Player {

	private int posX;
	private int posY;
	private PApplet app;

	public Player(PApplet app, int posX, int posY) {

		this.posX = posX;
		this.posY = posY;
		this.app = app;

	}

	public void drawPlayer() {

	}

	public void movePlayer() {

	}

}
