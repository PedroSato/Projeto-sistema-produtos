/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Dimension;
import java.util.List;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.bean.Produto;
import services.ServiceProduto;

/**
 *
 * @author Pedro
 */
public class ConsultaProduto extends javax.swing.JInternalFrame {

    /**
     * Creates new form ConsultaProduto
     */
    public ConsultaProduto() {
        initComponents();
    }

    public void openFrameInCenter(JInternalFrame jif) {

        Dimension desktopSize = this.getParent().getSize();

        Dimension jInternalFrameSize = jif.getSize();

        int width = (desktopSize.width - jInternalFrameSize.width) / 2;
        int height = (desktopSize.height - jInternalFrameSize.height) / 2;

        jif.setLocation(width, height);

        jif.setVisible(true);

    }

    private void pesquisarProduto() {
        List<Produto> resultado = ServiceProduto.buscarProduto(pesquisarText.getText());

        DefaultTableModel model = (DefaultTableModel) tableConsultaProduto.getModel();

        model.setRowCount(0);

        if (resultado != null && resultado.size() > 0) {

            for (int i = 0; i < resultado.size(); i++) {

                Produto produto = resultado.get(i);

                if (produto != null) {

                    Object[] row = new Object[4];

                    row[0] = produto.getNome();
                    row[1] = produto.getFabricante();
                    row[2] = produto.getEstoque();
                    row[3] = produto.getPreco();

                    model.addRow(row);

                }

            }

        } else {

            JOptionPane.showMessageDialog(rootPane,
                    "Não há produtos para exibição",
                    "Não há dados",
                    JOptionPane.ERROR_MESSAGE);

        }
    }

    private void alterar() {

        int row = tableConsultaProduto.getSelectedRow();

        List<Produto> resultado = ServiceProduto.buscarProduto((String) tableConsultaProduto.getValueAt(row, 0));

        Produto produtoBusca = resultado.get(0);
        produtoBusca.setAtivo(true);

        Produto produto = ServiceProduto.obterProduto(produtoBusca);

        if (produto != null) {

            CadastroProduto cadastroProduto = new CadastroProduto(true);

            this.getParent().add(cadastroProduto);

            this.openFrameInCenter(cadastroProduto);

            cadastroProduto.toFront();

        } else {

            JOptionPane.showMessageDialog(rootPane, "Não foi possível localizar este produto para edição",
                    "Erro", JOptionPane.ERROR_MESSAGE);

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pesquisarText = new javax.swing.JTextField();
        pesquisarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableConsultaProduto = new javax.swing.JTable();
        excluirBtn = new javax.swing.JButton();
        editarBtn = new javax.swing.JButton();
        voltarBtn = new javax.swing.JButton();

        jLabel1.setText("Pesquisar por nome do produto");

        pesquisarBtn.setText("Pesquisar");
        pesquisarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarBtnActionPerformed(evt);
            }
        });

        tableConsultaProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Fabricante", " Quantidade em Estoque", "Preço Unitário"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableConsultaProduto);

        excluirBtn.setText("Excluir");

        editarBtn.setText("Editar");
        editarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarBtnActionPerformed(evt);
            }
        });

        voltarBtn.setText("Voltar");
        voltarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisarText, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pesquisarBtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(voltarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(excluirBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(pesquisarText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisarBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(excluirBtn)
                    .addComponent(editarBtn)
                    .addComponent(voltarBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void voltarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarBtnActionPerformed
        this.dispose();// TODO add your handling code here:
    }//GEN-LAST:event_voltarBtnActionPerformed

    private void pesquisarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarBtnActionPerformed
        pesquisarProduto();        // TODO add your handling code here:
    }//GEN-LAST:event_pesquisarBtnActionPerformed

    private void editarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarBtnActionPerformed
        alterar();        // TODO add your handling code here:
    }//GEN-LAST:event_editarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton editarBtn;
    private javax.swing.JButton excluirBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pesquisarBtn;
    private javax.swing.JTextField pesquisarText;
    private javax.swing.JTable tableConsultaProduto;
    private javax.swing.JButton voltarBtn;
    // End of variables declaration//GEN-END:variables
}
