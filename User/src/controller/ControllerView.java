/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel Vitorio Lima , Gabriel Sá e Daniel
 */
public class ControllerView {

    //private static Servico servico1 = null;
    private static List<String> rotas;
    /**
     * 
     */
    private static List<String> cidades = new ArrayList<>();

    /**
     * Classe para controle da view
     */
    public ControllerView() {
        rotas = new ArrayList();
        cidades.add("Salvador");
        cidades.add("Recife");
        cidades.add("Fortaleza");
        cidades.add("Terezina"); 
        cidades.add("São Luís"); 
        cidades.add("Aracaju"); 
        cidades.add("Natal"); 
        cidades.add("Goiânia"); 
        cidades.add("Manaus"); 
        cidades.add("Porto Velho"); 
        cidades.add("Palmas"); 
        cidades.add("Rio Branco"); 
        cidades.add("Campo Grande"); 
        cidades.add("Belém"); 
        cidades.add("Porto Alegre"); 
        cidades.add("Curitiba"); 
        cidades.add("São Paulo"); 
        cidades.add("Rio Janeiro"); 
        cidades.add("Florianopolis"); 
        cidades.add("Belo Horizonte"); 
        cidades.add("Vitoria"); 
    }
    
    /**
    * Método para pegar cidades no servidor
    *
    *@throws java.rmi.RemoteException
    * @return
    */
    public static List<String> lerRotas(){
       return cidades;
    }
    
    /**
     * Método para salvar na lista de rotas os trechos escolhidos pelo usuário
     *
     * @param cidade1
     * @param cidade2
     * @param companhia
     */
    public static void salvarLista(String cidade1, String cidade2, String companhia) {
        rotas.add(cidade1 + ";" + cidade2 + ";" + companhia);
    }
}
