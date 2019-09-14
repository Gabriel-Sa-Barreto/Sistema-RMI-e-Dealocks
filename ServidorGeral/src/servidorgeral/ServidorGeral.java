/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgeral;

import servidorRMI.Servico;
import java.io.IOException;
import java.rmi.Naming;
import java.util.Iterator;
import java.util.List;
import model.Rotas;
import model.Grafo;

/**
 *
 * @author lca
 */
public class ServidorGeral {
    
    /**
     * Atributo responsavel por poder acessar os servicos oferecidos pela companhia1
     */
    private static Servico servico1 = null;
    /**
     * Atributo responsavel por guardar os trajetos oferecidos pela companhia1
     */
    private static List<Rotas> trajetos1;
    /**
     * Atributo responsavel por poder acessar os servicos oferecidos pela companhia2
     */
    private static Servico servico2 = null;
    /**
     * Atributo responsavel por guardar os trajetos oferecidos pela companhia2
     */
    private static List<Rotas> trajetos2;
    /**
     * Atributo responsavel por poder acessar os servicos oferecidos pela companhia3
     */
    private static Servico servico3 = null;
    /**
     * Atributo responsavel por guardar os trajetos oferecidos pela companhia3
     */
    private static List<Rotas> trajetos3;
    
    public ServidorGeral() {
        try{
            //string que deve conter o endereco onde o serviço está sendo
            //disponibilizado e o nome do serviço
            //servico da companhia1
            String localizacao1 = "//192.168.25.9/1099";
            servico1 = (Servico) Naming.lookup(localizacao1);
            //servico da companhia2
            /*String localizacao2 = "//192.168.25.9/1099"; //mudarip
            servico2 = (Servico) Naming.lookup(localizacao2);
            //servico da companhia3
            String localizacao3 = "//192.168.25.9/1099"; //mudar ip
            servico2 = (Servico) Naming.lookup(localizacao3);*/
        } catch(Exception ex){
            System.out.println("Erro" + ex.getMessage());
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServidorGeral geral = new ServidorGeral();
        //ler rotas da companhia1
        trajetos1 = servico1.lerRotas();
        //ler rotas da companhia2
        //trajetos2 = servico2.lerRotas();
        //ler rotas da companhia3
        //trajetos3 = servico3.lerRotas();
        Grafo grafo = new Grafo();
        //pega todas as rotas e coloca no grafo
        for(Iterator<Rotas> iter= trajetos1.iterator();((Iterator<Rotas>) iter).hasNext();){
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
         
         grafo.buscarCaminhos("Salvador", "Natal");
         /*System.out.println(servico.consultarQuantidadeDePassagens("Terezina","Natal"));
         servico.mudarQuantidadeDePassagens("Terezina","Natal",15);
         System.out.println(servico.consultarQuantidadeDePassagens("Terezina","Natal"));*/
    }
    
}
