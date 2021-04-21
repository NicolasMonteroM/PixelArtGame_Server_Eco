package main;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Servidor extends PApplet {

//	private TCPLauncher launcher;

	private TCPConnection1 P1conection;
	private TCPConnection2 P2conection;

	// personajes
	private Ladybug P1;
	private Bee P2;

	// imagenes
	PImage screen1, screen2, screen3, backgroundP1, backgroundP2, playOff, playOn, gameOverP1, gameOverP2;

	// fuente tipografía pixel
	PFont pixelFont, pixelFontSmol;

	// pantalla actual
	private int screen = 1;

	float P1distanceText = 100;
	float P2distanceText = 100;

	// # del jugador que ganó
	private int whoWon = 0;

	public static void main(String[] args) {
		PApplet.main("main.Servidor");

	}

	public void settings() {

		size(700, 600);

	}

	public void setup() {

		preload();

		P1conection = new TCPConnection1();
		P2conection = new TCPConnection2();

		// abrimos los puertos y esperamos conexiones
		P1conection.start();
		P2conection.start();

		// mandamos referencia de la clase principal a las conexiones
		P1conection.setMain(this);
		P2conection.setMain(this);

		// launcher = TCPLauncher.getInstance();
		// launcher.setServidor(this);
		// launcher.start();

		// creamos los personajes
		P1 = new Ladybug(this, 525, 300, 243, 243, 0);
		P2 = new Bee(this, 175, 300, 243, 243, 0);
		
		pixelFont = createFont("fonts/pixelplay.ttf", 25);
		pixelFontSmol = createFont("fonts/pixelplay.ttf", 20);

	}

	// cargamos las imagenes
	public void preload() {

		screen1 = loadImage("images/menu.jpg");
		screen2 = loadImage("images/instructions.jpg");
		screen3 = loadImage("images/connecting.jpg");
		backgroundP1 = loadImage("images/fondojuego1.png");
		backgroundP2 = loadImage("images/fondojuego2.png");
		playOff = loadImage("images/playOff.png");
		playOn = loadImage("images/playOn.png");
		gameOverP1 = loadImage("images/gameOverP1.jpg");
		gameOverP2 = loadImage("images/gameOverP2.jpg");
	}

	public void draw() {

		background(49, 65, 114);
		imageMode(CORNER);

		switch (screen) {
		case 1: // Pantalla Menu Principal
			setBgImage(screen1);
			playBtn();
			break;

		case 2: // Pantalla Instrucciones
			setBgImage(screen2);
			playBtn();
			break;

		case 3: // Pantalla Conexion
			setBgImage(screen3);
			playBtn();
			break;

		case 4: // Pantalla de juego

			/*
			 * for (int i = 0; i < launcher.getSessions().size(); i++) { Session session =
			 * launcher.getSessions().get(i); }
			 */

			// variables que ajustan la posición del fondo según el movimiento de los
			// jugadores
			int P1bgProgress = ((-3642 + 600) + P1.getDistancia() * 5);
			int P2bgProgress = ((-3642 + 600) + P2.getDistancia() * 5);

			P1.getDistancia();

			image(backgroundP1, 0, P1bgProgress, 350, 3645);
			image(backgroundP2, 350, P2bgProgress, 350, 3645);

			P1.drawPlayer();
			P2.drawPlayer();
			
			displayCountDown();

			// comprobamos si algún jugador ha alcanzado el límite
			if (P1.getDistancia() >= 550) {
				screen = 5;
				whoWon = 1;
			} else if (P2.getDistancia() >= 550) {
				whoWon = 2;
				screen = 5;
			}

			break;

		case 5:

			// decidimos la imagen que aparece en la última pantalla según quien gane
			if (whoWon == 1) {
				setBgImage(gameOverP1);
			} else if (whoWon == 2) {
				setBgImage(gameOverP2);
			}

			playBtn();
			break;
		}

	}
	
	public void displayCountDown() {

		int P1distanceDisplay = (int) P1distanceText;
		int P2distanceDisplay = (int) P2distanceText;
				
		textAlign(CENTER);
		textFont(pixelFontSmol);
		text("Distance to goal", 175, 130);
		text("Distance to goal", 525, 130);
		textFont(pixelFont);
		text(P1distanceDisplay, 175, 160);
		text(P2distanceDisplay, 525, 160);
		
	}

	/*
	 * componente "play"
	 */

	public void playBtn() {
		imageMode(CENTER);
		if (dist(mouseX, mouseY, 350, 520) < 40) {
			image(playOn, 350, 500, 66, 40);

		} else {
			image(playOff, 350, 500, 66, 40);
		}
		imageMode(CORNER);
	}

	/*
	 * recepción de los mensajes notificados por las clases de las conexiones
	 */

	public void notify(Message e, Object obj) {
		System.out.println(e.getMessage());

		// verificamos qué objeto nos está notificando el mensaje recibido

		if (obj instanceof TCPConnection1) {

			switch (e.getMessage()) {

			case "MOVEE":

				// movemos el jugador #1 al recibir el mensaje "MOVEE" por parte de la primera
				// conexión
				P1.movePlayer();
				// disminuye la distancia restante en pantalla
				if(P1distanceText > 0) {
					P1distanceText -= 1.82;
				}
				System.out.println(P1.getDistancia());

				break;
			default:
				break;
			}
		}

		if (obj instanceof TCPConnection2) {

			switch (e.getMessage()) {

			case "MOVEE":

				// movemos el jugador #2 al recibir el mensaje "MOVEE" por parte de la segunda
				// conexión
				P2.movePlayer();
				// disminuye la distancia restante en pantalla
				if(P2distanceText > 0) {
					P2distanceText -= 1.82;
				}
				//countDownDistance(P2distanceText);
				System.out.println(P2distanceText);
				System.out.println(P2.getDistancia());

				break;
			default:
				break;
			}

		}
	}

	/*
	 * método que recibe una variable tipo PImage para poner como fondo
	 */

	public void setBgImage(PImage p) {
		image(p, 0, 0, 700, 600);

	}

	/*
	 * método que reinicia las variables del juego parwa volver a empezar
	 */

	public void restartGame() {

		whoWon = 0;

		// asignamos valor 0 a la variable de distancia recorrida de los personajes
		P1.setDistancia(0);
		P2.setDistancia(0);
		
		// asignamos nuevamente 100 a la variable que muestra la distancia a la meta en la pantalla
		P1distanceText = 100;
		P2distanceText = 100;

		// Pantalla de juego
		screen = 4;

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

		case 5: // Volver a jugar
			if (dist(mouseX, mouseY, 350, 500) < 40) {
				System.out.println("click");
				restartGame();
			}
			break;
		}
	}

}
