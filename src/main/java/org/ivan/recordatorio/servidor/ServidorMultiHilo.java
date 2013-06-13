
package org.ivan.recordatorio.servidor;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import org.ivan.Dummy;


/**
 *
 * @author Jordi
 */
public class ServidorMultiHilo {

	public static final int PUERTO = 1235;


	public static void main(String[] aArgs)
		throws java.io.IOException, ClassNotFoundException
	{

		ServerSocket socketServidor;
		Socket	socketconectado;
                
              

		
		System.out.println("*****************************************************");
		System.out.println("**      servidor ficheros                         **");
		System.out.println("*****************************************************");
		System.out.println("Para contactar conmigo la solicitud es ");
		System.out.println ("\n GET  <nombreFichero>");
		System.out.println("*****************************************************");
		System.out.println("yo estoy en: ");
		System.out.println ("\n       "+InetAddress.getLocalHost().getHostAddress() +" " + PUERTO);
		System.out.println ("       "+InetAddress.getLocalHost().getHostName() +" " + PUERTO);
		System.out.println("*****************************************************");


                
               // System.out.println ("servidor: voy a crear un socketServidor");
		//
		// creo un socket de servidor
		//
		socketServidor = new ServerSocket (PUERTO);

                //System.out.println ("servidor: voy a crear un socketServidor: ya lo he hecho, ahora entrare en el while true");
		
                
		//
                
		//
		//
		// por siempre
		//
		while (true) {

			// accepto conexions: aquí espero
                    
                        System.out.println (" voy a esperar en el accept()");
			socketconectado = socketServidor.accept ();

			//
			// cuando sigo: alguien se ha conectado
			//
			System.out.println("conexión establecida con un cliente");

			//
			// creo un objeto que realizará el trabajo
			// con un thread propio
			//
			new HiloServidor(socketconectado);
			// una vez hecho, me vuelvo a esperar

		} // while true

	} // ()

} // class

