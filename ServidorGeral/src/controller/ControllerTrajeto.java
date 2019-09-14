/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import servidorRMI.Servico;

/**
 *
 * @author lsjsa
 */
public class ControllerTrajeto {
    
    /**
     * Metodo responsavel por verificar a confirmação da compra(tratamento do deadlock)
     * @param servico1
     * @param servico2
     * @param servico3
     * @param trajeto 
     * @return  
     */
    public synchronized int[] verificarTrajeto(Servico servico1, Servico servico2, Servico servico3 , List<String> trajeto) throws RemoteException{
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
                    quantVoo = servico1.consultarQuantidadeDePassagens(split[0], split[1]);
                    //verificar se pode comprar
                    if(quantVoo > 0)
                        podeUsar[i] = 1;
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
        if(confirmado == trajeto.size())
            return null;
        return podeUsar; //caso nao tenha vagas em alguns trajetos
    }
    
    /**
     * Metodo responsavel por alterar a quantidade de voos disponiveis para cada trajeto das companhias
     * @param servico1
     * @param servico2
     * @param servico3
     * @param trajeto
     * @throws RemoteException 
     */
    public void atualizarQuantidade(Servico servico1, Servico servico2, Servico servico3 , List<String> trajeto) throws RemoteException{
        //percorrer o trajeto
        for(Iterator<String> iter= trajeto.iterator();((Iterator<String>) iter).hasNext();){
            //split para pegar a informacao
            String split[] = iter.next().split(";");
            //switch para verificar a companhia
            switch(split[2]){
                case "Tam":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    servico1.mudarQuantidadeDePassagens(split[0], split[1], servico1.consultarQuantidadeDePassagens(split[0], split[1]));
                    break;
                case "Gol":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    servico2.mudarQuantidadeDePassagens(split[0], split[1], servico2.consultarQuantidadeDePassagens(split[0], split[1]));
                    break;
                case "Azul":
                    //variavel para quantidade de voos disponiveis no trajeto especifico
                    servico3.mudarQuantidadeDePassagens(split[0], split[1], servico3.consultarQuantidadeDePassagens(split[0], split[1]));
                    break;
            }
        }
        
    }
}
