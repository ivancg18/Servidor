package org.ivan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.ivan.recordatorio.util.IO;
import org.ivan.recordatorio.servidor.RecordatorioInterfaz;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() throws IOException
    {
        System.out.println("AppTest.suite(): preparando el test");
        
        System.out.println("AppTest.suite(): voy a arrancar el thread para ejecutar le servidro");

        Thread th = new Thread (new Runnable() {

            public void run() {
                try {
                    org.ivan.recordatorio.servidor.ServidorMultiHilo.main(null);
                } catch (IOException ex) { } catch (ClassNotFoundException ex) {
                        System.out.println("Ha cascado "+ex);
                }
           }
        });
        
        th.start();
        System.out.println ("acabo de arrancar otro thread para ejecutar el servidor");
         
 
           
        
        
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws UnknownHostException, IOException, InterruptedException
    {
        String hostServidor = "localhost"; // nombre o dirección IP del host donde esta el servidor
					// localhost => el servidor está en el mismo ordenador que este cliente
					// cambiarlo si no es así

		String mensaje = "DEL 2013-04-17 11:00";

		int PUERTO = org.ivan.recordatorio.servidor.ServidorMultiHilo.PUERTO;

		
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
		InputStream entrada = sock.getInputStream();
		PrintWriter salida = new PrintWriter(sock.getOutputStream());

		//
		// envio el mensaje (escribo)
		//
                IO.escribeLinea(mensaje, sock.getOutputStream());
		salida.flush();

		System.out.println ("envio al servidor: " + mensaje);

		//
		// leo (recibo) la respuesta y la escribo
                //
                
                String leido = IO.leeLinea(entrada);
                
                 String[] arrayleido= leido.split(",");
                
                 System.out.println("Guardo la linea ");
                 
               for(int i=0; i<arrayleido.length ;i++){
               
                 System.out.println("el servidor me ha respondido  >" + arrayleido[i] + "<");  
                   
               }
                                
		//System.out.println("el servidor me ha respondido  >" + IO.leeLinea(entrada) + "<");
                
               

		//
		// cierro los streams y el socket
		//
		entrada.close();
		salida.close();
		sock.close();
        
    }
    
    


}
/*
 
 //notas in extremis
 * 
 * 
 * interfaz RecordatorioInterfaz{
 * 
 * void instertar(String momento, Sring Asunto);
 * 
 * ArrayList<String> get CitasDelDia(String cita);
 * 
 * String getAsunto(String momento);
 * 
 * void borrarCita(String momento);
 * 
 * 
 * }
 
 
 
 */