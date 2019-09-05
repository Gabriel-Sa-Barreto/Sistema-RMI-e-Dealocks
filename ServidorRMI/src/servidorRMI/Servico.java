/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorRMI;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Rotas;
/**
 *
 * @author lsjsa
 */
public interface Servico {
    /**
     * Metodo que pega todas as rotas que estao no arquivo
     * @return
     * @throws RemoteException 
     */
    public List<Rotas> lerRotas() throws RemoteException , IOException;
    /**
     * Metodo que altera a quantidade de passagens em um trajeto
     * @param origem
     * @param destino
     * @param quantidade
     * @throws RemoteException 
     */
    public void mudarQuantidadeDePassagens(String origem, String destino, int quantidade) throws RemoteException;
    /**
     * Metodo que consulta a quantidade de passagens em um trajeto
     * @param origem
     * @param destino
     * @return
     * @throws RemoteException 
     */
    public int consultarQuantidadeDePassagens(String origem, String destino) throws RemoteException;
    /**
     * Metodo que pega todas as rotas
     * @return
     * @throws RemoteException 
     */
    public List<Rotas> getRotas() throws RemoteException;
}
