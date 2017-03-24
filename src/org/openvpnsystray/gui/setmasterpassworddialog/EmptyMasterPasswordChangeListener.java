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

import java.util.Locale;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.openvpnsystray.gui.utils.LocalizationProvider;
import org.openvpnsystray.gui.utils.Utils;

/**
 * {@link EmptyMasterPasswordChangeListener} implements the action that should be done when the state of the empty
 * master password checkbox has been changed.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: EmptyMasterPasswordChangeListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class EmptyMasterPasswordChangeListener implements ChangeListener {

    /**
     * The password label
     */
    private JLabel passwordLabel;

    /**
     * The password text field
     */
    private JPasswordField passwordTextField;

    /**
     * The re-enter label
     */
    private JLabel reEnterLabel;

    /**
     * The re-enter text field
     */
    private JPasswordField reEnterTextField;

    /**
     * Stored checkbox state. Is needed because change events are thrown even when the state of the checkbox hasn't
     * changed.
     */
    private boolean checkboxStoredSelected = false;

    /**
     * The set master password dialog
     */
    private SetMasterPasswordDialog setMasterPasswordDialog;

    /**
     * Creates a new {@link EmptyMasterPasswordChangeListener}.
     * 
     * @param setMasterPasswordDialog the set master password dialog
     * @param passwordLabel the password label
     * @param passwordTextField the password text field
     * @param reEnterLabel the re-enter label
     * @param reEnterTextField the re-enter text field
     */
    public EmptyMasterPasswordChangeListener(SetMasterPasswordDialog setMasterPasswordDialog,
                                             JLabel passwordLabel,
                                             JPasswordField passwordTextField,
                                             JLabel reEnterLabel,
                                             JPasswordField reEnterTextField) {
        this.setMasterPasswordDialog = setMasterPasswordDialog;
        this.passwordLabel = passwordLabel;
        this.passwordTextField = passwordTextField;
        this.reEnterLabel = reEnterLabel;
        this.reEnterTextField = reEnterTextField;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
     */
    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        JCheckBox checkBox = (JCheckBox) changeEvent.getSource();
        if (checkBox.isSelected() != this.checkboxStoredSelected) {
            this.checkboxStoredSelected = checkBox.isSelected();
            if (checkBox.isSelected()) {
                JComponent.setDefaultLocale(Locale.ENGLISH);
                int confirmDialogResult = JOptionPane.showConfirmDialog(this.setMasterPasswordDialog,
                    LocalizationProvider.getOptionPaneAllowEmptyMasterPasswordMessage(),
                    LocalizationProvider.getOptionPaneAllowEmptyMasterPasswordTitle(),
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
                if (confirmDialogResult == JOptionPane.OK_OPTION) {
                    this.passwordLabel.setEnabled(false);
                    this.passwordTextField.setEnabled(false);
                    Utils.secureTextDeletionFromTextField(this.passwordTextField,
                        this.passwordTextField.getPassword().length);
                    this.reEnterLabel.setEnabled(false);
                    this.reEnterTextField.setEnabled(false);
                    Utils.secureTextDeletionFromTextField(this.reEnterTextField,
                        this.reEnterTextField.getPassword().length);
                } else {
                    checkBox.setSelected(false);
                }
            } else {
                this.passwordLabel.setEnabled(true);
                this.passwordTextField.setEnabled(true);
                this.reEnterLabel.setEnabled(true);
                this.reEnterTextField.setEnabled(true);
            }
        }
    }

}
