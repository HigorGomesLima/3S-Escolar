package interfaces;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetos.Tipo;
import sistema.Agenda;

/**
 *
 * @author Higor
 */
public class CriarTipo extends javax.swing.JFrame {

    Agenda acesso;
    public CriarTipo() {
        initComponents();
        acesso = new Agenda();
        String ano = Calendar.getInstance().get(Calendar.YEAR)+"";
        this.caixaAno.setText(ano);
        try {
            this.caixaTurma.setListData(acesso.getTurmas(ano));
        } catch (SQLException ex) {
            Logger.getLogger(CriarTipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        caixaAno = new javax.swing.JTextField();
        caixaBimestre = new javax.swing.JComboBox<>();
        caixaMat = new javax.swing.JTextField();
        caixaLing1 = new javax.swing.JTextField();
        caixaNat = new javax.swing.JTextField();
        caixaHum = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        caixaTurma = new javax.swing.JList<>();
        criar = new javax.swing.JButton();
        caixaTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        caixaAno.setText("2019");
        caixaAno.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                caixaAnoCaretUpdate(evt);
            }
        });

        caixaBimestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        caixaMat.setText("0");
        caixaMat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                caixaMatCaretUpdate(evt);
            }
        });

        caixaLing1.setText("0");
        caixaLing1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                caixaLing1CaretUpdate(evt);
            }
        });

        caixaNat.setText("0");
        caixaNat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                caixaNatCaretUpdate(evt);
            }
        });

        caixaHum.setText("0");
        caixaHum.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                caixaHumCaretUpdate(evt);
            }
        });

        jScrollPane1.setViewportView(caixaTurma);

        criar.setText("Criar");
        criar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                criarActionPerformed(evt);
            }
        });

        caixaTotal.setText("70");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(caixaLing1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caixaMat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caixaNat, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(caixaHum, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(caixaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(caixaAno, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(caixaBimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(criar)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(caixaAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caixaBimestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(criar))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(caixaMat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(caixaLing1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(caixaNat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(caixaHum, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(caixaTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void criarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_criarActionPerformed

            Object[] listaTurma = this.caixaTurma.getSelectedValues();
            Tipo novo = new Tipo();
            novo.setAno(this.caixaAno.getText());
            novo.setBimestre((String) this.caixaBimestre.getSelectedItem());
            novo.setHumana(Integer.parseInt(this.caixaHum.getText()));
            novo.setLinguagem(Integer.parseInt(this.caixaLing1.getText()));
            novo.setMatematica(Integer.parseInt(this.caixaMat.getText()));
            novo.setNatureza(Integer.parseInt(this.caixaNat.getText()));
            novo.setTotal(Integer.parseInt(this.caixaTotal.getText()));
        try {
            acesso.criarTipo(novo, listaTurma);
        } catch (SQLException ex) {
            Logger.getLogger(CriarTipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_criarActionPerformed

    public void setTotal(){
        int valor = Integer.parseInt(this.caixaNat.getText()) + Integer.parseInt(this.caixaMat.getText()) +
                Integer.parseInt(this.caixaLing1.getText()) + Integer.parseInt(this.caixaHum.getText());
        this.caixaTotal.setText(valor+"");
    }
    private void caixaAnoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_caixaAnoCaretUpdate
        try {
            this.caixaTurma.setListData(acesso.getTurmas(this.caixaAno.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(CriarTipo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_caixaAnoCaretUpdate

    private void caixaLing1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_caixaLing1CaretUpdate
        if(!"".equals(this.caixaLing1.getText()))
            setTotal();
    }//GEN-LAST:event_caixaLing1CaretUpdate

    private void caixaMatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_caixaMatCaretUpdate
        if(!"".equals(this.caixaMat.getText()))
            setTotal();
    }//GEN-LAST:event_caixaMatCaretUpdate

    private void caixaNatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_caixaNatCaretUpdate
        if(!"".equals(this.caixaNat.getText()))
            setTotal();
    }//GEN-LAST:event_caixaNatCaretUpdate

    private void caixaHumCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_caixaHumCaretUpdate
        if(!"".equals(this.caixaHum.getText()))
            setTotal();
    }//GEN-LAST:event_caixaHumCaretUpdate
   
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
            java.util.logging.Logger.getLogger(CriarTipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CriarTipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CriarTipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CriarTipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CriarTipo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField caixaAno;
    private javax.swing.JComboBox<String> caixaBimestre;
    private javax.swing.JTextField caixaHum;
    private javax.swing.JTextField caixaLing1;
    private javax.swing.JTextField caixaMat;
    private javax.swing.JTextField caixaNat;
    private javax.swing.JLabel caixaTotal;
    private javax.swing.JList<String> caixaTurma;
    private javax.swing.JButton criar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
