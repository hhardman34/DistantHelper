/*
 * ConfigJPanel.java
 */

package jrdesktop.server;

import jrdesktop.Commons;
import jrdesktop.main;
import jrdesktop.rmi.server.RMIServer;
import jrdesktop.utilities.FileUtility;
import jrdesktop.utilities.InetAdrUtility;

import javax.swing.*;
import java.util.regex.Pattern;

/**
 * @author Benbac
 */
public class ConfigJPanel extends javax.swing.JPanel {

    private static int lastConfigIndex = 0;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonOK;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JCheckBox jCheckBoxDefaultAdr;
    private javax.swing.JCheckBox jCheckBoxManualAdr;
    private javax.swing.JCheckBox jCheckBoxMultihomedEnabled;
    private javax.swing.JCheckBox jCheckBoxReverseConnection;
    private javax.swing.JCheckBox jCheckBoxSSLEnabled;
    private javax.swing.JComboBox jComboBoxConfig;
    private javax.swing.JComboBox jComboBoxLocalAdrs;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldHTTPPort;
    private javax.swing.JTextField jTextFieldPort;
    private javax.swing.JTextField jTextFieldUsername;
    /**
     * Creates new form ConfigJPanel
     */
    public ConfigJPanel() {
        initComponents();
        jComboBoxConfig.setSelectedIndex(lastConfigIndex);
        if (jCheckBoxMultihomedEnabled.isSelected()) {
            jCheckBoxMultihomedEnabledActionPerformed(null);
        } else {
            jCheckBoxManualAdrActionPerformed(null);
        }
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonOK = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jCheckBoxSSLEnabled = new javax.swing.JCheckBox();
        jCheckBoxReverseConnection = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldUsername = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxConfig = new javax.swing.JComboBox(FileUtility.getSideConfigFiles(Commons.serverSide));
        jPanel4 = new javax.swing.JPanel();
        jCheckBoxManualAdr = new javax.swing.JCheckBox();
        jCheckBoxMultihomedEnabled = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxLocalAdrs = new javax.swing.JComboBox(InetAdrUtility.getLocalIPAdresses());
        jCheckBoxDefaultAdr = new javax.swing.JCheckBox();
        jButtonRefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldPort = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldHTTPPort = new javax.swing.JTextField();

        jButtonOK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jrdesktop/images/ok.png"))); // NOI18N
        jButtonOK.setText("Apply");
        jButtonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOKActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Connection mode"));

        jCheckBoxSSLEnabled.setSelected(RMIServer.serverConfig.ssl_enabled);
        jCheckBoxSSLEnabled.setText("Secured connection");

        jCheckBoxReverseConnection.setSelected(RMIServer.serverConfig.reverseConnection);
        jCheckBoxReverseConnection.setText("Reverse connection");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel3Layout.createSequentialGroup()
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jCheckBoxSSLEnabled)
                                        .add(jCheckBoxReverseConnection))
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel3Layout.createSequentialGroup()
                                .add(jCheckBoxSSLEnabled)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jCheckBoxReverseConnection)
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Authentication"));

        jLabel3.setText("Username:");

        jTextFieldUsername.setText(RMIServer.serverConfig.username);
        jTextFieldUsername.setMaximumSize(new java.awt.Dimension(28, 20));
        jTextFieldUsername.setMinimumSize(new java.awt.Dimension(4, 20));
        jTextFieldUsername.setPreferredSize(new java.awt.Dimension(79, 20));

        jLabel4.setText("Password:");

        jPasswordField.setText(RMIServer.serverConfig.password);
        jPasswordField.setMaximumSize(new java.awt.Dimension(28, 20));
        jPasswordField.setMinimumSize(new java.awt.Dimension(4, 20));
        jPasswordField.setPreferredSize(new java.awt.Dimension(70, 20));

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jLabel4)
                                        .add(jLabel3))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jTextFieldUsername, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .add(jPasswordField, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jLabel3)
                                        .add(jTextFieldUsername, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jPasswordField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(jLabel4)))
        );

        jLabel5.setText("Configuration:");

        jComboBoxConfig.setEditable(true);
        jComboBoxConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConfigActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("IP Address"));

        jCheckBoxManualAdr.setSelected(!RMIServer.serverConfig.multihomed_enabled);
        jCheckBoxManualAdr.setText("Manual ");
        jCheckBoxManualAdr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxManualAdrActionPerformed(evt);
            }
        });

        jCheckBoxMultihomedEnabled.setSelected(RMIServer.serverConfig.multihomed_enabled);
        jCheckBoxMultihomedEnabled.setText("Automatic");
        jCheckBoxMultihomedEnabled.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMultihomedEnabledActionPerformed(evt);
            }
        });

        jLabel1.setText("Address:");

        jComboBoxLocalAdrs.setSelectedItem(RMIServer.serverConfig.server_address);
        jComboBoxLocalAdrs.setMaximumSize(new java.awt.Dimension(28, 20));
        jComboBoxLocalAdrs.setMinimumSize(new java.awt.Dimension(28, 20));

        jCheckBoxDefaultAdr.setSelected(RMIServer.serverConfig.default_address);
        jCheckBoxDefaultAdr.setText("Default");
        jCheckBoxDefaultAdr.setToolTipText("Set as the default Java Virtual Machine IP Adresse");

        jButtonRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jrdesktop/images/reload.png"))); // NOI18N
        jButtonRefresh.setMaximumSize(new java.awt.Dimension(49, 20));
        jButtonRefresh.setMinimumSize(new java.awt.Dimension(49, 20));
        jButtonRefresh.setPreferredSize(new java.awt.Dimension(49, 20));
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel4Layout.createSequentialGroup()
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel4Layout.createSequentialGroup()
                                                .add(jCheckBoxManualAdr)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(jCheckBoxMultihomedEnabled)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                                .add(jCheckBoxDefaultAdr))
                                        .add(jPanel4Layout.createSequentialGroup()
                                                .add(jLabel1)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jComboBoxLocalAdrs, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jButtonRefresh, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 39, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .add(10, 10, 10))
        );
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel4Layout.createSequentialGroup()
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jCheckBoxManualAdr)
                                        .add(jCheckBoxMultihomedEnabled)
                                        .add(jCheckBoxDefaultAdr))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                                .add(jLabel1)
                                                .add(jComboBoxLocalAdrs, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .add(jButtonRefresh, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Ports"));

        jTextFieldPort.setText(String.valueOf(RMIServer.serverConfig.server_port));
        jTextFieldPort.setMaximumSize(new java.awt.Dimension(28, 20));
        jTextFieldPort.setMinimumSize(new java.awt.Dimension(28, 20));
        jTextFieldPort.setPreferredSize(new java.awt.Dimension(28, 20));

        jLabel2.setText("Main:");

        jLabel6.setText("Http:");

        jTextFieldHTTPPort.setText(String.valueOf(RMIServer.serverConfig.http_port));
        jTextFieldHTTPPort.setMaximumSize(new java.awt.Dimension(28, 20));
        jTextFieldHTTPPort.setMinimumSize(new java.awt.Dimension(28, 20));
        jTextFieldHTTPPort.setPreferredSize(new java.awt.Dimension(28, 20));

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel6)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jTextFieldHTTPPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(jPanel2Layout.createSequentialGroup()
                                                .add(jLabel2)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jTextFieldPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 75, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel2Layout.createSequentialGroup()
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jLabel2)
                                        .add(jTextFieldPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jLabel6)
                                        .add(jTextFieldHTTPPort, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(12, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                        .add(layout.createSequentialGroup()
                                                .add(jLabel5)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jComboBoxConfig, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jButtonOK))
                                        .add(layout.createSequentialGroup()
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jLabel5)
                                        .add(jComboBoxConfig, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                        .add(jButtonOK))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
                                        .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOKActionPerformed
        String config_name = "";
        Object item = jComboBoxConfig.getSelectedItem();
        if (item != null) {
            config_name = item.toString();
            if (!config_name.equals(Commons.DEFAULT_CONFIG) &&
                    !Pattern.matches("[^\\/:*?\"<>|]*", config_name)) {
                JOptionPane.showMessageDialog(ConfigJPanel.this,
                        "A configuration name cannot contain any of the following characters " +
                                "\\ / : * ? \" < > | ", "Error", JOptionPane.ERROR_MESSAGE);
                jComboBoxConfig.setFocusable(true);
                return;
            }
        }
        lastConfigIndex = jComboBoxConfig.getSelectedIndex();

        RMIServer.serverConfig.setConfiguration(Commons.serverSide, config_name,
                jComboBoxLocalAdrs.getSelectedItem().toString(),
                jCheckBoxDefaultAdr.isSelected(),
                jCheckBoxMultihomedEnabled.isSelected(),
                Integer.valueOf(jTextFieldPort.getText()),
                Integer.valueOf(jTextFieldHTTPPort.getText()),
                jTextFieldUsername.getText(),
                String.copyValueOf(jPasswordField.getPassword()),
                jCheckBoxSSLEnabled.isSelected(),
                jCheckBoxReverseConnection.isSelected());
        main.displayTab(0);
    }//GEN-LAST:event_jButtonOKActionPerformed

    private void jComboBoxConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConfigActionPerformed
        Object item = jComboBoxConfig.getSelectedItem();
        if (item == null) return;

        String name = item.toString();
        RMIServer.serverConfig.loadConfiguration(Commons.serverSide, name);

        jComboBoxLocalAdrs.setEnabled(!RMIServer.serverConfig.multihomed_enabled);
        jComboBoxLocalAdrs.setSelectedItem(RMIServer.serverConfig.server_address);
        jCheckBoxManualAdr.setSelected(!RMIServer.serverConfig.multihomed_enabled);
        jCheckBoxMultihomedEnabled.setSelected(RMIServer.serverConfig.multihomed_enabled);
        jCheckBoxDefaultAdr.setSelected(RMIServer.serverConfig.default_address);
        jTextFieldPort.setText(Integer.toString(RMIServer.serverConfig.server_port));
        jTextFieldHTTPPort.setText(Integer.toString(RMIServer.serverConfig.http_port));
        jTextFieldUsername.setText(RMIServer.serverConfig.username);
        jPasswordField.setText(RMIServer.serverConfig.password);
        jCheckBoxSSLEnabled.setSelected(RMIServer.serverConfig.ssl_enabled);
        jCheckBoxReverseConnection.setSelected(RMIServer.serverConfig.reverseConnection);
    }//GEN-LAST:event_jComboBoxConfigActionPerformed

    private void jCheckBoxManualAdrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxManualAdrActionPerformed
        jComboBoxLocalAdrs.setEnabled(jCheckBoxManualAdr.isSelected());
        jCheckBoxMultihomedEnabled.setSelected(!jCheckBoxManualAdr.isSelected());
    }//GEN-LAST:event_jCheckBoxManualAdrActionPerformed

    private void jCheckBoxMultihomedEnabledActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMultihomedEnabledActionPerformed
        jCheckBoxManualAdr.setSelected(!jCheckBoxMultihomedEnabled.isSelected());
        jComboBoxLocalAdrs.setEnabled(!jCheckBoxMultihomedEnabled.isSelected());
    }//GEN-LAST:event_jCheckBoxMultihomedEnabledActionPerformed

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRefreshActionPerformed
        jComboBoxLocalAdrs.setModel(new DefaultComboBoxModel(InetAdrUtility.getLocalIPAdresses()));
        jComboBoxLocalAdrs.setSelectedItem(RMIServer.serverConfig.server_address);
    }//GEN-LAST:event_jButtonRefreshActionPerformed
    // End of variables declaration//GEN-END:variables

}
