/******************************************************************************
 * Copyright (c) 2008 Stefan Franke                                           *
 *                                                                            *
 * This file is part of OpenVPN SysTray.                                      *
 *                                                                            *
 * OpenVPN SysTray is free software: you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License as published by       *
 * the Free Software Foundation, version 2 of the License.                    *
 *                                                                            *
 * OpenVPN SysTray is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of             *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the              *
 * GNU General Public License for more details.                               *
 *                                                                            *
 * You should have received a copy of the GNU General Public License          *
 * along with OpenVPN SysTray.  If not, see <http://www.gnu.org/licenses/>.   *
 ******************************************************************************/
package org.openvpnsystray.gui.setmasterpassworddialog;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.WindowConstants;

import org.openvpnsystray.gui.systray.SystemTrayModalDialog;
import org.openvpnsystray.gui.utils.IconProvider;
import org.openvpnsystray.gui.utils.LocalizationProvider;
import org.openvpnsystray.gui.utils.Utils;

/**
 * The {@link SetMasterPasswordDialog} implements a dialog that asks the user for a new master password. Additionally, a
 * checkbox is supplied to allow empty master passwords. If the user activates this option a message dialog is shown to
 * inform about the risk of an empty master password.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SetMasterPasswordDialog.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SetMasterPasswordDialog extends SystemTrayModalDialog {

    /**
     * UID for serialization purpose
     */
    private static final long serialVersionUID = -7275497701872291527L;

    /**
     * Width of the frame
     */
    private static final int FRAME_WIDTH = 250;

    /**
     * Height of the frame
     */
    private static final int FRAME_HEIGHT = 120;

    /**
     * The password text field
     */
    private JPasswordField passwordTextField;

    /**
     * The re-enter text field
     */
    private JPasswordField reEnterTextField;

    /**
     * The master password
     */
    private char[] masterPassword;

    /**
     * Creates a new {@link SetMasterPasswordDialog}.
     */
    public SetMasterPasswordDialog() {
        this(null);
    }

    /**
     * Creates a new {@link SetMasterPasswordDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     */
    public SetMasterPasswordDialog(Window owner) {
        super(owner, LocalizationProvider.getSetMasterPasswordDialogDialogTitle(), ModalityType.APPLICATION_MODAL);
        initDialog();
    }

    /**
     * Creates a new {@link SetMasterPasswordDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the {@link Dialog.ModalityType} of this dialog
     */
    public SetMasterPasswordDialog(Window owner, SystemTray systemTray, TrayIcon trayIcon, int systemTrayModalityType) {
        super(owner, LocalizationProvider.getSetMasterPasswordDialogDialogTitle(), ModalityType.APPLICATION_MODAL,
            systemTray, trayIcon, systemTrayModalityType);
        initDialog();
    }

    /**
     * Initialize the dialog, but sets visibility to false. Use {@link SetMasterPasswordDialog#showDialog()} to make it
     * visible.
     */
    private void initDialog() {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        Utils.setFrameToMiddleOfTheScreen(this);

        JPanel panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);

        JPanel centerPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 2);
        centerPanel.setLayout(gridLayout);

        JPanel upperLeftPanel = new JPanel();
        JLabel passwordLabel = new JLabel(LocalizationProvider.getSetMasterPasswordDialogPassword());
        upperLeftPanel.add(passwordLabel);

        JPanel upperRightPanel = new JPanel();
        this.passwordTextField = new JPasswordField(10);
        upperRightPanel.add(this.passwordTextField);

        JPanel bottomLeftPanel = new JPanel();
        JLabel reEnterLabel = new JLabel(LocalizationProvider.getSetMasterPasswordDialogReEnter());
        bottomLeftPanel.add(reEnterLabel);

        JPanel bottomRightPanel = new JPanel();
        this.reEnterTextField = new JPasswordField(10);
        bottomRightPanel.add(this.reEnterTextField);

        centerPanel.add(upperLeftPanel);
        centerPanel.add(upperRightPanel);
        centerPanel.add(bottomLeftPanel);
        centerPanel.add(bottomRightPanel);
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        BorderLayout bLayout = new BorderLayout();
        southPanel.setLayout(bLayout);

        JPanel northPanel = new JPanel();
        JCheckBox checkbox = new JCheckBox();
        checkbox.addChangeListener(new EmptyMasterPasswordChangeListener(this, passwordLabel, this.passwordTextField,
            reEnterLabel, this.reEnterTextField));
        JLabel saveAuthLabel = new JLabel(LocalizationProvider
                .getSetMasterPasswordDialogAllowEmptyMasterPasswordQuestion());
        saveAuthLabel.addMouseListener(new EmptyMasterPasswordListener(checkbox));
        northPanel.add(checkbox);
        northPanel.add(saveAuthLabel);
        southPanel.add(northPanel, BorderLayout.NORTH);

        JPanel sPanel = new JPanel();
        JButton okButton = new JButton(LocalizationProvider.getButtonsOK());
        SetMasterPasswordDialogOKActionListener masterPasswordFrameActionListener = new SetMasterPasswordDialogOKActionListener(
            this, this.passwordTextField, this.reEnterTextField, checkbox);
        okButton.addActionListener(masterPasswordFrameActionListener);
        okButton.setEnabled(true);
        JButton cancelButton = new JButton(LocalizationProvider.getButtonsCancel());
        cancelButton.addActionListener(new SetMasterPasswordDialogCancelActionListener(this));
        sPanel.add(okButton);
        sPanel.add(cancelButton);
        southPanel.add(sPanel, BorderLayout.SOUTH);

        panel.add(southPanel, BorderLayout.SOUTH);

        this.add(panel);

        this.pack();
        this.setIconImage(IconProvider.OPENVPN_SYSTRAY_IMAGE);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setResizable(false);
        this.setVisible(false);
    }

    /**
     * Returns the master password.
     * 
     * @return the master password
     */
    public char[] getMasterPassword() {
        return this.masterPassword;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.openvpnsystray.gui.systray.SystemTrayModalDialog#showDialog()
     */
    @Override
    public int showDialog() {
        this.setVisible(true);
        this.masterPassword = this.passwordTextField.getPassword();
        Utils.secureTextDeletionFromTextField(this.passwordTextField, this.masterPassword.length);
        Utils.secureTextDeletionFromTextField(this.reEnterTextField, this.masterPassword.length);
        return super.getExitValue();
    }
}
