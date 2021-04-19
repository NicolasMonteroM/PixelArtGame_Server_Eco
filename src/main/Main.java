package main;

import processing.core.PApplet;

public class Main extends PApplet {

	private TCPConnection1 P1conection;
	private TCPConnection2 P2conection;

	private Player P1;
	private Player P2;

	public static void main(String[] args) {
		PApplet.main(Main.class.getName());

	}

	public void settings() {
		
		//cambio c:

		size(1200, 700);

	}

	public void setup() {

		P1conection = new TCPConnection1();
		P1conection.setMain(this);

		P2conection = new TCPConnection2();
		P2conection.setMain(this);

	}

	public void draw() {

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
