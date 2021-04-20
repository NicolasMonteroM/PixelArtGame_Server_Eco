package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

public class Session extends Thread{

	private String id;
	private BufferedWriter writer;
	private Socket socket;
	private Servidor observer;

	public Session ( Socket socket) {
		this.socket = socket;
		this.id = UUID.randomUUID().toString();
	}
	@Override
	public void run () {
		try {
			//declarations
			InputStream is = socket.getInputStream();
			OutputStream out = socket.getOutputStream();

			writer = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader breader = new BufferedReader( new InputStreamReader(is));

			//Recepcion
			while (true) {
				System.out.println("Esperando el Mensaje...:)");
				String mensaje = breader.readLine();
				observer.cuandoLlegueElMensaje(this, mensaje);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}


	public void enviar (String msg) {
		new Thread(
				()->{
					try {
						writer.write (msg + "\n");
						writer.flush();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				).start();
	}

	// Método de suscripción
		public void setObserver(Servidor observer) {
			this.observer = observer;
		}
		
		public String getID(){
			return this.id;
		}
}
