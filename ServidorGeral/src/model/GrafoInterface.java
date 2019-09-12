/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 * Interface que define os métodos que um usuário poderá acessar da classe Grafo.
 * @author gabriel
 */
public interface GrafoInterface {
 
    /**
     * Método que adiciona um novo vértice ao grafo, recebendo como parâmetro o index
     * para o array de vértice e o nome associado ao vértice. 
     * @param nome 
     */
    public void addVertice(String nome);
    
    /**
     * Método que verifica se já existe vertice
     * @param nome 
     * @return  
     */
    public int existeVertice(String nome);
    
    /**
     * Método que adiciona uma nova aresta entre dois vértices e
     * recebe como parâmetro o nome dos vértices que serão interligados e o peso da aresta,
     * v1 é o vértice no qual terá v2 como adjacente.
     * @param v1
     * @param v2
     * @param peso
     * @return 1 caso operação feita com sucesso e 0 caso ocorra uma falha.
     */
    public int addAresta(String v1, String v2, int peso);
    
    /**
     * Método que verifica se existe uma conexão entre dois vértices e
     * recebe como parâmetro o nome dos vértices que estão interligados pela aresta.
     * @param v1
     * @param v2
     * @return -1 caso não exista ligação entre os vértices desejados. Caso encontre, retorna o peso da aresta. 
     */
    public int existeAresta(String v1, String v2);
    
    /**
     * Método que verifica se um vértice possue vértices adjacentes a ele.
     * @param v
     * @return true ou false
     */
    public boolean listaAdjVazia(String v);
    
    /**
     * Método que retorna a lista de adjacencia de um vértice, recebe como parâmetro o nome
     * do vértice desejado.
     * @param v
     * @return null caso não encontre o vértice desejado ou se a lista de adjacencia estiver vazia.
     */
    public ArrayList listAdjacencia(String v);
    
      
    /**
     * Método que remove uma aresta desejada,
     * recebe como parâmetro v1, o vértice no qual deseja-se excluir a adjacência com o vértice v2.
     * @param v1
     * @param v2
     * @return 1 para sucesso e 0 para false.
     */
    public int removeAresta(String v1, String v2);
    
    /**
     * Método que retorna o número de arestas do grafo.
     * @return 
     */
    public int numArestas();
    
     /**
     * Método que retorna o número de vértices do grafo.
     * @return 
     */
    public int numVertices(); 

    
    /**
     * Método que configura um novo peso para a ligação entre dois vértices, no qual
     * v2 é adjacente a v1.
     * @param v1
     * @param v2
     * @param newPeso
     * @return 1 caso a operação tenha sido feita com sucesso e 0 caso tenha ocorrido alguma falha.
     */
    public int setPesoAresta(String v1, String v2, int newPeso); 
    
    /**
     * Método que procura por todos os caminhos possiveis a partir da origem para o destino
     * @param vi
     * @param v2 
     */
    public void buscarCaminhos(String origem , String destino);
    
    
    
}
