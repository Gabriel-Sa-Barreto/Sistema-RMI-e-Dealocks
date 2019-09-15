/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerPacote;

/**
 *
 * @author Gabriel Sá, Samuel Vitorio e Daniel
 */
public class ControllerPacotes {
    
    /**Método auxiliar na conversao de String para int
    * @author Samuel Vitorio Lima e Gabriel Sá Barreto
    * @param valor  String- receber a String a ser convertida.
    * @param padrao int - caso aconteça uma exceção um valor padrão que possa ser inserido.
    * @return int - retornar o inteiro.
    */    
    public static int strToInt(String valor, int padrao) {
        try {
            return Integer.valueOf(valor); // Para retornar um Integer, use Integer.parseInt
        } 
        catch (NumberFormatException e) {  // Se houver erro na conversão, retorna o valor padrão
            return padrao;
        }
    }  
}
