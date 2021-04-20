package main;
import processing.core.PApplet;
import processing.core.PConstants;

public abstract class Player {

	protected int posX;
	protected int posY;
	protected PApplet app;
	protected int distance;
	protected int vidas;
	protected int width, height;

	public Player(PApplet app, int posX, int posY, int width, int height, int vidas) {

		this.posX = posX;
		this.posY = posY;
		this.setApp(app);
		this.width = width;
		this.height = height;
		this.vidas = vidas;
		this.distance = 200;
		
	
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

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
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

	public void drawPlayer() {
		this.getApp().ellipse(posX, posY, 60, 60);

	}

	public void movePlayer() {
		this.distance--;
		
	}
	
	public void vidasPlayer() {
		
		
	}

	public PApplet getApp() {
		return app;
	}

	public void setApp(PApplet app) {
		this.app = app;
	}

}
