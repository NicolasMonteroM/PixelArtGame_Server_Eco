package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Ladybug extends Player {
	private PImage ladyBug;
	public Ladybug(PApplet app, int posX, int posY, int width, int height, int vidas) {
		super (app, posX, posY, width, height, vidas);


	}

	public void drawPlayer() {
		this.ladyBug = this.app.loadImage("./images/ladybug.png");
		app.image(ladyBug, posX, posY, width, height);

	}

	public void movePlayer() {

	}
	public void vidasPlayer () {
		//Le decimos que si la posición en y es = a la posicion final ( Ganó ) se le resta vida al otro jugador
	}
	
}
