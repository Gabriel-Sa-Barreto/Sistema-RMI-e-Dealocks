/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorRMI;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Rotas;
import util.Grafo;
/**
 *
 * @author lsjsa
 */
public class ClienteRMI {
    
    private static Servico servico = null;
    private static List<Rotas> trajetos;
    
    public ClienteRMI() {
        try{
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            String localizacao = "//localhost/servico";
            servico = (Servico) Naming.lookup(localizacao);
        } catch(Exception ex){
            System.out.println("Erro" + ex.getMessage());
        }
    }
    
     public static void main(String[] args) throws IOException {
         ClienteRMI cliente = new ClienteRMI();
         trajetos = servico.lerRotas();
         Grafo grafo = new Grafo();
         //pega todas as rotas e coloca no grafo
         for(Iterator<Rotas> iter= trajetos.iterator();((Iterator<Rotas>) iter).hasNext();){
             Rotas verificar = iter.next();
             String origem = verificar.getOrigem(); //pega o destino do trajeto
             String destino = verificar.getDestino();//pega a origem do trajeto
             if(grafo.existeVertice(origem) == -1) //verifica se já existe o vertice pertencente a origem
                 grafo.addVertice(origem);
             if(grafo.existeVertice(destino) == -1) //verifica se já existe o vertice pertencente o destino
                 grafo.addVertice(destino);
             if(grafo.existeAresta(verificar.getOrigem(),verificar.getDestino()) == -1) //caso não exista uma aresta com esse destino e origem
                 grafo.addAresta(origem,destino,verificar.getQuantidade());//adiciona nova aresta
         }
         
         grafo.buscarCaminhos("Salvador", "Natal");
         /*System.out.println(servico.consultarQuantidadeDePassagens("Terezina","Natal"));
         servico.mudarQuantidadeDePassagens("Terezina","Natal",15);
         System.out.println(servico.consultarQuantidadeDePassagens("Terezina","Natal"));*/
     }
    
}
