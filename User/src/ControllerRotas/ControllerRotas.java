/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerRotas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabriel
 */
public class ControllerRotas {
    
    /**
     * Lista que armazena as possibilidades de rotas recebidas do servidor
     * apartir de uma origem e destino.
     */
    private static List<String> rotas;
    
    /**
     * Atributo que implementa a técnica de semáforo para o acesso à lista de rotas por parte
     * do cliente.
     */
    private static int semaforoNewRoutes = 0;
    
    /**
     * Atributo que armazena a lista de trechos que estão indisponíveis.
     */
    private static List<String> trechosIndisponiveis;
    
    /**
     * Atributo que implementa a técnica de semáforo para o acesso à lista de trechosIndisponiveis por parte
     * do cliente.
     */
    private static int semaforoNewTrechoFailed = 0;

    public ControllerRotas() {
        rotas = new ArrayList<String>();
        trechosIndisponiveis = new ArrayList<String>();
    }
    
    
    
    /**
     * Método que adiciona uma nova rota recebida pelo servidor.
     * @param rota 
     */
    public static void addNovaRota(String rota){
        rotas.add(rota);
    }
    
     /**
     * Método que adiciona um trecho falho recebido pelo servidor.
     * @param trecho 
     */
    public static void addTrechoFalho(String trecho){
        trechosIndisponiveis.add(trecho);
    }

    /**
     * Método que retorna a lista de rotas após enviado origem e destino.
     * @return List
     */
    public static List<String> getRotas() {
        rotas.forEach(u -> System.out.println("Rota: " + u.toString()));
        return rotas;
    }

    /**
     * Método que informa ao usuário se já foram recebidos todos os trechos falhos enviadas pelo servidor
     * após a verificação da compra.
     * @return 
     */
    public static boolean isHasNewTrechoFailed() {
        if(semaforoNewTrechoFailed == 1){
            return false;
        }
        return true;
    }
    
    /**
     * Método que informa ao usuário se terão novas rotas sendo enviadas pelo servidor.
     * @return 
     */
    public static boolean isHasNewRoutes() {
        if(semaforoNewRoutes == 1){
            return false;
        }
        return true;
    }
    

    /**
     * Método que configura se terão novas rotas sendo armazenadas na lista,
     * através da técnica do semáforo.
     * @param hasNewRoute 
     */
    public static void setHasNewRoutes(boolean hasNewRoute) {
        if(!hasNewRoute){ //caso seja falso, não terá mais novas rotas.
            semaforoNewRoutes = 1; //libera o acessso
            return;
        }
        semaforoNewRoutes = 0; //trava o acesso à lista de rotas.
    }

    /**
     * Método que configura se terá novas rotas sendo armazenadas na lista,
     * através da técnica do semáforo. 
     * @param hasNewRouteFailed
     */
    public static void setHasNewTrechoFailed(boolean hasNewRouteFailed) {
        if(!hasNewRouteFailed){ //caso seja falso, não terá mais novos trechos falhos.
            semaforoNewTrechoFailed = 1; //libera o acessso
            return;
        }
        semaforoNewTrechoFailed = 0; //trava o acesso à lista de rotas.
    }   

    /**
     * Método que retorna a lista de trechos indisponíveis quando realizado o pedido de compra.
     * @return List
     */
    public static List<String> getTrechosIndisponiveis() {
        return trechosIndisponiveis;
    }
    
    /**
     * Método que limpa a lista de rotas enviadas pelo servidor.
     */
    public static void clearListRotas(){
        rotas.clear();
    }
}
