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
package org.openvpnsystray.gui.userpassdialog;

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
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import org.openvpnsystray.gui.systray.SystemTrayModalDialog;
import org.openvpnsystray.gui.utils.IconProvider;
import org.openvpnsystray.gui.utils.LocalizationProvider;
import org.openvpnsystray.gui.utils.Utils;

/**
 * The {@link UserPassDialog} implements a dialog that asks for username and password. Additionally, a checkbox is
 * supplied to set whether the authentication information should be saved. This dialog can be used to ask for passwords
 * only too. The save authentication checkbox may also be disabled. Both is configurable through constructors.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: UserPassDialog.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class UserPassDialog extends SystemTrayModalDialog {

    /**
     * UID for serialization purpose
     */
    private static final long serialVersionUID = 5471489972554998852L;

    /**
     * Width of the frame
     */
    private static final int FRAME_WIDTH = 250;

    /**
     * Height of the frame
     */
    private static final int FRAME_HEIGHT = 120;

    /**
     * The username text field
     */
    private JTextField userNameTextField;

    /**
     * The password text field
     */
    private JPasswordField passwordTextField;

    /**
     * The username
     */
    private String userName;

    /**
     * The password
     */
    private char[] password;

    /**
     * The save authentication value
     */
    private Boolean saveAuth = null;

    /**
     * The save authentication checkbox
     */
    private JCheckBox checkbox;

    /**
     * Creates a new {@link UserPassDialog}.
     * 
     * @param enableUserName decides whether username should be enabled
     * @param enableSaveAuth decides whether save authentication checkbox and label should be enabled
     */
    public UserPassDialog(boolean enableUserName, boolean enableSaveAuth) {
        this(null, enableUserName, enableSaveAuth);
    }

    /**
     * Creates a new {@link UserPassDialog}.
     * 
     * @param window the {@link Window} from which the dialog is displayed
     * @param enableUserName decides whether username should be enabled
     * @param enableSaveAuth decides whether save authentication checkbox and label should be enabled
     */
    public UserPassDialog(Window window,
                          boolean enableUserName,
                          boolean enableSaveAuth) {
        super(window, LocalizationProvider.getUserPassDialogDialogTitle(), ModalityType.APPLICATION_MODAL);
        initDialog(enableUserName, enableSaveAuth);
    }

    /**
     * Creates a new {@link UserPassDialog}.
     * 
     * @param enableUserName decides whether username should be enabled
     * @param enableSaveAuth decides whether save authentication checkbox and label should be enabled
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the {@link Dialog.ModalityType} of this dialog
     */
    public UserPassDialog(boolean enableUserName,
                          boolean enableSaveAuth,
                          SystemTray systemTray,
                          TrayIcon trayIcon,
                          int systemTrayModalityType) {
        this(null, enableUserName, enableSaveAuth, systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link UserPassDialog}.
     * 
     * @param window the {@link Window} from which the dialog is displayed
     * @param enableUserName decides whether username should be enabled
     * @param enableSaveAuth decides whether save authentication checkbox and label should be enabled
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the {@link Dialog.ModalityType} of this dialog
     */
    public UserPassDialog(Window window,
                          boolean enableUserName,
                          boolean enableSaveAuth,
                          SystemTray systemTray,
                          TrayIcon trayIcon,
                          int systemTrayModalityType) {
        super(window, LocalizationProvider.getUserPassDialogDialogTitle(), ModalityType.APPLICATION_MODAL, systemTray,
            trayIcon, systemTrayModalityType);
        initDialog(enableUserName, enableSaveAuth);
    }

    /**
     * Initialize the dialogInitialize the dialog, but sets visibility to false. Use {@link UserPassDialog#showDialog()}
     * to make it visible.
     * 
     * @param enableUserName decides whether username should be enabled
     * @param enableSaveAuth decides whether save authentication checkbox and label should be enabled
     */
    private void initDialog(boolean enableUserName, boolean enableSaveAuth) {
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        Utils.setFrameToMiddleOfTheScreen(this);

        JPanel panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);

        JPanel centerPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 2);
        centerPanel.setLayout(gridLayout);

        JPanel upperLeftPanel = new JPanel();
        JLabel userNameLabel = new JLabel(LocalizationProvider.getUserPassDialogUserName());
        userNameLabel.setEnabled(enableUserName);
        upperLeftPanel.add(userNameLabel);

        JPanel upperRightPanel = new JPanel();
        this.userNameTextField = new JTextField(10);
        this.userNameTextField.setEnabled(enableUserName);
        upperRightPanel.add(this.userNameTextField);

        JPanel bottomLeftPanel = new JPanel();
        JLabel passwordLabel = new JLabel(LocalizationProvider.getUserPassDialogPassword());
        bottomLeftPanel.add(passwordLabel);

        JPanel bottomRightPanel = new JPanel();
        this.passwordTextField = new JPasswordField(10);
        bottomRightPanel.add(this.passwordTextField);

        centerPanel.add(upperLeftPanel);
        centerPanel.add(upperRightPanel);
        centerPanel.add(bottomLeftPanel);
        centerPanel.add(bottomRightPanel);
        panel.add(centerPanel, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        BorderLayout bLayout = new BorderLayout();
        southPanel.setLayout(bLayout);

        JPanel northPanel = new JPanel();
        this.checkbox = new JCheckBox();
        this.checkbox.setEnabled(enableSaveAuth);
        JLabel saveAuthLabel = new JLabel(LocalizationProvider.getUserPassDialogSaveUserPassQuestion());
        saveAuthLabel.setEnabled(enableSaveAuth);
        saveAuthLabel.addMouseListener(new SaveAuthListener(this.checkbox));
        northPanel.add(this.checkbox);
        northPanel.add(saveAuthLabel);
        southPanel.add(northPanel, BorderLayout.NORTH);

        JPanel sPanel = new JPanel();
        JButton okButton = new JButton(LocalizationProvider.getButtonsOK());
        okButton.addActionListener(new UserPassDialogOKActionListener(this));
        JButton cancelButton = new JButton(LocalizationProvider.getButtonsCancel());
        cancelButton.addActionListener(new UserPassDialogCancelActionListener(this));
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
     * Returns the username the user typed in or <code>null</code> if username text field is disabled.
     * 
     * @return the username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Returns the password the user typed in.
     * 
     * @return the password
     */
    public char[] getPassword() {
        return this.password;
    }

    /**
     * Returns the save authentication value which may be <code>true</code>, <code>false</code> or
     * <code>null</code>. <br>
     * <br>
     * true - the user wishes to save authentication information <br>
     * false - the user doesn't wish to save authentication information <br>
     * null - the save authentication checkbox is disabled
     * 
     * @return the save authentication value
     */
    public Boolean getSaveAuth() {
        return this.saveAuth;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.openvpnsystray.gui.systray.SystemTrayModalDialog#showDialog()
     */
    @Override
    public int showDialog() {
        this.setVisible(true);
        if (this.userNameTextField.isEnabled()) {
            this.userName = this.userNameTextField.getText();
            Utils.secureTextDeletionFromTextField(this.userNameTextField, this.userName.length());
        }
        this.password = this.passwordTextField.getPassword();
        Utils.secureTextDeletionFromTextField(this.passwordTextField, this.password.length);
        if (this.checkbox.isEnabled()) {
            this.saveAuth = new Boolean(this.checkbox.isSelected());
        }
        return super.getExitValue();
    }
}
