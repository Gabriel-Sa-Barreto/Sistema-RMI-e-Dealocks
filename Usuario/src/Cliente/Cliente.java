/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Gabriel Sá e Samuel Vitorio
 */
public class Cliente {
     /**Atributo responsável por guardar o ip da máquina que o cliente irá se conectar.
    */
    private static String host;
    
    /**Atributo responsável por guardar a porta do servidor para conexão.
     */
    private static int porta;
    
    /**Atributo responsável por guardar o socket do cliente.
     */
    private static Socket cliente;
    
    /**Atributo responsável por guardar o objeto que será executado em uma thread para recebimento dos pacotes do servidor.
     */
    private static Recebedor receive;
    
    /**Construtor da classe Cliente que é responsável por criar um novo cliente no sistema e conectá-lo ao servidor  
    * @param host String -Ip da máquina ao qual o cliente irá se conectar
    * @param porta int    - Porta do servidor para conexão
    * @author Samuel Vitorio Lima e Gabriel Sá Barreto 
    * @throws java.io.IOException 
    */
    public Cliente (String host, int porta) throws IOException {
        this.host = host;
        this.porta = porta;
    }
    
    /**Método para conectar o cliente ao servidor, este método é ao clicar um novo objeto do tipo Cliente.
    *  @author Samuel Vitorio Lima e Gabriel Sá Barreto 
    */
    public void conexao() throws IOException{
        cliente = new Socket(this.host, this.porta);
        System.out.println("O cliente se conectou ao servidor!");
    }
    
    
    /**
     * Método responsável pela criação e execução da thread responsável por receber pacotes vindos do servidor 
     * para o objeto cliente.
     * @author Samuel Vitorio Lima e Gabriel Sá Barreto
     * @throws UnknownHostException
     * @throws IOException 
     */
    public void executa() throws UnknownHostException, IOException {
        // thread para receber mensagens do servidor
        receive = new Recebedor(cliente.getInputStream(), this.cliente);
        new Thread(receive).start();
        System.out.println("inicia Recebedor");
    }

    /**
     * Método que retorna o objeto socket que está vinculado ao cliente
     * @author Samuel Vitorio Lima e Gabriel Sá Barreto
     * @return 
     */
    public Socket getCliente() {
        return cliente;
    }

    /**
     * Método para configurar o objeto socket que está vinculado ao cliente.
     * @author Samuel Vitorio Lima e Gabriel Sá Barreto
     * @param cliente 
     */
    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }
}
