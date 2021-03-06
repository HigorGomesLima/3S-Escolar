/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import objetos.Aluno;
import objetos.Tipo;
import sistema.Agenda;

/**
 *
 * @author cat-joao
 */
public class Principal extends javax.swing.JFrame {

    Agenda acesso;
    public Principal() {
        initComponents();
        acesso = new Agenda();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        bCriarTurma = new javax.swing.JButton();
        bCSVAluno = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        botaoPB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bCriarTurma.setText("Adicionar e Criar Turma");
        bCriarTurma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bCriarTurmaMouseClicked(evt);
            }
        });

        bCSVAluno.setText("Gerar Aluno CSV");
        bCSVAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bCSVAlunoMouseClicked(evt);
            }
        });

        jButton1.setText("Gerar PDF");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        botaoPB.setText("Prova Brasil");
        botaoPB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bCriarTurma)
                    .addComponent(bCSVAluno)
                    .addComponent(jButton1)
                    .addComponent(botaoPB))
                .addGap(0, 228, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(bCriarTurma)
                .addGap(18, 18, 18)
                .addComponent(bCSVAluno)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoPB)
                .addContainerGap(429, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bCSVAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bCSVAlunoMouseClicked
        try {
            acesso.criarCSV("ListaAlunos.csv");
        } catch (SQLException | IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_bCSVAlunoMouseClicked

    private void bCriarTurmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bCriarTurmaMouseClicked
        JFrame jf = new CriarTurma(acesso);
        jf.setVisible(true);
        jf.show();
    }//GEN-LAST:event_bCriarTurmaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
//            acesso.criarPDF("60012019");
//            acesso.criarPDF("60022019");
//            acesso.criarPDF("60032019");
//            acesso.criarPDF("60042019");
//           acesso.criarPDF("70012019");
//            acesso.criarPDF("70022019");
//            acesso.criarPDF("70032019");
//            acesso.criarPDF("70042019");
//            acesso.criarPDF("80012019");
//            acesso.criarPDF("80022019");
//            acesso.criarPDF("80032019");
//            acesso.criarPDF("80042019");
//            acesso.criarPDF("90012019");
//            acesso.criarPDF("90022019");
            acesso.criarPDF("90032019");
//           acesso.criarPDF("90042019");
//           acesso.criarPDF("90052019");
        //  acesso.criarPDFMedio("10012019");
         //  acesso.criarPDFMedio("10022019");
         // acesso.criarPDFMedio("10032019");
          //  acesso.criarPDFMedio("10042019");
          // acesso.criarPDFMedio("10052019");
         //  acesso.criarPDFMedio("10062019");
          // acesso.criarPDFMedio("20012019");
          //  acesso.criarPDFMedio("20022019");
          // acesso.criarPDFMedio("20032019");
          // acesso.criarPDFMedio("20042019");
          //  acesso.criarPDFMedio("20052019");
          // acesso.criarPDFMedio("20062019");
           // acesso.criarPDFMedio("30012019");
          //  acesso.criarPDFMedio("30022019");
          // acesso.criarPDFMedio("30032019");
          //  acesso.criarPDFMedio("30042019");
           // acesso.criarPDFMedio("30052019");
            
        } catch (DocumentException | IOException | SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void botaoPBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPBActionPerformed
        try {
        JFileChooser fc = new JFileChooser();
        fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(new FileNameExtensionFilter(".csv","csv"));
        fc.showOpenDialog(this);
        String turma = (String)JOptionPane.showInputDialog(null,"Selecione a turma","Seleção",JOptionPane.QUESTION_MESSAGE,null,(new String[]{"30012019","30022019","30032019","30042019","30052019","90012019","90022019","90032019","90042019","90052019"}),"30012019");
        String materia = (String)JOptionPane.showInputDialog(null,"Selecione a materia","Seleção",JOptionPane.QUESTION_MESSAGE,null,(new String[]{"portugues","matematica"}),"portugues");
        acesso.corrigirProvaBrasil(fc.getSelectedFiles(), turma,materia);
        } catch (IOException | SQLException ex) {
            Logger.getLogger(RealizarCorrecao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botaoPBActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bCSVAluno;
    private javax.swing.JButton bCriarTurma;
    private javax.swing.JButton botaoPB;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
