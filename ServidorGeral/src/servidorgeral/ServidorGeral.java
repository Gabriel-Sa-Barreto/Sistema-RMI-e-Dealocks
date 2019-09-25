/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgeral;

import controller.ControllerTrajeto;
import java.io.IOException;
import servidor.Servidor;

/**
 *
 * @author Gabriel Sá, Samuel Vitorio e Daniel
 */
public class ServidorGeral {   
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        //criacao do servidor que se comunicará com o cliente.
        Servidor server = new Servidor(1885);   
    }   
}
