/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Grafo;
import model.Rotas;
import servidorRMI.Servico;

/**
 *
 * @author Samuel Vitorio Lima , Gabriel Sá e Daniel
 */
public class ControllerTrajeto {
    
    /**
     * Estrutura de um grafo que armazena todas as rotas disponíveis para um usuário.
     */
    private static Grafo grafoDeRotas = new Grafo();
     
    /**
     * Atributo que armazena o objeto remoto da empresa 1.
     */
    private static Servico servico1;
    
    /**
     * Atributo que armazena o objeto remoto da empresa 2.
     */
    private static Servico servico2;
    
    /**
     * Atributo que armazena o objeto remoto da empresa 3.
     */
    private static Servico servico3;
    
    /**
     * Método que inicializa os objetos que serão usados para acessar 
     * os métodos remotos.
     */
    public static void startServico(){
        try{
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            //servico da companhia1
            servico1 = (Servico) Naming.lookup("//192.168.25.9/1099");
        } catch(Exception ex){
            System.out.println("Erro com algum serviço 1" + ex.getMessage());
        }
        
        try{
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            servico2 = (Servico) Naming.lookup("//192.168.25.9/1099");
        } catch(Exception ex){
            System.out.println("Erro com algum serviço 2" + ex.getMessage());
        }
        
        try{
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            servico3 = (Servico) Naming.lookup("//192.168.25.9/1099");
        } catch(Exception ex){
            System.out.println("Erro com algum serviço 3" + ex.getMessage());
        }     
        grafoDeRotas = new Grafo();
    }
    
    public static void construirGrafo(int opcao) throws IOException{
        List<Rotas> trajetos = new ArrayList<Rotas>();
        switch(opcao){
            case 1: trajetos = servico1.lerRotas();
                break;
            case 2: trajetos = servico2.lerRotas();
                break;
            case 3: trajetos = servico3.lerRotas();
                break;
        }
        //pega todas as rotas e coloca no grafo
        for(Iterator<Rotas> iter= trajetos.iterator();((Iterator<Rotas>) iter).hasNext();){
             Rotas verificar = iter.next();
             String origem = verificar.getOrigem(); //pega o destino do trajeto
             String destino = verificar.getDestino();//pega a origem do trajeto
             if(grafoDeRotas.existeVertice(origem) == -1) //verifica se já existe o vertice pertencente a origem
                 grafoDeRotas.addVertice(origem);
             if(grafoDeRotas.existeVertice(destino) == -1) //verifica se já existe o vertice pertencente o destino
                 grafoDeRotas.addVertice(destino);
             if(grafoDeRotas.existeAresta(verificar.getOrigem(),verificar.getDestino()) == -1) //caso não exista uma aresta com esse destino e origem
                 grafoDeRotas.addAresta(origem,destino,verificar.getQuantidade());//adiciona nova aresta
         }
    }
    
    
    
    /**
     * Metodo responsavel por verificar a confirmação da compra(tratamento do deadlock)
     * @param trajeto 
     * @return  
     */
    public static synchronized int[] verificarTrajeto(List<String> trajeto) throws RemoteException{
        //vetor responsavel por verificaar se os trajetos escolhidos estão disponiveis
        int podeUsar[] = new int[trajeto.size()];
        //inicializar o vetor
        for(int i = 0; i < podeUsar.length ; i++){
            podeUsar[i] = 0;
        }
        int i = 0;
        int quantVoo;
        //verificar todos os trajetos
        for(Iterator<String> iter= trajeto.iterator();((Iterator<String>) iter).hasNext();){
            //split para pegar a informacao
            String split[] = iter.next().split(";");
            //switch para verificar a companhia
            switch(split[2]){
                case "Tam":
                    //variavel para quantidade de voos disponiveis
                    System.out.println(split[0]);
                    System.out.println(split[1]);
                    quantVoo = servico1.consultarQuantidadeDePassagens(split[0], split[1]);
                    System.out.println(quantVoo);
                    //verificar se pode comprar
                    if(quantVoo > 0){
                       podeUsar[i] = 1;
                    }
                    break;
                case "Gol":
                    //variavel para quantidade de voos disponiveis
                    quantVoo = servico2.consultarQuantidadeDePassagens(split[0], split[1]);
                    //verificar se pode comprar
                    if(quantVoo > 0)
                        podeUsar[i] = 1;
                    break;
                case "Azul":
                    //variavel para quantidade de voos disponiveis
                    quantVoo = servico3.consultarQuantidadeDePassagens(split[0], split[1]);
                    //verificar se pode comprar
                    if(quantVoo > 0)
                        podeUsar[i] = 1;
                    break;
            }
            i++;
        }
        
        //verificar se todos os trajetos estao disponivieis
        int confirmado = 0; // verificar a quantidade de confirmado 
        for(int j = 0; j < podeUsar.length ; j++){
            if(podeUsar[j] == 1)
                confirmado++;
        }
        //caso todos estão disponiveis
        System.out.println(confirmado);
        System.out.println(trajeto.size());
        if(confirmado == trajeto.size()){
            System.out.println("Teste");
            //atualiza as quantidades de passagens por vaga
            atualizarQuantidade(trajeto);
            return null;
        }
        System.out.println("Nao");
        return podeUsar; //caso nao tenha vagas em alguns trajetos
    }
    
    /**
     * Metodo responsavel por alterar a quantidade de voos disponiveis para cada trajeto das companhias
     * @param trajeto
     * @throws RemoteException 
     */
    private static synchronized void atualizarQuantidade(List<String> trajeto) throws RemoteException{
        //percorrer o trajeto
        for(Iterator<String> iter= trajeto.iterator();((Iterator<String>) iter).hasNext();){
            //split para pegar a informacao
            String split[] = iter.next().split(";");
            //switch para verificar a companhia
            switch(split[2]){
                case "Tam":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    System.out.println("qa: " + servico1.consultarQuantidadeDePassagens(split[0], split[1]));
                    servico1.mudarQuantidadeDePassagens(split[0], split[1], servico1.consultarQuantidadeDePassagens(split[0], split[1]));
                    System.out.println("qd: " + servico1.consultarQuantidadeDePassagens(split[0], split[1]));
                    break;
                case "Gol":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    System.out.println(servico1.consultarQuantidadeDePassagens(split[0], split[1]));
                    servico2.mudarQuantidadeDePassagens(split[0], split[1], servico2.consultarQuantidadeDePassagens(split[0], split[1]));
                    break;
                case "Azul":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    System.out.println(servico1.consultarQuantidadeDePassagens(split[0], split[1]));
                    servico3.mudarQuantidadeDePassagens(split[0], split[1], servico3.consultarQuantidadeDePassagens(split[0], split[1]));
                    break;
            }
        }
    }
    
    /**
     * Método que acessa o grafo de rotas e retorna todas as possibilidades de rotas encontradas,
     * apartir de uma origem e destino.
     * @param origem  - String
     * @param destino - String
     * @return lista de possibilidade de rotas.
     */
    public synchronized static List<String> buscarRotas(String origem, String destino){
        List<String> rotas = grafoDeRotas.buscarCaminhos(origem, destino);
        return rotas;
    }     
}
