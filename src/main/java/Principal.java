
import views.FornecedorCreateView;
import views.ProdutoCreateView;
import views.FornecedorEditView;
import views.ClienteList;
import views.ClienteCreateView;
import views.ClienteEditView;
import views.FornecedorListView;
import views.ProdutoEditView;
import views.ProdutoListView;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author Luiz Galhardo
 */
public class Principal extends javax.swing.JFrame {

    public Principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu3 = new javax.swing.JMenu();
        lblTitulo = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuView = new javax.swing.JMenu();
        itemViewCliente = new javax.swing.JMenuItem();
        itemViewForn = new javax.swing.JMenuItem();
        itemViewProd = new javax.swing.JMenuItem();
        menuCreat = new javax.swing.JMenu();
        itemCreatCliente = new javax.swing.JMenuItem();
        itemCreatForn = new javax.swing.JMenuItem();
        itemCreatProd = new javax.swing.JMenuItem();
        menuConsExc = new javax.swing.JMenu();
        itemConsCliente = new javax.swing.JMenuItem();
        itemConsForn = new javax.swing.JMenuItem();
        itemConsProd = new javax.swing.JMenuItem();

        jMenu3.setText("jMenu3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTitulo.setText("GESTÃO DE ESTOQUE");

        menuView.setText("VIZUALIZAÇÃO");

        itemViewCliente.setText("VIZUALIZAR CLIENTE");
        itemViewCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemViewClienteActionPerformed(evt);
            }
        });
        menuView.add(itemViewCliente);

        itemViewForn.setText("VIZUALIZAR FORNECEDOR");
        itemViewForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemViewFornActionPerformed(evt);
            }
        });
        menuView.add(itemViewForn);

        itemViewProd.setText("VIZUALIZAR PRODUTO");
        itemViewProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemViewProdActionPerformed(evt);
            }
        });
        menuView.add(itemViewProd);

        jMenuBar1.add(menuView);

        menuCreat.setText("CADASTRO");

        itemCreatCliente.setText("CADASTRAR CLIENTE");
        itemCreatCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCreatClienteActionPerformed(evt);
            }
        });
        menuCreat.add(itemCreatCliente);

        itemCreatForn.setText("CADASTAR FORNECEDOR");
        itemCreatForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCreatFornActionPerformed(evt);
            }
        });
        menuCreat.add(itemCreatForn);

        itemCreatProd.setText("CADASTRAR PRODUTO");
        itemCreatProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCreatProdActionPerformed(evt);
            }
        });
        menuCreat.add(itemCreatProd);

        jMenuBar1.add(menuCreat);

        menuConsExc.setText("CONSULTAR OU EXCLUIR");

        itemConsCliente.setText("CONSULTAR OU EXCLUIR CLIENTE");
        itemConsCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConsClienteActionPerformed(evt);
            }
        });
        menuConsExc.add(itemConsCliente);

        itemConsForn.setText("CONSULTAR OU EXCLUIR FORNECEDOR");
        itemConsForn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConsFornActionPerformed(evt);
            }
        });
        menuConsExc.add(itemConsForn);

        itemConsProd.setText("CONSULTAR OU EXCLUIR PRODUTO");
        itemConsProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemConsProdActionPerformed(evt);
            }
        });
        menuConsExc.add(itemConsProd);

        jMenuBar1.add(menuConsExc);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(156, 156, 156))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(lblTitulo)
                .addContainerGap(287, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemConsClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsClienteActionPerformed
       abreEditCliente();
    }//GEN-LAST:event_itemConsClienteActionPerformed

    private void itemConsProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsProdActionPerformed
        abreEditProd();
    }//GEN-LAST:event_itemConsProdActionPerformed

    private void itemCreatClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCreatClienteActionPerformed
       abreCadCliente();
    }//GEN-LAST:event_itemCreatClienteActionPerformed

    private void itemCreatFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCreatFornActionPerformed
        abreCadForn();
    }//GEN-LAST:event_itemCreatFornActionPerformed

    private void itemCreatProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCreatProdActionPerformed
        abreCadProd();
    }//GEN-LAST:event_itemCreatProdActionPerformed

    private void itemConsFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemConsFornActionPerformed
        abreEditForn();
    }//GEN-LAST:event_itemConsFornActionPerformed

    private void itemViewClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemViewClienteActionPerformed
        abreListaCliente();
    }//GEN-LAST:event_itemViewClienteActionPerformed

    private void itemViewFornActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemViewFornActionPerformed
        abreListaForn();
    }//GEN-LAST:event_itemViewFornActionPerformed

    private void itemViewProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemViewProdActionPerformed
       abreListaProd();
    }//GEN-LAST:event_itemViewProdActionPerformed

    
    public void abreListaCliente() {
        ClienteList.getClienteList().setVisible(true);
    }
    
    public void abreListaForn() {
        FornecedorListView.getFornecedorListView().setVisible(true);
    }
    
    public void abreListaProd() {
        ProdutoListView.getProdutoListView().setVisible(true);
    }
    
    public void abreEditCliente() {
        ClienteEditView.getClienteEditOrDelete().setVisible(true);
    }
    
    public void abreEditProd() {
        ProdutoEditView.getProdutoEditOrDelete().setVisible(true);
    }
    
    public void abreEditForn() {
        FornecedorEditView.getFornecedorEditOrDelete().setVisible(true);
    }

    public void abreCadCliente(){
        ClienteCreateView.getCadCliente().setVisible(true);
    }
    
    public void abreCadForn() {
        FornecedorCreateView.getCadFornecedor().setVisible(true);
    }
    
    public void abreCadProd() {
        ProdutoCreateView.getCadProduto().setVisible(true);
    }

    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemConsCliente;
    private javax.swing.JMenuItem itemConsForn;
    private javax.swing.JMenuItem itemConsProd;
    private javax.swing.JMenuItem itemCreatCliente;
    private javax.swing.JMenuItem itemCreatForn;
    private javax.swing.JMenuItem itemCreatProd;
    private javax.swing.JMenuItem itemViewCliente;
    private javax.swing.JMenuItem itemViewForn;
    private javax.swing.JMenuItem itemViewProd;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JMenu menuConsExc;
    private javax.swing.JMenu menuCreat;
    private javax.swing.JMenu menuView;
    // End of variables declaration//GEN-END:variables
}
