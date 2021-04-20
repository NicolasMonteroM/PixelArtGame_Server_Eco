package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Servidor extends PApplet {

	private TCPLauncher launcher;
	private Ladybug P1;
	private Bee P2;
	PImage screen1, screen2, screen3, backgroundP1, backgroundP2;

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
		//startGame();
		

	}

	public void preload() {
		screen1 = loadImage("images/menu.png");
		screen2 = loadImage("images/instructions.png");
		screen3 = loadImage("images/connecting.png");
		backgroundP1 = loadImage("images/fondojuego.png");
		backgroundP2 = loadImage("images/fondojuego.png");
	}

	public void draw() {
		background(49, 65, 114);
		
		//P1.drawPlayer();
		//P2.drawPlayer();
		imageMode(CENTER);

		switch (screen) {
		case 1: // Pantalla Menu Principal
			image(screen1, 350, 300);
			break;

		case 2: // Pantalla Instrucciones
			image(screen2, 350, 300);
			break;

		case 3: // Pantalla Conexion
			image(screen3, 350, 300);
			break;

		case 4: // Pantalla de juego
			image(backgroundP1, 175, posP1);
			image(backgroundP2, 525, posP2);

			break;
		}

	}
/*
	public void notify(Message e, Object obj) {

		if (obj instanceof TCPConnection1) {

			System.out.println("Player 1: " + e.getMessage());

			switch (e.getMessage()) {

			case "FLYYYY":

				break;

			}
		}

		if (obj instanceof TCPConnection2) {

			System.out.println("Player 2: " + e.getMessage());

			switch (e.getMessage()) {

			case "FLYYYY":
				break;

			}
		}
	}*/

	public void startGame() {
		
		P1 = new Ladybug(this, 650, 200,400,400,3);
		P2 = new Bee(this, 150, 200,400,400,3);

	}

	public void gameOver() {

	}

	public void restartGame() {

	}
	
	public void cuandoLlegueElMensaje (Session s, String mensaje) {
		System.out.println("Mensaje recibido por parte del " + s.getID() + ":" + mensaje);
	}

	public void notify(Message e, Object obj) {
		System.out.println(e.getMessage());
	}

	public void mousePressed() {
		System.out.println(mouseX + "::" + mouseY);

		switch (screen) {
		case 1: // Pantalla Menu Principal
			if (mouseX > 310 && mouseX < 383 && mouseY > 467 && mouseY < 498) {
				System.out.println("click");
				screen = 2;
			}
			break;

		case 2: // Pantalla Instrucciones
			if (mouseX > 310 && mouseX < 383 && mouseY > 467 && mouseY < 498) {
				System.out.println("click");
				screen = 3;
			}
			break;

		case 3: // Pantalla Conexion
			if (mouseX > 268 && mouseX < 431 && mouseY > 457 && mouseY < 476) {
				System.out.println("click");
				screen = 4;
			}
			break;

		case 4: // Pantalla de juego

			break;
		}
	}

}
