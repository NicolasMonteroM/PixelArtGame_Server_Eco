package main;

import com.google.gson.Gson;

import processing.core.PApplet;
import processing.core.PImage;

public class Servidor extends PApplet {

//	private TCPLauncher launcher;

	private TCPConnection1 P1conection;
	private TCPConnection2 P2conection;

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

		P1conection = new TCPConnection1();
		P1conection.setMain(this);
		P1conection.start();

		P2conection = new TCPConnection2();
		P2conection.setMain(this);
		P2conection.start();

		preload();

		// launcher = TCPLauncher.getInstance();
		// launcher.setServidor(this);
		// launcher.start();
		screen = 1;

		P1 = new Ladybug(this, 550, 300, 243, 243, 3, 0);
		P2 = new Bee(this, 150, 300, 243, 243, 3, 0);

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

			/*
			 * for (int i = 0; i < launcher.getSessions().size(); i++) { Session session =
			 * launcher.getSessions().get(i); }
			 */

			if (P1.getDistancia() >= 550) {
				screen = 5;
				gameOver(P1);
			}

			if (P2.getDistancia() >= 550) {
				screen = 5;
				gameOver(P2);
			}


			int bgStart = -3335 + 300;
			int bgPosYP1 = (bgStart + P1.getDistancia() * 5);
			int bgPosYP2 = (bgStart + P1.getDistancia() * 5);

			image(backgroundP1, 0, bgPosYP1, 350, 3645);
			image(backgroundP2, 350, bgPosYP2, 350, 3645);

			P1.drawPlayer();
			P2.drawPlayer();

			break;

		case 5:

			break;
		}

	}

	public void notify(Message e, Object obj) {
		System.out.println(e.getMessage());

		if (obj instanceof TCPConnection1) {

			switch (e.getMessage()) {

			case "MOVEE":

				P1.movePlayer();
				System.out.println("MOVEEEEE");
				System.out.println(P1.getDistancia());

				break;
			}
		}

		if (obj instanceof TCPConnection2) {

			switch (e.getMessage()) {

			case "MOVEE":

				P2.movePlayer();
				System.out.println("MOVEEEEE");
				System.out.println(P2.getDistancia());

				break;
			}

		}
	}

	public void gameOver(Object obj) {
		
		

	}

	public void restartGame() {
		P1.setDistancia(0);
		P2.setDistancia(0);

	}
	/*
	 * public void cuandoLlegueElMensaje(Session s, String mensaje) {
	 * System.out.println("Mensaje recibido por parte del " + s.getID() + ":" +
	 * mensaje); Message msg = new Message(mensaje); notify(msg, s);
	 * 
	 * Gson gson = new Gson(); Message message = gson.fromJson(mensaje,
	 * Message.class);
	 * 
	 * }
	 */

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
