/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Cliente.Cliente;
import ControllerRede.ControllerRede;
import ControllerRotas.ControllerRotas;
import controller.ControllerView;
import java.awt.CardLayout;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Samuel Vitorio Lima , Gabriel Sá e Daniel
 */
public class Inicio extends javax.swing.JFrame {

    /**
     * Atributo que realiza conexão com o servidor.
     */
    private Cliente cliente;

    /**
     * Creates new form Inicio
     */
    public Inicio() throws IOException {
        initComponents();
        cont = new ControllerView();
        List<String> trajeto = cont.lerRotas();
        new ControllerRotas();
        for (Iterator<String> iter = trajeto.iterator(); ((Iterator<String>) iter).hasNext();) {
            String add = iter.next();
            boxOrigem.addItem(add);
            boxDestino.addItem(add);
        }
        cliente = new Cliente("172.16.201.60", 1885);
        cliente.conexao();
        cliente.executa();
        ControllerRede.enviarDado(cliente.getCliente(), "4;verificar");
        //espera pela confirmação dos serviços que estão disponíveis.
        while (true) {
            boolean verificacao = ControllerRotas.isHasServiceFailed();
            System.out.println("Esperando Serviços!");
            if (verificacao == false) {
                break;
            }
        }
        
        if(!ControllerRotas.getServicoFailed().isEmpty()){
            //pega o nome dos serviços que estão indisponíveis.
            for (Iterator<String> iter = ControllerRotas.getServicoFailed().iterator(); ((Iterator<String>) iter).hasNext();) {
                JOptionPane.showMessageDialog(null,"Serviço da empresa " + iter.next() + " pode estar indisponível!!");
            }  
            ControllerRotas.getServicoFailed().clear();
            ControllerRotas.setHasNewTrechoFailed(true);
        }    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        telaUser = new javax.swing.JPanel();
        inicio = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        subTitulo = new javax.swing.JLabel();
        labelOrigem = new javax.swing.JLabel();
        boxOrigem = new javax.swing.JComboBox<Object>();
        labelDestino = new javax.swing.JLabel();
        boxDestino = new javax.swing.JComboBox<Object>();
        buttonConfirmar = new javax.swing.JButton();
        escolherRota = new javax.swing.JPanel();
        titulo1 = new javax.swing.JLabel();
        buttonComprar = new javax.swing.JButton();
        buttonTam = new javax.swing.JButton();
        buttonGol = new javax.swing.JButton();
        buttonAzul = new javax.swing.JButton();
        labelIndisponivel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<Object>();
        jScrollPane3 = new javax.swing.JScrollPane();
        escolhidos = new javax.swing.JList<Object>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        VoltarTelaInicial = new javax.swing.JButton();
        buttonCancelar = new javax.swing.JButton();
        confirmacao = new javax.swing.JPanel();
        labelConfirmacao = new javax.swing.JLabel();
        buttonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio");

        telaUser.setLayout(new java.awt.CardLayout());

        titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titulo.setText("BiCom");

        subTitulo.setText("Bilhete Compartilhado");

        labelOrigem.setText("Origem");

        boxOrigem.setPreferredSize(new java.awt.Dimension(75, 26));

        labelDestino.setText("Destino");

        boxDestino.setPreferredSize(new java.awt.Dimension(75, 26));

        buttonConfirmar.setText("Confirmar");
        buttonConfirmar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout inicioLayout = new javax.swing.GroupLayout(inicio);
        inicio.setLayout(inicioLayout);
        inicioLayout.setHorizontalGroup(
            inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioLayout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(boxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addComponent(boxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102))
            .addGroup(inicioLayout.createSequentialGroup()
                .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inicioLayout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(titulo))
                    .addGroup(inicioLayout.createSequentialGroup()
                        .addGap(229, 229, 229)
                        .addComponent(subTitulo))
                    .addGroup(inicioLayout.createSequentialGroup()
                        .addGap(257, 257, 257)
                        .addComponent(buttonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(inicioLayout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(labelOrigem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelDestino)
                .addGap(145, 145, 145))
        );
        inicioLayout.setVerticalGroup(
            inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOrigem)
                    .addComponent(labelDestino))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(inicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(boxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addComponent(buttonConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );

        telaUser.add(inicio, "inicio");

        titulo1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        titulo1.setText("BiCom");

        buttonComprar.setText("Comprar");
        buttonComprar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonComprarActionPerformed(evt);
            }
        });

        buttonTam.setText("Tam");
        buttonTam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTamActionPerformed(evt);
            }
        });

        buttonGol.setText("Gol");
        buttonGol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGolActionPerformed(evt);
            }
        });

        buttonAzul.setText("Azul");
        buttonAzul.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAzulActionPerformed(evt);
            }
        });

        labelIndisponivel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelIndisponivel.setText("Roteiro Indiponível");

        lista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(lista);

        escolhidos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane3.setViewportView(escolhidos);

        jLabel1.setText("Trechos Escolhidos");

        jLabel2.setText("Escolha seu trajeto:");

        VoltarTelaInicial.setText("Voltar");
        VoltarTelaInicial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VoltarTelaInicialActionPerformed(evt);
            }
        });

        buttonCancelar.setText("Cancelar");
        buttonCancelar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        buttonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout escolherRotaLayout = new javax.swing.GroupLayout(escolherRota);
        escolherRota.setLayout(escolherRotaLayout);
        escolherRotaLayout.setHorizontalGroup(
            escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escolherRotaLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(escolherRotaLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(81, 81, 81)
                        .addComponent(titulo1)
                        .addGap(84, 84, 84)
                        .addComponent(jLabel1))
                    .addGroup(escolherRotaLayout.createSequentialGroup()
                        .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(escolherRotaLayout.createSequentialGroup()
                                .addComponent(buttonTam, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonGol, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonAzul, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(escolherRotaLayout.createSequentialGroup()
                                .addComponent(buttonComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(16, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escolherRotaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelIndisponivel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(104, 104, 104)
                .addComponent(VoltarTelaInicial)
                .addGap(55, 55, 55))
        );
        escolherRotaLayout.setVerticalGroup(
            escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escolherRotaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonTam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAzul, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonGol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelIndisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(VoltarTelaInicial))
                .addContainerGap())
        );

        telaUser.add(escolherRota, "escolherRota");

        labelConfirmacao.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelConfirmacao.setText("Compra Realizada com Sucesso!!");

        buttonVoltar.setText("Comprar outra passagem");
        buttonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout confirmacaoLayout = new javax.swing.GroupLayout(confirmacao);
        confirmacao.setLayout(confirmacaoLayout);
        confirmacaoLayout.setHorizontalGroup(
            confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacaoLayout.createSequentialGroup()
                .addGroup(confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(confirmacaoLayout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addComponent(buttonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(confirmacaoLayout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(labelConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(188, Short.MAX_VALUE))
        );
        confirmacaoLayout.setVerticalGroup(
            confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacaoLayout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addComponent(labelConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        telaUser.add(confirmacao, "confirmacao");
        confirmacao.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(telaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(telaUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que envia a origem e destino da viagem que o usuário escolheu com
     * o acionamento do botão.
     *
     */
    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        try {
            labelIndisponivel.setVisible(false);
            String origem = (String) boxOrigem.getSelectedItem();
            String destino = (String) boxDestino.getSelectedItem();
            String pacote = "1" + ";" + origem + ";" + destino;
            //enviar pacote de origem e destino para o servidor geral.
            cliente.conexao(); //se conecta ao servidor
            cliente.executa();
            ControllerRede.enviarDado(cliente.getCliente(), pacote);
            while (true) { //espera pelo recebimento de todas as rotas.
                boolean verificacao = ControllerRotas.isHasNewRoutes();
                System.out.println(verificacao);
                if (verificacao == false) {
                    break;
                }
            }
            //somente continua execução depois do recebimento de todas as rotas.            
            List<String> rotas = ControllerRotas.getRotas();

            //pegar os trechos das rotas enviadas pelo servidor
            for (Iterator<String> iter = rotas.iterator(); ((Iterator<String>) iter).hasNext();) {
                String rota = iter.next(); //pega uma rota
                String split[] = rota.split("-"); //realiza o split pelo -
                for (int j = 0; j < split.length; j++) {
                    //pegar o trajeto saida/chegada
                    if (j != (split.length - 1)) {
                        String trajeto = "Saida: " + split[j] + "/Chegada: " + split[j + 1];
                        if (!listaRotas.contains(trajeto)) //verifica se já contem na lista, senão adiciona
                        {
                            listaRotas.addElement(trajeto); //evita repetição de trecho
                        }
                    }
                }
            }
            lista.setModel(listaRotas);

        } catch (IOException ex) {
            System.out.println("Erro: de conexão com o servidor");
        }
        CardLayout c = (CardLayout) telaUser.getLayout();
        c.show(telaUser, "escolherRota");

    }//GEN-LAST:event_buttonConfirmarActionPerformed

    /**
     * Método que envia a lista com os trechos que o usuário escolheu após o
     * acionamento do botão.
     *
     */
    private void buttonComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonComprarActionPerformed
        try {
            //se conecta ao servidor
            cliente.conexao();
            cliente.executa();
            //pegar os trechos das rotas enviadas pelo servidor
            int laco = 0; //variavel para controlar o laco
            while (laco <= listaTrechos.size() - 1) {
                String informacao = (String) listaTrechos.get(laco); //pega os trajetos escolhidos
                String split1[] = informacao.split("/"); //separa a string pelo caracter "/"
                String split2[] = split1[0].trim().split(":"); //pegar a saida do trecho
                String saida = split2[1].substring(1); //variavel que guarda o inicio do trajeto
                String split3[] = split1[1].trim().split(":"); // pegar a parte da string chegada e companhia
                String split4[] = split3[1].replace("(", ";").replace(")", ";").split(";"); // separar companhia e chegada
                String destino = split4[0].substring(1, split4[0].length() - 1);
                //opcode;valorDeFinalizado;saída;destino;empresa
                String pacote = "2" + ";" + "1" + ";" + saida + ";" + destino + ";" + split4[1];
                ControllerRede.enviarDado(cliente.getCliente(), pacote);
                laco++;
            }
            String pacote = "2" + ";" + "0" + ";" + "vazio" + ";" + "vazio" + ";" + "vazio" + ";";
            ControllerRede.enviarDado(cliente.getCliente(), pacote);
            while (true) { //espera pelo recebimento de todos os trechos.
                boolean verificacao = ControllerRotas.isHasNewTrechoFailed();
                System.out.println("Esperando Confirmação!");
                if (verificacao == false) {
                    break;
                }
            }
            if (ControllerRotas.getTrechosIndisponiveis().isEmpty()) {
                CardLayout c = (CardLayout) telaUser.getLayout();
                c.show(telaUser, "confirmacao");
                return;
            } else {
                String mensagem = "Trechos indisponiveis: \n";
                List<String> falhos = ControllerRotas.getTrechosIndisponiveis();
                for (Iterator<String> iter = falhos.iterator(); ((Iterator<String>) iter).hasNext();) {
                    //saída;destino;empresa;tipoDeFalha
                    String trajeto = iter.next();
                    String split[] = trajeto.split(";");
                    mensagem = mensagem + split[0] + "->" + split[1] + "/" + split[2] + " Falha: ";
                    if(split[3].equals("0")){
                        mensagem = mensagem + "falta de vagas\n";
                    }else if(split[3].equals("1")){
                        mensagem = mensagem + "empresa indisponível\n";
                    }
                }
                JOptionPane.showMessageDialog(null, mensagem);
                ControllerRotas.getTrechosIndisponiveis().clear();
                ControllerRotas.setHasNewTrechoFailed(true);
            }
        } catch (IOException ex) {
            Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonComprarActionPerformed

    /**
     * Método que envia o trecho e a companhia Tam.
     *
     */
    private void buttonTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTamActionPerformed
        String companhia = "Tam";
        int index = lista.getSelectedIndex();
        String rota = (String) listaRotas.elementAt(index);

        String split[] = rota.split("/");
        String colocar = split[0] + "/" + split[1] + " (" + companhia + ")";
        listaTrechos.addElement(colocar);
        ControllerView.salvarLista(split[0], split[1], companhia);
        escolhidos.setModel(listaTrechos);
    }//GEN-LAST:event_buttonTamActionPerformed

    /**
     * Método que envia o trecho e a companhia Gol.
     *
     */
    private void buttonGolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGolActionPerformed
        String companhia = "Gol";
        int index = lista.getSelectedIndex();
        String rota = (String) listaRotas.elementAt(index);

        String split[] = rota.split("/");
        String colocar = split[0] + "/" + split[1] + " (" + companhia + ")";
        listaTrechos.addElement(colocar);
        ControllerView.salvarLista(split[0], split[1], companhia);
        escolhidos.setModel(listaTrechos);
    }//GEN-LAST:event_buttonGolActionPerformed

    /**
     * Após ter realizado a comprar poder retornar para a tela inicial.
     *
     */
    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVoltarActionPerformed
        ControllerRotas.clearListRotas();
        listaRotas.clear();//limpando lista de rotas
        lista.setModel(listaRotas);
        listaTrechos.clear();//limpando lista de trechos escolhidos
        escolhidos.setModel(listaTrechos);
        CardLayout c = (CardLayout) telaUser.getLayout();
        c.show(telaUser, "inicio");
    }//GEN-LAST:event_buttonVoltarActionPerformed

    /**
     * Método que envia o trecho e a companhia Azul.
     *
     */
    private void buttonAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAzulActionPerformed
        String companhia = "Azul";
        int index = lista.getSelectedIndex();
        String rota = (String) listaRotas.elementAt(index);

        String split[] = rota.split("/");
        String colocar = split[0] + "/" + split[1] + " (" + companhia + ")";
        listaTrechos.addElement(colocar);
        ControllerView.salvarLista(split[0], split[1], companhia);
        escolhidos.setModel(listaTrechos);
    }//GEN-LAST:event_buttonAzulActionPerformed

    /**
     * Método para voltar pra tela anterior
     */
    private void VoltarTelaInicialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VoltarTelaInicialActionPerformed
        ControllerRotas.clearListRotas();
        listaRotas.clear();//limpando lista de rotas
        lista.setModel(listaRotas);
        listaTrechos.clear();//limpando lista de trechos escolhidos
        escolhidos.setModel(listaTrechos);
        CardLayout c = (CardLayout) telaUser.getLayout();
        c.show(telaUser, "inicio");
    }//GEN-LAST:event_VoltarTelaInicialActionPerformed

    /**
     * Metodo que retirar um trajeto escolhido pelo usuario
     *
     * @param evt
     */
    private void buttonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelarActionPerformed
        // TODO add your handling code here:
        int index = escolhidos.getSelectedIndex(); //pega o indice da tupla desejada
        listaTrechos.remove(index); //retirar da lista
        escolhidos.setModel(listaTrechos); //colocar na JList a lista atualizada
    }//GEN-LAST:event_buttonCancelarActionPerformed

    ControllerView cont;//instância do controller da view
    DefaultListModel listaRotas = new DefaultListModel();
    DefaultListModel listaTrechos = new DefaultListModel();

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Inicio().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VoltarTelaInicial;
    private javax.swing.JComboBox<Object> boxDestino;
    private javax.swing.JComboBox<Object> boxOrigem;
    private javax.swing.JButton buttonAzul;
    private javax.swing.JButton buttonCancelar;
    private javax.swing.JButton buttonComprar;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JButton buttonGol;
    private javax.swing.JButton buttonTam;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JPanel confirmacao;
    private javax.swing.JPanel escolherRota;
    private javax.swing.JList<Object> escolhidos;
    private javax.swing.JPanel inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelConfirmacao;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelIndisponivel;
    private javax.swing.JLabel labelOrigem;
    private javax.swing.JList<Object> lista;
    private javax.swing.JLabel subTitulo;
    private javax.swing.JPanel telaUser;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    // End of variables declaration//GEN-END:variables
}
