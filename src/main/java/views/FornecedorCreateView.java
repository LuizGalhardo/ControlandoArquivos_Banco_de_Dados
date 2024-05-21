package views;


import bancodados.FornecedorDAO;
import models.Fornecedor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Luiz Galhardo
 */
public class FornecedorCreateView extends javax.swing.JFrame {

    private static FornecedorCreateView cadFornecedorUnico;

    public FornecedorCreateView() {
        initComponents();
    }

    public static FornecedorCreateView getCadFornecedor() {
        if (cadFornecedorUnico == null) {
            cadFornecedorUnico = new FornecedorCreateView();
        }
        return cadFornecedorUnico;
    }

    private void setEmptyValues() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
    }

    private String fornecedorToText(Fornecedor fornecedor) {
        return fornecedor.getRazaoSocial() + ";"
                + fornecedor.getEmail() + ";"
                + fornecedor.getTelefone() + ";"
                + fornecedor.getCnpj();
    }

    private void adicionarFornecedor(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_adicionarFornecedor
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setRazaoSocial(jTextField1.getText());
        fornecedor.setEmail(jTextField2.getText());
        fornecedor.setTelefone(jTextField3.getText());
        fornecedor.setCnpj(jTextField4.getText());

        if (fornecedor.getRazaoSocial().isEmpty() || fornecedor.getEmail().isEmpty() || fornecedor.getTelefone().isEmpty()
                || fornecedor.getCnpj().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos vazios",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fornecedor != null) {
            FornecedorDAO dao = new FornecedorDAO();
            dao.CriaConexao();
            dao.Inserir(fornecedor);
            dao.FechaConexao();
            JOptionPane.showMessageDialog(
                    null,
                    "Fornecedor cadastrado com sucesso",
                    "Confirmação de cadastro",
                    3);
            setEmptyValues();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "CPF já cadastrado!",
                    "Erro no cadastro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_adicionarFornecedor

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText(" CADASTRO FORNECEDOR");

        jLabel2.setText("Razão Social");

        jLabel3.setText("Email");

        jLabel4.setText("Telefone");

        jLabel5.setText("CNPJ");

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(168, 168, 168)
                        .addComponent(jLabel3))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jTextField2)
                            .addComponent(jTextField4)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int respSair = JOptionPane.showConfirmDialog(
                null,
                "Deseja realmente voltar?",
                "Operação de volta",
                JOptionPane.YES_NO_OPTION);

        if (respSair == 0) {
            setEmptyValues();
            dispose();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setRazaoSocial(jTextField1.getText());
        fornecedor.setEmail(jTextField2.getText());
        fornecedor.setTelefone(jTextField3.getText());
        fornecedor.setCnpj(jTextField4.getText());

        if (fornecedor.getRazaoSocial().isEmpty() || fornecedor.getEmail().isEmpty() || fornecedor.getTelefone().isEmpty()
                || fornecedor.getCnpj().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos.", "Campos vazios",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (fornecedor != null) {
            FornecedorDAO dao = new FornecedorDAO();
            dao.CriaConexao();
            dao.Inserir(fornecedor);
            dao.FechaConexao();

            JOptionPane.showMessageDialog(
                    null,
                    "Fornecedor cadastrado com sucesso",
                    "Confirmação de cadastro",
                    3);
            setEmptyValues();
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "CPF já cadastrado!",
                    "Erro no cadastro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}