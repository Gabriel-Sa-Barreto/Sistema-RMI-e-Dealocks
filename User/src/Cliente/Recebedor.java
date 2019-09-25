/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente;

import ControllerPacote.ControllerPacote;
import ControllerRede.ControllerRede;
import ControllerRotas.ControllerRotas;
import java.io.*;
import java.net.Socket;

/**
 *
 * @author Gabriel Sá e Samuel Vitorio 
 */
public class Recebedor implements Runnable{
    /**
     * Atributo que receberá o Inputstream do servidor conectado.
     */
    private InputStream servidor;
    
    /**
     * Atributo que configura se as atividades da serão executadas.
     */
    private boolean start;
    
    /**Atributo responsável por guardar uma cópia do socket do cliente.
     */
    private Socket cliente;
    
    /**
     * Atributo que guarda o pacote padrão de fechamento de conexão com o servidor.
     */
    private  String fechar = "3" + ";" + "Vazio";
    
    /**Construtor da classe Recebedor que inicia todos os atributos. 
     * @author Samuel Vitorio Lima e Gabriel Sá Barreto 
     * @param servidor - Atributo responsável por receber o objeto InputStream do servidor.
     * @param cliente  - Atributo responsável por receber o socket do cliente.
     */
    public Recebedor(InputStream servidor, Socket cliente) {
        this.servidor = servidor;
        this.cliente = cliente;
        this.start    = true;
    }
   
    
    @Override
    public void run() {
        String pacote = "";
        int acao = 0;
        DataInputStream entrada = new DataInputStream(this.servidor);
        while(start){
            //recebe mensagem do servidor
            try {
                //recebimento do pacote de dados do usuário
                pacote = entrada.readUTF();
                String dados[] = pacote.split(";");
                acao = ControllerPacote.strToInt(dados[0],0);
                System.out.println(pacote);
                switch(acao) {
                    case 1:
                        //recebimento das rotas calculadas pelo servidor.
                        //apartir da origem e destino enviadas pelo usuário.
                        //formato do pacote:
                        //opcode;rota;sinalDeFinalizado
                        int finalizadoRotas = ControllerPacote.strToInt(dados[2], 2);
                        if(finalizadoRotas == 1){
                            //terá um próximo pacote de rota.
                            ControllerRotas.setHasNewRoutes(true);
                            System.out.println("Recebe pacotes rotas");
                            //adiciona uma nova rota
                            ControllerRotas.addNovaRota(dados[1]);
                        }else if(finalizadoRotas == 0){
                            //não terá um próximo pacote de rota.
                            ControllerRotas.setHasNewRoutes(false);
                            //envia pacote para encerrar conexão com o servidor.
                            ControllerRede.enviarDado(cliente, this.fechar);
                            //encerra o loop da thread
                            System.out.println("Acabou rotas");
                            start = false;
                        }
                        break;
                    case 2:
                        //recebimento de confirmação/erro do trajeto escolhido.
                        if(dados[1].equals("Compra-Realizada")){
                            System.out.println("Entrou");
                            ControllerRotas.setHasNewTrechoFailed(false);
                            //envia pacote para encerrar conexão com o servidor.
                            ControllerRede.enviarDado(cliente, this.fechar);
                            //encerra o loop da thread
                            start = false;
                        }else{
                            //recebe trajetos que deram erro.
                            //formato do pacote:
                            //opcode;trechoIndisponível;sinalDefinalizado
                            int finalizadoTrechos = ControllerPacote.strToInt(dados[1], 2);
                            if(finalizadoTrechos == 1){
                                //mais trechos falhos serão enviados. 
                                ControllerRotas.setHasNewTrechoFailed(true);
                                //formato do dados:
                                //saída;destino;empresa;tipoDeFalha
                                String trecho = dados[2] + ";" + dados[3] + ";" + dados[4] + ";" + dados[5];
                                ControllerRotas.addTrechoFalho(trecho);
                            }else if(finalizadoTrechos == 0){
                               //acabou a transmissão de trechos falhos.
                               ControllerRotas.setHasNewTrechoFailed(false);
                               //envia pacote para encerrar conexão com o servidor.
                               ControllerRede.enviarDado(cliente, this.fechar);
                               //encerra o loop da thread
                               start = false;
                            }
                        }
                        break;
                    case 4:
                        //recebimento de confirmação/erro do trajeto escolhido.
                        if(dados[1].equals("Vazio")){
                            ControllerRotas.setHasServiceFailed(false);
                            //envia pacote para encerrar conexão com o servidor.
                            ControllerRede.enviarDado(cliente, this.fechar);
                            //encerra o loop da thread
                            start = false;
                        }else{
                            //recebe servicos que estao indisponiveis.
                            //formato do pacote:
                            //opcode;servicoIndisponível;
                            ControllerRotas.setHasServiceFailed(true);
                            ControllerRotas.addServiceFailed(dados[1]);
                        }
                        break;
                    default:
                        System.out.println("Erro no campo de ação do cliente!!");
                        break;
                }
            } catch (IOException ex) {
                System.out.println("Erro de recebimento de dados: " + ex.getMessage());
            }
        }
    }
}