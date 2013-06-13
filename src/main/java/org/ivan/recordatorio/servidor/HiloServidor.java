package org.ivan.recordatorio.servidor;


import org.ivan.recordatorio.util.IO;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.ivan.Dummy;
import org.ivan.RecordatorioBD;
//import util.Utilidades;

/**
 *
 * @author Jordi
 */
public class HiloServidor implements Runnable {

    // socket donde estamos conectados
    private Socket elSocket;
    // stream para leer (recibir)
    private InputStream entrada;
    // stream para escribir (enviar)
    private OutputStream salida;
    //creo un objeto Base de datos
    private RecordatorioBD bd;
    

    //......................................................................
    //......................................................................
    public HiloServidor(Socket con)
            throws java.io.IOException, ClassNotFoundException {

        // guarda el socket (que me envía ServidorMultiHilo (en while true) )
        this.elSocket = con;

        // obtén streams para  entrada (recepción) y salida (envío)
        this.entrada = con.getInputStream();
        this.salida = con.getOutputStream();
        
        ////////////////////////////////////////////7
        
        bd = new RecordatorioBD();
        
        bd.abrirconexion("Agenda.db");
        
        
        
        
    //    System.out.println ("constructor HiloServidor: voy a arrancar el trhead para run()");
        //
        // crea un trhead para ejecute esta clase
        // y arráncalo
        //
        Thread th = new Thread(this);
        th.start(); // ahora hay un nuevo thread en run()
        
       //System.out.println ("final del constructor HiloServidor");

    } // ()

    //......................................................................
    //......................................................................
    public void run() {
        
        //System.out.println ("HiloServidor.run(): empiezo !!!");
        
        try {

            
        //System.out.println ("HiloServidor.run(): espero leer la linea");
            String linea = IO.leeLinea(this.entrada);
            //String compara = "GET";

            //Utilidades.muestraMensajeC(linea);
                System.out.println("Servidor ha recibido: "+linea);
            
            
            String[] arrayleido = linea.split(" ");
             
        
         
         if(arrayleido[0].equalsIgnoreCase("GETC")){
         
             ArrayList Citas= new ArrayList(); 
             
             
                Citas=bd.getCitasDelDia("'"+arrayleido[1]+"'");
                          
                IO.escribeLinea(Citas.toString(), salida);//se supone que ya irá
                                
                
         }else if(arrayleido[0].equalsIgnoreCase("GETA")){
         
             String Motivo=bd.getAsunto("'"+arrayleido[1]+"'", "'"+arrayleido[2]+"'");
            
             IO.escribeLinea(Motivo, salida);//se supone que ya irá
                      
         }else if(arrayleido[0].equalsIgnoreCase("INSER")){
         
             bd.insertar("'"+arrayleido[1]+"'", "'"+arrayleido[2]+"'", "'"+arrayleido[3]+"'");
                      
         }else if(arrayleido[0].equalsIgnoreCase("DEL")){
         
         bd.borrarCita("'"+arrayleido[1]+"'", "'"+arrayleido[2]+"'");
         
         }           
                                
             
                
                // hago echo
                //IO.escribeLinea("retorno: " + linea, salida);
            
                this.elSocket.close();
                                System.out.println("Socket cerrado");

            /*String[] trozos = linea.split("[ ]+");

            if (trozos.length == 2) {

                if (trozos[1] == compara) {
                } else {
                    //Utilidades.muestraMensajeC(" TTP/1.1 400 Bad Request : la solicitud no es GET ");
                    System.out.println(" TTP/1.1 400 Bad Request : la solicitud no es GET ");
                    
                    return;
                }

                FileInputStream fich = new FileInputStream(trozos[1]);

                //Utilidades.muestraMensajeC("HTTP/1.1 200 OK");
                System.out.println("HTTP/1.1 200 OK");
                //Utilidades.muestraMensajeC("/n");
                System.out.println("/n");
                


            } else {
                IO.escribeLinea("HTTP/1.1 400 Bad Request : la solicitud no tiene 2 palabras", this.salida);
                IO.escribeLinea("", this.salida); // después siempre se escribe una linea en blanco, tal y como hemos acordado en el protocolo
                return;
            }


        */} catch (FileNotFoundException err) {

            System.out.println("fichero no encontrado: " + err.getMessage());

        } catch (IOException err) {

            System.out.println("error entrada/salida: " + err.getMessage());

        }



        
    } // ()

  
    
   
} // class

