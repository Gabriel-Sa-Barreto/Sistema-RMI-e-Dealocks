/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorRMI;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
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
    
    /**
     * Atributo que guardara os trajetos lidos no arquivo
     */
    private List<Rotas> trajetos = new ArrayList<>();
    
    public ServidorRMI() throws RemoteException {
        super();
    }
    
    /**
     * Metodo que pega todas as rotas que estao no arquivo
     * @return
     * @throws RemoteException , IOException
     */
    @Override
    public List<Rotas> lerRotas() throws RemoteException , IOException{
        String caminhoArquivo = "../Trajeto/trajeto.txt"; //diretorio onde esta o arquivo que armazena os trajetos
        //objeto responsavel por ler as linhas do arquivo
        BufferedReader read = new BufferedReader(new FileReader(caminhoArquivo)); 
        String linha = "";
        //faz a leitura da primeira linha do arquivo que contem o nome da companhia
        String companhia;
        companhia = read.readLine();
        //leitura dos trajetos
        while((linha = read.readLine()) != null){
            //dados separados por "-" 
            String split[] = linha.split("-");
            //criacao de uma rota
            Rotas rota =  new Rotas(split[0],split[1],Integer.valueOf(split[2]),companhia);
            //adiciona na lista
            trajetos.add(rota);
        }
        //retorna a lista
        read.close();
        return trajetos;
    }
    
     /**
     * Metodo que altera a quantidade de passagens em um trajeto
     * @param origem
     * @param destino
     * @param quantidade
     * @throws RemoteException 
     */
    @Override
    public void mudarQuantidadeDePassagens(String origem, String destino, int quantidade) throws RemoteException {
        //for com iterados para andar na lista para a procura da rota especifica para alterar um dado do trajeto
        for(Iterator<Rotas> iter= trajetos.iterator();((Iterator<Rotas>) iter).hasNext();){
            Rotas verificar = iter.next();
            //if que procura a rota
            if(verificar.getOrigem().equals(origem) && verificar.getDestino().equals(destino)){
                verificar.setQuantidade(quantidade);//altera a quantidade de vagas disponiveis
            }
        }
        //atualizar o arquivo
        try {
            atualizar();
        } catch (IOException ex) {
            Logger.getLogger(ServidorRMI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Metodo que consulta a quantidade de passagens em um trajeto
     * @param origem
     * @param destino
     * @return
     * @throws RemoteException 
     */
    @Override
    public int consultarQuantidadeDePassagens(String origem, String destino) throws RemoteException {
        //for com iterados para andar na lista para a procura da rota especifica
        for(Iterator<Rotas> iter= trajetos.iterator();((Iterator<Rotas>) iter).hasNext();){
            Rotas verificar = iter.next();
            //if que procura a rota
            if(verificar.getOrigem().equals(origem) && verificar.getDestino().equals(destino)){
                return verificar.getQuantidade();//retorna a quantidade de vagas 
            }
        }
        return 0;
    }
    
    /**
     * Metodo que retorna a lista que já foi lida do arquivo
     * @return
     * @throws RemoteException 
     */
    @Override
    public List<Rotas> getRotas() throws RemoteException {
        return trajetos;
    }
    
    /**
     * Método que atualiza o arquivo
     * @throws IOException 
     */
    public void atualizar() throws IOException{
        //objeto responsavel por escrever as linhas do arquivo
        String caminhoArquivo = "../Trajeto/trajeto.txt"; //diretorio onde esta o arquivo que armazena os trajetos
        BufferedWriter write = new BufferedWriter(new FileWriter(caminhoArquivo,false));
        //padrao definido para o armazenamento da informacao no arquivo
        write.write(trajetos.get(0).getCompanhia() + "\n");
        int i = 0;
        //sobrescrever todos os trajetos com a atualizacao
        while(i < trajetos.size()){
            write.write(trajetos.get(i).getOrigem() + "-" + trajetos.get(i).getDestino() + "-" + trajetos.get(i).getQuantidade() + "\n");
            i++;
        }
        //fechar o buffer de escrita
        write.close();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            //implementação do servidor
            ServidorRMI servidor = new ServidorRMI();
            LocateRegistry.createRegistry(1099);
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            String localizacao = "//192.168.25.9/1099";
            //System.setProperty("java.rmi.server.hostname", "10.0.0.123");
            //System.out.println("Teste2");
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
