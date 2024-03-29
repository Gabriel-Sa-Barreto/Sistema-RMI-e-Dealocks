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
     * Estrutura de um grafo que armazena todas as rotas disponíveis para um
     * usuário.
     */
    private static Grafo grafoDeRotas = new Grafo();

    /**
     * Atributo que armazena o objeto remoto da empresa 1.
     */
    private static Servico servico1 = null;

    /**
     * Atributo que armazena o objeto remoto da empresa 2.
     */
    private static Servico servico2 = null;

    /**
     * Atributo que armazena o objeto remoto da empresa 3.
     */
    private static Servico servico3 = null;

    /**
     * Método que inicializa os objetos que serão usados para acessar os métodos
     * remotos.
     */
    public static void startServico() {
        try {
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            //servico da companhia1
            servico1 = (Servico) Naming.lookup("//172.16.201.60/1099");
            servico1.lerRotas();
        } catch (Exception ex) {
            System.out.println("Erro com algum serviço 1" + ex.getMessage());
        }

        try {
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            servico2 = (Servico) Naming.lookup("//172.16.201.61/1091");
            servico2.lerRotas();
        } catch (Exception ex) {
            System.out.println("Erro com algum serviço 2" + ex.getMessage());
        }

        try {
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            servico3 = (Servico) Naming.lookup("//172.16.201.62/1099");
            servico3.lerRotas();
        } catch (Exception ex) {
            System.out.println("Erro com algum serviço 3" + ex.getMessage());
        }
    }

    public static void construirGrafo(int opcao) throws IOException {
        List<Rotas> trajetos = new ArrayList<Rotas>();
        switch (opcao) {
            case 1:
                trajetos = servico1.lerRotas();
                break;
            case 2:
                trajetos = servico2.lerRotas();
                break;
            case 3:
                trajetos = servico3.lerRotas();
                break;
        }
        //pega todas as rotas e coloca no grafo
        for (Iterator<Rotas> iter = trajetos.iterator(); ((Iterator<Rotas>) iter).hasNext();) {
            Rotas verificar = iter.next();
            String origem = verificar.getOrigem(); //pega o destino do trajeto
            String destino = verificar.getDestino();//pega a origem do trajeto
            if (grafoDeRotas.existeVertice(origem) == -1) //verifica se já existe o vertice pertencente a origem
            {
                grafoDeRotas.addVertice(origem);
            }
            if (grafoDeRotas.existeVertice(destino) == -1) //verifica se já existe o vertice pertencente o destino
            {
                grafoDeRotas.addVertice(destino);
            }
            if (grafoDeRotas.existeAresta(verificar.getOrigem(), verificar.getDestino()) == -1) //caso não exista uma aresta com esse destino e origem
            {
                grafoDeRotas.addAresta(origem, destino, verificar.getQuantidade());//adiciona nova aresta
            }
        }
    }

    /**
     * Metodo responsavel por verificar a confirmação da compra(tratamento do
     * deadlock)
     *
     * @param trajeto
     * @return
     */
    public static synchronized int[] verificarTrajeto(List<String> trajeto) throws RemoteException, IOException {
        //startServico();
        //vetor responsavel por verificaar se os trajetos escolhidos estão disponiveis
        int podeUsar[] = new int[trajeto.size()];
        //inicializar o vetor
        for (int i = 0; i < podeUsar.length; i++) {
            podeUsar[i] = 0;
        }
        int i = 0;
        //verificar todos os trajetos
        for (Iterator<String> iter = trajeto.iterator(); ((Iterator<String>) iter).hasNext();) {
            //split para pegar a informacao
            String split[] = iter.next().split(";");
            //switch para verificar a companhia
            switch (split[2]) {
                case "Tam":
                    try {
                        int quantVoo1;
                        //variavel para quantidade de voos disponiveis
                        quantVoo1 = servico1.consultarQuantidadeDePassagens(split[0], split[1]);
                        //verificar se pode comprar
                        if (quantVoo1 > 0) {
                            podeUsar[i] = 1;
                        }
                    } catch (NullPointerException ex) {
                        System.err.println("Serviço 1 indisponível");
                        podeUsar[i] = 2; //informa que o trecho está indisponível por causa do serviço que não está aberto
                    }
                    break;
                case "Gol":
                    try {
                        //variavel para quantidade de voos disponiveis
                        int quantVoo2;
                        quantVoo2 = servico2.consultarQuantidadeDePassagens(split[0], split[1]);
                        //verificar se pode comprar
                        if (quantVoo2 > 0) {
                            podeUsar[i] = 1;
                        }
                    } catch (NullPointerException ex) {
                        System.err.println("Serviço 2 indisponível");
                        podeUsar[i] = 2; //informa que o trecho está indisponível por causa do serviço que não está aberto
                    }
                    break;
                case "Azul":
                    try {
                        int quantVoo3;
                        //variavel para quantidade de voos disponiveis
                        quantVoo3 = servico3.consultarQuantidadeDePassagens(split[0], split[1]);
                        //verificar se pode comprar
                        if (quantVoo3 > 0) {
                            podeUsar[i] = 1;
                        }
                    } catch (NullPointerException ex) {
                        System.err.println("Serviço 3 indisponível");
                        podeUsar[i] = 2; //informa que o trecho está indisponível por causa do serviço que não está aberto
                    }
                    break;
            }
            i++;
        }

        //verificar se todos os trajetos estao disponivieis
        int confirmado = 0; // verificar a quantidade de confirmado 
        for (int j = 0; j < podeUsar.length; j++) {
            if (podeUsar[j] == 1) {
                confirmado++;
            }
        }
        //caso todos estão disponiveis
        if (confirmado == trajeto.size()) {
            //atualiza as quantidades de passagens por vaga
            atualizarQuantidade(trajeto);
            return null;
        }
        return podeUsar; //caso nao tenha vagas em alguns trajetos
    }

    /**
     * Metodo responsavel por alterar a quantidade de voos disponiveis para cada
     * trajeto das companhias
     *
     * @param trajeto
     * @throws RemoteException
     */
    private static synchronized void atualizarQuantidade(List<String> trajeto) throws RemoteException {
        //percorrer o trajeto
        for (Iterator<String> iter = trajeto.iterator(); ((Iterator<String>) iter).hasNext();) {
            //split para pegar a informacao
            String split[] = iter.next().split(";");
            //switch para verificar a companhia
            switch (split[2]) {
                case "Tam":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    servico1.mudarQuantidadeDePassagens(split[0], split[1], servico1.consultarQuantidadeDePassagens(split[0], split[1]) - 1);
                    break;
                case "Gol":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    servico2.mudarQuantidadeDePassagens(split[0], split[1], servico2.consultarQuantidadeDePassagens(split[0], split[1]) - 1);
                    break;
                case "Azul":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    servico3.mudarQuantidadeDePassagens(split[0], split[1], servico3.consultarQuantidadeDePassagens(split[0], split[1]) - 1);
                    break;
            }
        }
    }
    
    /**
     * Método que acessa o grafo de rotas e retorna todas as possibilidades de
     * rotas encontradas, apartir de uma origem e destino.
     *
     * @param origem - String
     * @param destino - String
     * @return lista de possibilidade de rotas.
     */
    public synchronized static List<String> buscarRotas(String origem, String destino) {
        List<String> rotas = grafoDeRotas.buscarCaminhos(origem, destino);
        return rotas;
    }

    /**
     * Metodo que pega o atributo do servico 1
     *
     * @return
     */
    public static Servico getServico1() {
        return servico1;
    }

    /**
     * Metodo que pega o atributo do servico 2
     *
     * @return
     */
    public static Servico getServico2() {
        return servico2;
    }

    /**
     * Metodo que pega o atributo do servico 3
     *
     * @return
     */
    public static Servico getServico3() {
        return servico3;
    }

}
