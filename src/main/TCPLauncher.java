package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

public class  TCPLauncher extends Thread {

	private static TCPLauncher instance;
	
	private TCPLauncher() {}

	public static TCPLauncher getInstance () {
		if (instance == null) { 
			instance = new TCPLauncher();
		}
		return instance;
	}

	
	private Servidor observer;
	private ServerSocket server;
	private ArrayList<Session> sessions;
	


	// Método de suscripción
	public void setServidor (Servidor observer) {
		this.observer = observer;
	}

	//Debe tener un run porque es un hilo
	@Override
	public void run() {
		try {

			//Conectamos
			sessions = new ArrayList<Session>();
			server = new ServerSocket(8000);

			while(true) {
				System.out.println("Esperando conección en 8000... :)");
				Socket socket = server.accept();
				Session session = new Session(socket);
				session.setObserver(observer);
				session.start();
				
				sessions.add(session);
				
				System.out.println("El cliente se ha conectado");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
