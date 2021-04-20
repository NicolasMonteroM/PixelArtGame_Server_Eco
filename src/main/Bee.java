package main;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Bee{
	
	private PImage bee;
	private int posX;
	private int posY;
	private PApplet app;
	private int distancia;
	private int vidas;
	private int width, height, speed;
	
	public Bee(PApplet app, int posX, int posY, int width, int height, int vidas, int distancia) {

		this.posX = posX;
		this.posY = posY;
		this.app = app;
		this.width = width;
		this.height = height;
		this.vidas = vidas;
		this.distancia = distancia;
		this.speed = 5;

	}
	
	public void movePlayer() {
		this.distancia += speed;

	}
	
	public void drawPlayer() {
		this.app.imageMode(PConstants.CENTER);
		this.bee = this.app.loadImage("./images/bee.png");
		app.image(bee, posX, posY, width, height);
		this.app.imageMode(PConstants.CORNER);
	}


	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	


}
