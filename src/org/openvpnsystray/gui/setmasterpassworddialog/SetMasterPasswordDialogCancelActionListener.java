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

import org.openvpnsystray.gui.systray.SystemTrayModalDialog;

/**
 * {@link SetMasterPasswordDialogCancelActionListener} implements the action that should be done when the 'Cancel'
 * button has being clicked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SetMasterPasswordDialogCancelActionListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SetMasterPasswordDialogCancelActionListener implements ActionListener {

    /**
     * The set master password dialog
     */
    private SetMasterPasswordDialog setMasterPasswordDialog;

    /**
     * Creates a new {@link SetMasterPasswordDialogCancelActionListener}.
     * 
     * @param setMasterPasswordDialog the set master password dialog
     */
    public SetMasterPasswordDialogCancelActionListener(SetMasterPasswordDialog setMasterPasswordDialog) {
        this.setMasterPasswordDialog = setMasterPasswordDialog;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(@SuppressWarnings("unused")ActionEvent actionEvent) {
        this.setMasterPasswordDialog.dispose();
        this.setMasterPasswordDialog.setExitValue(SystemTrayModalDialog.CANCEL_OPTION);
    }

}
