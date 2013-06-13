/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivan;



import java.io.*;
import java.net.*;

/**
 *
 * @author Jordi
 */
public class ClientePrueba {

	public static void main(String[] args)
		throws java.io.IOException {

		String hostServidor = "localhost"; // nombre o dirección IP del host donde esta el servidor
					// localhost => el servidor está en el mismo ordenador que este cliente
					// cambiarlo si no es así

		String mensaje = "hola que tal";

		int PUERTO = org.ivan.recordatorio.servidor.ServidorMultiHilo.PUERTO;

		// saca el el nombre del servidor, el puerto y el mensaje de la linea de comandos
		if (args.length == 3) {
			hostServidor = args[0];
			PUERTO = Integer.parseInt(args[1]);
			mensaje = args[2];
		}

		System.out.println ("me voy a conectar al servidor: " + hostServidor + " " + PUERTO);

		//
		// creo un socket (su número local no sé cuál es)
		// el constructor recibe el la dirección y puerto
		// del servidor al que nos queremos conectar
		//
		Socket sock = new Socket(hostServidor, PUERTO);

		//
		// obtengo streams para leer y escribir a través del socket
		// (podré leer y escribir líneas)
		//
		BufferedReader entrada = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		PrintWriter salida = new PrintWriter(sock.getOutputStream());

		//
		// envio el mensaje (escribo)
		//
                org.ivan.recordatorio.util.IO.escribeLinea(mensaje, sock.getOutputStream());
		salida.flush();

		System.out.println ("envio al servidor: " + mensaje);

		//
		// leo (recibo) la respuesta y la escribo
		//
		String linea = entrada.readLine();

		System.out.println("el servidor me ha respondido  >" + linea + "<");

		//
		// cierro los streams y el socket
		//
		entrada.close();
		salida.close();
		sock.close();
	} // ()

} // class
