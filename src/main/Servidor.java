package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Servidor extends PApplet {

	private TCPLauncher launcher;
	private Ladybug P1;
	private Bee P2;
	PImage screen1, screen2, screen3, backgroundP1, backgroundP2, playOff, playOn;

	private int screen = 1;
	private int posP1 = -290, posP2 = -290;

	public static void main(String[] args) {
		PApplet.main("main.Servidor");

	}

	public void settings() {

		size(700, 600);

	}

	public void setup() {

		preload();

		launcher = TCPLauncher.getInstance();
		launcher.setServidor(this);
		launcher.start();
		screen = 1;

		P1 = new Ladybug(this, 550, 300, 243, 243, 3);
		P2 = new Bee(this, 150, 300, 243, 243, 3);

	}

	public void preload() {
		screen1 = loadImage("images/menu.jpg");
		screen2 = loadImage("images/instructions.jpg");
		screen3 = loadImage("images/connecting.jpg");
		backgroundP1 = loadImage("images/fondojuego1.png");
		backgroundP2 = loadImage("images/fondojuego2.png");
		playOff = loadImage("images/playOff.png");
		playOn = loadImage("images/playOn.png");
	}

	public void draw() {
		background(49, 65, 114);

		imageMode(CORNER);

		switch (screen) {
		case 1: // Pantalla Menu Principal
			image(screen1, 0, 0, 700, 600);
			imageMode(CENTER);
			if (dist(mouseX, mouseY, 350, 520) < 40) {
				image(playOn, 350, 500, 66, 40);

			} else {
				image(playOff, 350, 500, 66, 40);
			}
			imageMode(CORNER);
			break;

		case 2: // Pantalla Instrucciones
			image(screen2, 0, 0, 700, 600);
			imageMode(CENTER);
			if (dist(mouseX, mouseY, 350, 520) < 40) {
				image(playOn, 350, 500, 66, 40);

			} else {
				image(playOff, 350, 500, 66, 40);
			}
			imageMode(CORNER);
			break;

		case 3: // Pantalla Conexion
			image(screen3, 0, 0, 700, 600);
			break;

		case 4: // Pantalla de juego
			image(backgroundP1, 0, (-3645 + 600) - posP1, 350, 3645);
			image(backgroundP2, 350, (-3645 + 600) - posP2, 350, 3645);

			P1.drawPlayer();
			P2.drawPlayer();

			break;
		}

	}

	public void notify(Message e, Object obj) {
		System.out.println(e.getMessage());
	}

	/*
	 * public void notify(Message e, Object obj) {
	 * 
	 * if (obj instanceof TCPConnection1) {
	 * 
	 * System.out.println("Player 1: " + e.getMessage());
	 * 
	 * switch (e.getMessage()) {
	 * 
	 * case "FLYYYY":
	 * 
	 * break;
	 * 
	 * } }
	 * 
	 * if (obj instanceof TCPConnection2) {
	 * 
	 * System.out.println("Player 2: " + e.getMessage());
	 * 
	 * switch (e.getMessage()) {
	 * 
	 * case "FLYYYY": break;
	 * 
	 * } } }
	 */

	public void gameOver() {

	}

	public void restartGame() {

	}

	public void cuandoLlegueElMensaje(Session s, String mensaje) {
		System.out.println("Mensaje recibido por parte del " + s.getID() + ":" + mensaje);
	}

	public void mousePressed() {
		System.out.println(mouseX + "::" + mouseY);

		switch (screen) {
		case 1: // Pantalla Menu Principal
			if (dist(mouseX, mouseY, 350, 500) < 40) {
				System.out.println("click");
				screen = 2;
			}
			break;

		case 2: // Pantalla Instrucciones
			if (dist(mouseX, mouseY, 350, 500) < 40) {
				System.out.println("click");
				screen = 3;
			}
			break;

		case 3: // Pantalla Conexion
			if (dist(mouseX, mouseY, 350, 500) < 40) {
				System.out.println("click");
				screen = 4;
			}
			break;

		case 4: // Pantalla de juego

			break;
		}
	}

}
