/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorRMI;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import model.Rotas;
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
         trajetos.forEach(u -> System.out.println(u.getOrigem() + "-" + u.getDestino() + "-" + u.getCompanhia()));
         System.out.println(servico.consultarQuantidadeDePassagens("Terezina","Natal"));
         servico.mudarQuantidadeDePassagens("Terezina","Natal",15);
         System.out.println(servico.consultarQuantidadeDePassagens("Terezina","Natal"));
     }
    
}
