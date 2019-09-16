/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appuser.controller;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Samuel Vitorio Lima , Gabriel Sá e Daniel
 */
public class ControllerView {

    //private static Servico servico1 = null;
    List<String> rotas;

    /**
     * Classe para controle da view
     */
    public ControllerView() {
        rotas = new ArrayList();
        //String localizacao1 = "//192.168.25.9/1099";
        //servico1 = (Servico) Naming.lookup(localizacao1);
    }
    
//    /**
//     * Método para pegar cidades no servidor
//     *
//     *@throws java.rmi.RemoteException
//     * @return
//     */
//    public List<Rotas> lerRotas() throws RemoteException{
//        return servico1.lerRotas();
//    }

    /**
     * Método que verifica se o roteiro escolhido pelo usuário está disponível
     *
     * @return
     * @throws java.rmi.RemoteException
     */
    public boolean checarRota() throws RemoteException {
//        int i[] = servico1.verificarTrajeto(rotas);
//        if (i.equals(null)) {
//            return true;
//        }
        return false;
    }

    /**
     * Método para salvar na lista de rotas os trechos escolhidos pelo usuário
     *
     * @param cidade1
     * @param cidade2
     * @param companhia
     */
    public void salvarLista(String cidade1, String cidade2, String companhia) {
        rotas.add(cidade1 + ";" + cidade2 + ";" + companhia);
    }

    /**
     * Método para buscar todas as possibilidades de rotas apartir de uma origem
     * e destino.
     *
     * @param origem
     * @param destino
     * @return
     * @throws java.rmi.RemoteException
     */
    public List<String> buscarRotas(String origem, String destino) throws RemoteException {
//        return servico1.buscarRotas(origem, destino);
        return null;
    }
}
