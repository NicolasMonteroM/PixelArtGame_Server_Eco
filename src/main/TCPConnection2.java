package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

public class TCPConnection2 extends Thread {

	private Servidor ref;

	@Override
	public void run() {
		try {
			
			//creamos un server socket en el puerto 8000
			ServerSocket server = new ServerSocket(8000);
			System.out.println("Waiting connection...");

			// aceptamos conexión
			Socket socket = server.accept();
			System.out.println("Connected on 8000");

			
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader breader = new BufferedReader(isr);

			while (true) {

				// guardamos el mensaje recibido
				String dataReceived = breader.readLine();

				// serialización gson
				Gson gson = new Gson();
				Message c = gson.fromJson(dataReceived, Message.class);

				// notifica a la clase principal el mensaje
				ref.notify(c, this);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setMain(Servidor main) {
		this.ref = main;
	}

}
