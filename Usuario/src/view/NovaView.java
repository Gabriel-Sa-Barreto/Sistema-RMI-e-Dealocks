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
/**
 *
 * @author lablenda2
 */
public class NovaView extends javax.swing.JFrame {

    /**
     * Atributo que realiza conexão com o servidor.
     */
    private Cliente cliente;
    
    /**
     * Creates new form NovaView
     */
    public NovaView() throws IOException {
        initComponents();
        new ControllerView();
        List<String> trajeto = ControllerView.lerRotas();
        for (Iterator<String> iter = trajeto.iterator(); ((Iterator<String>) iter).hasNext();) {
           String add = iter.next();
           boxOrigem.addItem(add);
           boxDestino.addItem(add);
        }
        cliente = new Cliente("10.0.0.102", 1885);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        telaUser = new javax.swing.JPanel();
        inicio = new javax.swing.JPanel();
        titulo = new javax.swing.JLabel();
        subTitulo = new javax.swing.JLabel();
        labelOrigem = new javax.swing.JLabel();
        boxOrigem = new javax.swing.JComboBox<>();
        labelDestino = new javax.swing.JLabel();
        boxDestino = new javax.swing.JComboBox<>();
        buttonConfirmar = new javax.swing.JButton();
        escolherRota = new javax.swing.JPanel();
        titulo1 = new javax.swing.JLabel();
        buttonComprar = new javax.swing.JButton();
        buttonTam = new javax.swing.JButton();
        subTitulo1 = new javax.swing.JLabel();
        buttonGol = new javax.swing.JButton();
        buttonAzul = new javax.swing.JButton();
        labelIndisponivel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lista = new javax.swing.JList<>();
        confirmacao = new javax.swing.JPanel();
        labelConfirmacao = new javax.swing.JLabel();
        buttonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 171, Short.MAX_VALUE)
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

        subTitulo1.setText("Bilhete Compartilhado");

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
        labelIndisponivel.setEnabled(false);

        lista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane2.setViewportView(lista);

        javax.swing.GroupLayout escolherRotaLayout = new javax.swing.GroupLayout(escolherRota);
        escolherRota.setLayout(escolherRotaLayout);
        escolherRotaLayout.setHorizontalGroup(
            escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escolherRotaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(titulo1)
                .addGap(279, 279, 279))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, escolherRotaLayout.createSequentialGroup()
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(escolherRotaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelIndisponivel, javax.swing.GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
                        .addGap(86, 86, 86))
                    .addGroup(escolherRotaLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(buttonGol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonTam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonAzul, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
            .addGroup(escolherRotaLayout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(subTitulo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        escolherRotaLayout.setVerticalGroup(
            escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(escolherRotaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(subTitulo1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(escolherRotaLayout.createSequentialGroup()
                        .addComponent(buttonTam)
                        .addGap(18, 18, 18)
                        .addComponent(buttonGol)
                        .addGap(18, 18, 18)
                        .addComponent(buttonAzul))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(escolherRotaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelIndisponivel, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonComprar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        telaUser.add(escolherRota, "escolherRota");

        labelConfirmacao.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        labelConfirmacao.setText("Passagem Comprada");

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
                .addContainerGap()
                .addComponent(buttonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(405, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, confirmacaoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );
        confirmacaoLayout.setVerticalGroup(
            confirmacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(confirmacaoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(labelConfirmacao, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        telaUser.add(confirmacao, "confirmação");

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

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        try {

            String origem = (String) boxOrigem.getSelectedItem();
            String destino = (String) boxDestino.getSelectedItem();
            String pacote = "1" + ";" + origem + ";" + destino;
            //enviar pacote de origem e destino para o servidor geral.
            cliente.conexao(); //se conecta ao servidor
            cliente.executa();
            ControllerRede.enviarDado(cliente.getCliente(),pacote);
            while(ControllerRotas.isHasNewRoutes());
            //somente continua execução depois do recebimento de todas as rotas.

            List<String> rotas = ControllerRotas.getRotas();

            //pegar os trechos das rotas enviadas pelo servidor
            for (Iterator<String> iter = rotas.iterator(); ((Iterator<String>) iter).hasNext();) {
                String rota = iter.next(); //pega uma rota
                String split[] = rota.split("-"); //realiza o split pelo -
                for(int j = 0 ; j < split.length; j++){
                    //pegar o trajeto saida/chegada
                    if(j != (split.length -1)){
                        String trajeto = "Saida: " + split[j] + "/Chegada: " + split[j+1];
                        if(!listaRotas.contains(trajeto)) //verifica se já contem na lista, senão adiciona
                        listaRotas.addElement(trajeto); //evita repetição de trecho
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

    private void buttonComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonComprarActionPerformed

    }//GEN-LAST:event_buttonComprarActionPerformed

    private void buttonTamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTamActionPerformed
        String companhia = "Tam";
        int index = lista.getSelectedIndex();
        String rota = (String) listaRotas.elementAt(index);
        String split[] = rota.split("-");
        ControllerView.salvarLista(split[0], split[1], companhia);
    }//GEN-LAST:event_buttonTamActionPerformed

    private void buttonGolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGolActionPerformed

        String companhia = "Gol";
        int index = lista.getSelectedIndex();
        String rota = (String) listaRotas.elementAt(index);
        String split[] = rota.split("-");
        ControllerView.salvarLista(split[0], split[1], companhia);
    }//GEN-LAST:event_buttonGolActionPerformed

    private void buttonAzulActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAzulActionPerformed
        String companhia = "Azul";
        int index = lista.getSelectedIndex();
        String rota = (String) listaRotas.elementAt(index);
        String split[] = rota.split("-");
        ControllerView.salvarLista(split[0], split[1], companhia);
    }//GEN-LAST:event_buttonAzulActionPerformed

    private void buttonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVoltarActionPerformed
        CardLayout c = (CardLayout) telaUser.getLayout();
        c.show(telaUser, "inicio");
    }//GEN-LAST:event_buttonVoltarActionPerformed
    
    DefaultListModel listaRotas = new DefaultListModel();
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
            java.util.logging.Logger.getLogger(NovaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NovaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NovaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NovaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new NovaView().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(NovaView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> boxDestino;
    private javax.swing.JComboBox<Object> boxOrigem;
    private javax.swing.JButton buttonAzul;
    private javax.swing.JButton buttonComprar;
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JButton buttonGol;
    private javax.swing.JButton buttonTam;
    private javax.swing.JButton buttonVoltar;
    private javax.swing.JPanel confirmacao;
    private javax.swing.JPanel escolherRota;
    private javax.swing.JPanel inicio;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelConfirmacao;
    private javax.swing.JLabel labelDestino;
    private javax.swing.JLabel labelIndisponivel;
    private javax.swing.JLabel labelOrigem;
    private javax.swing.JList<Object> lista;
    private javax.swing.JLabel subTitulo;
    private javax.swing.JLabel subTitulo1;
    private javax.swing.JPanel telaUser;
    private javax.swing.JLabel titulo;
    private javax.swing.JLabel titulo1;
    // End of variables declaration//GEN-END:variables
}
