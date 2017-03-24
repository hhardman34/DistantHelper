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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

import org.openvpnsystray.gui.systray.SystemTrayModalDialog;
import org.openvpnsystray.passwordmanager.SecureCharArray;

/**
 * {@link SetMasterPasswordDialogOKActionListener} implements the action that should be done when the 'OK' button has
 * being clicked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SetMasterPasswordDialogOKActionListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SetMasterPasswordDialogOKActionListener implements ActionListener {

    /**
     * The set master password dialog
     */
    private SetMasterPasswordDialog setMasterPasswordDialog;

    /**
     * The password text field
     */
    private JPasswordField passwordTextField;

    /**
     * The re-enter text field
     */
    private JPasswordField reEnterTextField;

    /**
     * The empty master password checkbox
     */
    private JCheckBox checkbox;

    /**
     * Creates a new {@link SetMasterPasswordDialogOKActionListener}.
     * 
     * @param setMasterPasswordDialog the set master password dialog
     * @param passwordTextField the password text field
     * @param reEnterTextField the re-enter text field
     * @param checkbox the empty master password checkbox
     */
    public SetMasterPasswordDialogOKActionListener(SetMasterPasswordDialog setMasterPasswordDialog,
                                                   JPasswordField passwordTextField,
                                                   JPasswordField reEnterTextField,
                                                   JCheckBox checkbox) {
        this.setMasterPasswordDialog = setMasterPasswordDialog;
        this.passwordTextField = passwordTextField;
        this.reEnterTextField = reEnterTextField;
        this.checkbox = checkbox;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(@SuppressWarnings("unused")ActionEvent actionEvent) {
        char password[] = this.passwordTextField.getPassword();
        char reEnter[] = this.reEnterTextField.getPassword();
        if (SecureCharArray.compareCharArrays(password, reEnter) || this.checkbox.isSelected()) {
            this.setMasterPasswordDialog.dispose();
            this.setMasterPasswordDialog.setExitValue(SystemTrayModalDialog.OK_OPTION);
            synchronized (this.setMasterPasswordDialog) {
                this.setMasterPasswordDialog.notify();
            }
        }
        SecureCharArray.clearCharArray(password);
        SecureCharArray.clearCharArray(reEnter);
    }

}
