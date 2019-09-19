/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerRede;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Gabriel Sá e Samuel Vitorio.
 */
public class ControllerRede {
    /**Método para enviar os pacotes desejados ao servidor.
    * @author Samuel Vitorio Lima e Gabriel Sá Barreto
    * @param conexao Socket - obter a conexao com o server para enviar os pacotes para ele via TCP
     * @param pacote
    */
    public static void enviarDado(Socket conexao , String pacote){
        try{
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            saida.writeUTF(pacote);
        }catch(IOException ex){
            System.out.println("ControllerRede->Erro de envio na mensagem: " +  ex.toString());
        }
    } 
}
