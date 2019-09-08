/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 * Classe que implementa uma estrutura de dados chamada grafos.
 * @author gabriel
 */
public class Grafo implements GrafoInterface {
      
    /**
     * Atributo que armazena a lista de vértices atuais do grafo.
     */
    private ArrayList<Celula> listVertices;
    
    /**
     * Atributo que informa o número de arestas do grafo.
     */
    private int numArestas;
    
    /**
     * Método construtor que inicia um novo grafo,
     * recebendo como parâmetro inicial a quantidade de vértices que ele terá. 
     */
    public Grafo() {
        this.listVertices = new ArrayList<Celula>();
        this.numArestas   = 0;
    }

    @Override
    public void addVertice(String nome) {
        if(listVertices.isEmpty()){
            //adiciona o primeiro vértice do grafo.
            listVertices.add( new Celula(0,nome) );
            return;
        }
        //adiciona um novo vértice ao fim do array de vértices.
        listVertices.add( new Celula(listVertices.size(),nome) );
    }
    
    @Override
    public int addAresta(String v1, String v2, int peso) { 
        Celula c1 = null;
        Celula c2 = null;
        int verificacao = 0;
        for(int i = 0; i < listVertices.size(); i++){
            Celula c = listVertices.get(i);            
            if(c.getNome().equals(v1)){
                c1 = c;
                verificacao++;
            }
            if(c.getNome().equals(v2)){
                c2 = c;
                verificacao++;
            }
        }
        if(verificacao == 2){
            //caso tenha encontrado os dois vértice adiciona a adjacência. 
            this.listVertices.get(c1.getVertice()).getArestas().add(new Aresta(c2,peso));
            this.numArestas++; //incrementa o número de arestas do grafo.
            return 1;
        }
        return 0;
    }

    @Override
    public int existeAresta(String v1, String v2) {
        for(int i = 0; i < listVertices.size(); i++){ //busca primeiramente o vértice no qual desejamos acessar sua lista de adjacências.
            Celula c = listVertices.get(i);            
            if(c.getNome().equals(v1)){
                //depois de encontrado o vértice desejado, consulta-se sua lista de adjacências para comprovar se existe a ligação entre os dois vértices.
                Aresta c2;
                for(int j = 0; j < c.getArestas().size(); j++){
                    c2 = c.getArestas().get(j);
                    if(c2.getVert().getNome().equals(v2)){
                        return c2.getPeso(); 
                    }
                }
            }
        }       
      return -1;
    }

    @Override
    public boolean listaAdjVazia(String v) {
        for(int i = 0; i < listVertices.size(); i++){ //busca primeiramente o vértice no qual desejamos acessar sua lista de adjacências.
            Celula c = listVertices.get(i);            
            if(c.getNome().equals(v)){
                //depois de encontrado o vértice desejado, consulta-se sua lista de adjacências.
                if(c.getArestas().isEmpty()){
                    return true;
                }else{
                    return false;
                }
            }
        }      
        return false;
    }

    @Override
    public int removeAresta(String v1, String v2) {
        for(int i = 0; i < listVertices.size(); i++){ //busca primeiramente o vértice no qual desejamos acessar sua lista de adjacências.
            Celula c = listVertices.get(i);            
            if(c.getNome().equals(v1)){
                //depois de encontrado o vértice desejado, consulta-se sua lista de adjacências para buscar a aresta que será removida.
                Aresta c2;
                for(int j = 0; j < c.getArestas().size(); j++){
                    c2 = c.getArestas().get(j);
                    if(c2.getVert().getNome().equals(v2)){ //caso encontre a aresta desejada.
                        c.getArestas().remove(j);
                        return 1; 
                    }
                }
            }
        }   
        return 0;
    }

    @Override
    public int numArestas() {
        return this.numArestas;
    }

    @Override
    public int numVertices() {
       return this.listVertices.size();
    }

    @Override
    public int setPesoAresta(String v1, String v2, int newPeso) {
       for(int i = 0; i < listVertices.size(); i++){ //busca primeiramente o vértice no qual desejamos acessar sua lista de adjacências.
            Celula c = listVertices.get(i);            
            if(c.getNome().equals(v1)){
                //depois de encontrado o vértice desejado, consulta-se sua lista de adjacências para configurar o novo peso da aresta.
                Aresta c2;
                for(int j = 0; j < c.getArestas().size(); j++){
                    c2 = c.getArestas().get(j);
                    if(c2.getVert().getNome().equals(v2)){      //caso encontre a aresta desejada.
                        c.getArestas().get(j).setPeso(newPeso); //configura o novo peso da aresta.
                        return 1; 
                    }
                }
            }
        }     
        return 0;
    }

    @Override
    public ArrayList listAdjacencia(String v) {
        for(int i = 0; i < listVertices.size(); i++){ //busca primeiramente o vértice no qual desejamos acessar sua lista de adjacências.
            Celula c = listVertices.get(i);            
            if(c.getNome().equals(v)){
                //depois de encontrado o vértice desejado, retorna-se sua lista de adjacencia caso não esteja vazio.
                if(!c.getArestas().isEmpty()){
                    return c.getArestas();
                }else return null;
            }
        }     
        return null;
    }
    
    /**
     * Classe que descreve um vértice (célula) pertencente ao grafo.
     * @author gabriel
     */
    protected class Celula {
        
        /**
         * Índice que indica a célula (vértice) no array de vértices do grafo.
         */
        private int vertice;
        
        /**
         * Atributo que informa o nome associado ao vértice do grafo.
         */
        private String nome;
        
        /**
         * Atributo que armazena os vértices adjacentes ao vértice criado.
         */
        private ArrayList<Aresta> arestas;

        /**
         * Método construtor que inicia um novo objeto que será um vértice do grafo.
         * @param vertice
         * @param nome 
         */
        public Celula(int vertice, String nome) {
            this.vertice = vertice;
            this.nome = nome;
            this.arestas = new ArrayList<Aresta>();
        }

        /**
         * Método que retorna o índice associado ao vértice.
         * @return 
         */
        public int getVertice() {
            return vertice;
        }

        /**
         * Método que retorna o nome do vértice.
         * @return 
         */
        public String getNome() {
            return nome;
        }

        /**
         * Método que retornar a lista de vértice adjacentes a uma célula.
         * @return 
         */
        public ArrayList<Aresta> getArestas() {
            return arestas;
        }
        
        /**
         * Método que adiciona um vértice adjacente.
         * @param v 
         */
        public void addArestas(Aresta v){
            this.arestas.add(v);
        }
         
        /**
         * Método usado para verificar se um vértice qualquer é adjacente a um outro vértice
         * ao se percorrer a lista de adjacência do mesmo.
         * @param obj
         * @return 
         */
        
    }

    /**
     * Classe que descreve a ligação entre dois vértices (células)
     * @author gabriel
     */
    protected class Aresta {

        /**
         * Atributo que informa o nome do vértice ao qual a aresta está conectando.
         */
        private Celula vertice;

        /**
         * Atributo que informa o peso da aresta. 
         */
        private int peso;


        /**
         * Método construtor que monta uma ligação entre dois vértices através de uma aresta. 
         * @param v1
         * @param peso 
         */
        public Aresta(Celula v1, int peso) {
            this.vertice = v1;
            this.peso = peso;
        }

        /**
         * Método que retorna o nome do vértice 1.
         * @return 
         */
        public Celula getVert() {
            return vertice;
        }

        /**
         * Método que retorna o peso atual da aresta.
         * @return 
         */
        public int getPeso() {
            return peso;
        }

        /**
         * Método que seta um novo peso para um objeto aresta.
         * @param peso 
         */
        public void setPeso(int peso) {
            this.peso = peso;
        }
    }
}
