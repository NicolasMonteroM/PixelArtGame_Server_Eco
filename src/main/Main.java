package main;

import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet {

	private TCPConnection1 P1conection;
	private TCPConnection2 P2conection;

	private Player P1;
	private Player P2;
	
	private PImage screen1,screen2,screen3,backgroundP1,backgroundP2;
	private int screen = 1;
	private int posP1 = -290, posP2 = -290;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());

	}

	public void settings() {
		
		//cambio c:

		size(700, 600);

	}

	public void setup() {

		P1conection = new TCPConnection1();
		P1conection.setMain(this);

		P2conection = new TCPConnection2();
		P2conection.setMain(this);

		screen1 = loadImage("../images/menu.png");
		screen2 = loadImage("../images/instructions.png");
		screen3 = loadImage("../images/connecting.png");
		backgroundP1 = loadImage("../images/fondojuego.png");
		backgroundP2 = loadImage("../images/fondojuego.png");
	}

	public void draw() {
		background(50);
		imageMode(CENTER);

		switch(screen) {
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
	}
	
	public void drawGame() {
		
		P1.drawPlayer();
		P2.drawPlayer();
	}

	public void startConnection() {

		P1conection.start();
		P2conection.start();

	}

	public void startGame() {

		P1 = new Ladybug(this, 200, 200);
		P2 = new Bee(this, 200, 200);

	}

	public void gameOver() {

	}

	public void restartGame() {

	}

	public void mousePressed() {

	}

}
