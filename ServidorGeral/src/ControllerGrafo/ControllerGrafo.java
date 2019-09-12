/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerGrafo;

import java.util.List;
import model.Grafo;

/**
 *
 * @author Gabriel Sá e Samuel Vitorio
 */
public class ControllerGrafo {
    
    /**
     * Estrutura de um grafo que armazena todas as rotas disponíveis para um usuário.
     */
    private static Grafo grafoDeRotas = new Grafo();
    
    
    /**
     * Método que acessa o gráfico
     * @return 
     */
    public synchronized static List<String> buscarRotas(String origem, String destino){
        grafoDeRotas.buscarCaminhos(origem, destino);
        return null;
    } 
    
    
    
}
