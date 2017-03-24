package jrdesktop;

import jrdesktop.rmi.server.RMIServer;
import jrdesktop.server.Server;
import jrdesktop.viewer.ConnectionDialog;
import jrdesktop.viewer.Viewer;

import javax.swing.*;

/**
 * @author Admin
 */
public class HomeJPanel extends javax.swing.JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonNewConnection;
    private javax.swing.JButton jButtonStartStop;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextAreaStatus;
    /**
     * Creates new form HomeJPanel
     */
    public HomeJPanel() {
        initComponents();
        updateStatus();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaStatus = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jButtonStartStop = new javax.swing.JButton();
        jButtonNewConnection = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

        jTextAreaStatus.setBackground(new java.awt.Color(236, 233, 216));
        jTextAreaStatus.setColumns(17);
        jTextAreaStatus.setEditable(false);
        jTextAreaStatus.setFont(new java.awt.Font("SansSerif", 0, 11));
        jTextAreaStatus.setRows(5);
        jTextAreaStatus.setBorder(javax.swing.BorderFactory.createTitledBorder("Status"));
        jTextAreaStatus.setMaximumSize(new java.awt.Dimension(160, 100));
        jTextAreaStatus.setMinimumSize(new java.awt.Dimension(160, 100));
        jScrollPane1.setViewportView(jTextAreaStatus);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jrdesktop/images/logo_big.png"))); // NOI18N

        jButtonStartStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jrdesktop/images/player_play.png"))); // NOI18N
        jButtonStartStop.setText("Start");
        jButtonStartStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartStopActionPerformed(evt);
            }
        });

        jButtonNewConnection.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jrdesktop/images/connect_creating.png"))); // NOI18N
        jButtonNewConnection.setText("New connection ...");
        jButtonNewConnection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewConnectionActionPerformed(evt);
            }
        });

        jButtonExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/jrdesktop/images/exit.png"))); // NOI18N
        jButtonExit.setText("Exit");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(layout.createSequentialGroup()
                                                .add(12, 12, 12)
                                                .add(jButtonStartStop, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 96, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jButtonNewConnection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 188, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                .add(jButtonExit, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 88, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1)
                                                .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                                                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 221, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                                        .add(jLabel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 170, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(layout.createSequentialGroup()
                                .addContainerGap()
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                        .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                        .add(jButtonStartStop)
                                        .add(jButtonNewConnection)
                                        .add(jButtonExit))
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonStartStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartStopActionPerformed
        if (RMIServer.serverConfig.reverseConnection) {
            if (Viewer.isRunning())
                Viewer._Stop();
            else
                Viewer._Start();
        } else {
            if (Server.isRunning())
                Server.Stop();
            else
                Server.Start();
        }
        updateStatus();
    }//GEN-LAST:event_jButtonStartStopActionPerformed

    private void jButtonNewConnectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewConnectionActionPerformed
        ConnectionDialog.main(null);
    }//GEN-LAST:event_jButtonNewConnectionActionPerformed

    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExitActionPerformed
        main.quit();
    }//GEN-LAST:event_jButtonExitActionPerformed

    public void updateStatus() {
        if (RMIServer.serverConfig.reverseConnection)
            if (Viewer.isRunning()) {
                jTextAreaStatus.setText(Viewer.getStatus());
                jButtonStartStop.setText("Stop");
                jButtonStartStop.setIcon(new ImageIcon(Commons.STOP_ICON));
            } else {
                jTextAreaStatus.setText("Stopped.");
                jButtonStartStop.setText("Start");
                jButtonStartStop.setIcon(new ImageIcon(Commons.START_ICON));
            }
        else if (Server.isRunning()) {
            jTextAreaStatus.setText(Server.getStatus());
            jButtonStartStop.setText("Stop");
            jButtonStartStop.setIcon(new ImageIcon(Commons.STOP_ICON));
        } else {
            jTextAreaStatus.setText("Stopped.");
            jButtonStartStop.setText("Start");
            jButtonStartStop.setIcon(new ImageIcon(Commons.START_ICON));
        }
    }
    // End of variables declaration//GEN-END:variables

}