/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import ControllerPacote.ControllerPacotes;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel Sá e Samuel Vitorio
 */
public class TrataCliente implements Runnable{
    
    /**
     * InputStream do cliente.
     */
    private InputStream cliente;

    /**
     * Objeto do Servidor para realizar ações de envio de mensagem a todos os cliente conectados.
     */
    private Servidor servidor;

    /**
     * Atributo que especifica a acao que será executada pelo servidor.
     */
    private String acao;
    
    /**
     * Atributo que indica o campo de opcode que foi convertido de string para int do pacote de dados recebido.
     */
    private int opcode;
    
    /**
     * Construtor que inicializa todos os atributos da classe.
     * @param cliente  - InputStream do cliente conectado.
     * @param servidor - Objeto servidor
     */
    public TrataCliente(InputStream cliente, Servidor servidor){
        this.cliente  = cliente;
        this.servidor = servidor;
    } 

    @Override
    public void run() {
        DataInputStream entrada = new DataInputStream(cliente);
        try {
            acao = entrada.readUTF(); //pacote de dados do usuário.
            String split[] = acao.split(";");
            opcode = ControllerPacotes.strToInt(split[0],0); //conversão para inteiro do campo de OpCode. 
            switch(acao){
                case "1": //1- Recebimento de origem e destino enviado pelo usuário.
                    //enviar para o cliente todas as possibilidades de rotas
                    //na aplicação do usuário, realizar o tratamento de trechos repetidos na hora de apresentar a ele.
                   
                    break;
                case "2": //Recebimento do trajeto escolhido pelo cliente.
                    
                    break;
                default:
            }
            
            
            
            //implementar ações do cliente
        } catch (IOException ex) {
            System.out.println("Erro : " + ex.getMessage());
        }
        
        
        
    }
}
