/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 * Classe responsavel por guardar a rota
 */

/**
 *
 * @author lsjsa
 */
public class Rotas implements Serializable{
    
    /**
     * Atributo que armazena o local onde o trajeto começa
     */
    private String origem;
    /**
     * Atributo que armazena o destino do trajeto
     */
    private String destino;
    /**
     * Atributo que armazena a quantidade de passagens sobrando
     */
    private int quantidade;
    /**
     * Atributo que armazena a companhia responasel por esse trajeto
     */
    private String companhia;

    /**
     * Construtor da model Rotas
     * @param origem
     * @param destino
     * @param quantidade
     * @param companhia 
     */
    public Rotas(String origem, String destino, int quantidade, String companhia) {
        this.origem = origem;
        this.destino = destino;
        this.quantidade = quantidade;
        this.companhia = companhia;
    }

    /**
     * Metodo get do atributo origem
     * @return 
     */
    public String getOrigem() {
        return origem;
    }

    /**
     * Metodo get do atributo destino
     * @return 
     */
    public String getDestino() {
        return destino;
    }

    /**
     * Metodo get do atributo quantidade
     * @return 
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Metodo get do atributo companhia
     * @return 
     */
    public String getCompanhia() {
        return companhia;
    }

    /**
     * Metodo set do atributo quantidade
     * @param quantidade 
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
}
