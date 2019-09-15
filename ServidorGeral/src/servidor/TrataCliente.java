/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import ControllerPacote.ControllerPacotes;
import controller.ControllerTrajeto;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

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
     * Atributo que armazena o conjunto dos trechos de uma rota escolhida por um usuário.
     */
    private List<String> listaDeTrechos;
    
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
        boolean loop = true;
        while(loop){
            try {
                acao = entrada.readUTF(); //pacote de dados do usuário.
                String dados[] = acao.split(";");
                opcode = ControllerPacotes.strToInt(dados[0],0); //conversão para inteiro do campo de OpCode. 
                switch(opcode){
                    case 1: 
                        //1- Recebimento de origem e destino enviado pelo usuário.
                        String origem  = dados[1];
                        String destino = dados[2];
                        List<String> rotas = ControllerTrajeto.buscarRotas(origem, destino);

                        //Envia ao cliente todas as possibilidades de rotas apartir da origem e destino desejado.
                        //na aplicação do usuário, realizar o tratamento de trechos repetidos na hora de apresentar a ele.
                        String pacote = "";
                        for(Iterator<String> iter = rotas.iterator();((Iterator<String>) iter).hasNext();){
                            pacote = "1" + ";" + iter.next() + ";" + "1";
                            servidor.distribuiMensagem(pacote);
                        }
                        
                        //envia pacote de encerramento
                        pacote = "1" + ";" + "vazio" + ";" + "0";
                        servidor.distribuiMensagem(pacote);
                        break;
                    case 2: 
                        //Recebimento do trajeto escolhido pelo cliente.
                        //converte para inteiro o campo que informa se já foram enviados
                        //todos os pacotes da rota escolhida pelo cliente.
                        //formato do pacote:
                        //opcode;valorDeFinalizado;saída;destino;empresa
                        int terminado = ControllerPacotes.strToInt(dados[1],2); 
                        if(terminado == 0){
                            String resposta = "";
                            //caso tenha terminado, realiza a verificação se todos os trechos estão disponíveis.
                            int[] verificacao = ControllerTrajeto.verificarTrajeto(listaDeTrechos);
                            if(verificacao == null){
                                //todos os trechos estão disponíveis.
                                //seta confirmação da vaga do cliente.
                                ControllerTrajeto.atualizarQuantidade(listaDeTrechos);
                                resposta = "2" + ";" + "Compra-Realizada";
                                 //agora envia confirmação de pedido feito.
                                servidor.distribuiMensagem(resposta);
                            }else{
                                //envia trechos indisponíveis
                                for(int i = 0; i < verificacao.length; i++){
                                    if(verificacao[i] == 0){
                                        resposta = "2" + ";" + "1" + listaDeTrechos.get(i);
                                        servidor.distribuiMensagem(resposta);
                                    }
                                }
                                //agora envia um pacote para informar que acabou a transmissão de trechos indisponíveis.
                                //formato do pacote:
                                //opcode;sinalDefinalizado;trechoIndisponível
                                resposta = "2" + ";" + "0" + "Vazio";
                                servidor.distribuiMensagem(resposta);
                                listaDeTrechos.clear();
                            }                           
                        }else if(terminado == 1){
                            //caso não tenha acabado, armazena o trecho recebido 
                            //e espera pelo próximo.
                            String trecho = dados[2] + ";" + dados[3] + ";" + dados[4];
                            listaDeTrechos.add(trecho);
                        } 
                        break;
                    case 3: //pacote para encerrar conexão com cliente.
                        loop = false;
                        break;
                    default:
                }
            } catch (IOException ex) {
                System.out.println("Erro : " + ex.getMessage());
            }
       }
    }
}
