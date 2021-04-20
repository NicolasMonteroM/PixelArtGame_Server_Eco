package main;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Bee extends Player{
	
	private PImage bee;
	public Bee(PApplet app, int posX, int posY, int width, int height, int vidas) {
		super (app, posX, posY, width, height, vidas);
		
	}
	
	public void drawPlayer() {
		this.app.imageMode(PConstants.CENTER);
		this.bee = this.app.loadImage("./images/bee.png");
		app.image(bee, posX, posY, width, height);
		this.app.imageMode(PConstants.CORNER);
	}

	public void movePlayer() {

	}

}
