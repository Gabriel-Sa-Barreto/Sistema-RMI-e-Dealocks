/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorRMI;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Rotas;

/**
 * Classe resonsavel pelo Servidor RMI
 */

/**
 *
 * @author lsjsa
 */
public class ServidorRMI extends UnicastRemoteObject implements Servico {
    
    private List<Rotas> trajetos = new ArrayList<>();
    
    public ServidorRMI() throws RemoteException {
        super();
    }
    
    @Override
    public List<Rotas> lerRotas() throws RemoteException , IOException{
        String caminhoArquivo = "../Trajeto/trajeto.txt";
        //objeto responsavel por ler as linhas do arquivo
        BufferedReader read = new BufferedReader(new FileReader(caminhoArquivo));
        String linha = "";
        String companhia;
        companhia = read.readLine();
        while((linha = read.readLine()) != null){
            String split[] = linha.split("-");
            Rotas rota =  new Rotas(split[0],split[1],Integer.valueOf(split[2]),companhia);
            trajetos.add(rota);
        }
        return trajetos;
    }

    @Override
    public void mudarQuantidadeDePassagens(String origem, String destino, int quantidade) throws RemoteException {
        for(Iterator<Rotas> iter= trajetos.iterator();((Iterator<Rotas>) iter).hasNext();){
            Rotas verificar = iter.next();
            if(verificar.getOrigem().equals(origem) && verificar.getDestino().equals(destino)){
                verificar.setQuantidade(quantidade);
            }
        }
    }

    @Override
    public int consultarQuantidadeDePassagens(String origem, String destino) throws RemoteException {
        for(Iterator<Rotas> iter= trajetos.iterator();((Iterator<Rotas>) iter).hasNext();){
            Rotas verificar = iter.next();
            if(verificar.getOrigem().equals(origem) && verificar.getDestino().equals(destino)){
                return verificar.getQuantidade();
            }
        }
        return 0;
    }

    @Override
    public List<Rotas> getRotas() throws RemoteException {
        return trajetos;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            //implementação do servidor
            ServidorRMI servidor = new ServidorRMI();
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            String localizacao = "//localhost/servico";
            //Registra nosso servidor e o serviço que está sendo disponibilizado.
            //Caso seja feita alguma modificação ele já remove a antiga instância,
            //e registra novamente.
            Naming.rebind(localizacao, servidor);
        } catch (RemoteException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (MalformedURLException ex) {
            System.out.println("Erro de URL mal formada: " + ex.getMessage());
        }
    }
    
}
