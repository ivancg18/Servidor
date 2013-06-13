/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ivan;

import java.io.IOException;
import java.util.ArrayList;
import org.ivan.recordatorio.servidor.HiloServidor;
import org.ivan.recordatorio.servidor.RecordatorioInterfaz;
import org.ivan.recordatorio.util.IO;

/**
 *
 * @author ivan
 */
public class Dummy implements RecordatorioInterfaz {

    public void insertar(String momento, String Asunto) {
        
        
        System.out.println("Inserto Cita");
    }

    public ArrayList<String> getCitasDelDia(String cita) {
        
        System.out.println("Get Citas del Día");
        return null;
        
    }

    public String getAsunto(String momento) {
        System.out.println("Get Asunto");
         return null;
    }

    public void borrarCita(String momento) {
        
            System.out.println("Borro Cita");
        
    }


}
