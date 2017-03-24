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
package org.openvpnsystray.gui.systray;

import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.openvpnsystray.Configuration;
import org.openvpnsystray.ConfigurationProvider;
import org.openvpnsystray.gui.setmasterpassworddialog.SetMasterPasswordDialog;
import org.openvpnsystray.passwordmanager.PasswordManager;
import org.openvpnsystray.utils.Serialize;

/**
 * {@link ChangeMasterPasswordActionListener} implements the action that should be done when the 'Change Master
 * Password' menu item has being clicked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: ChangeMasterPasswordActionListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class ChangeMasterPasswordActionListener implements ActionListener {

    /**
     * The configuration object
     */
    private static Configuration configuration = ConfigurationProvider.getConfiguration();

    /**
     * The system tray
     */
    private SystemTray systemTray;

    /**
     * The tray icon
     */
    private TrayIcon trayIcon;

    /**
     * Creates a new {@link ChangeMasterPasswordActionListener}.
     * 
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     */
    public ChangeMasterPasswordActionListener(SystemTray systemTray, TrayIcon trayIcon) {
        this.systemTray = systemTray;
        this.trayIcon = trayIcon;
    }

    @Override
    public void actionPerformed(@SuppressWarnings("unused")
    ActionEvent actionEvent) {
        SetMasterPasswordDialog setMasterPasswordDialog = new SetMasterPasswordDialog(null, this.systemTray,
            this.trayIcon, SystemTrayModalDialog.POPUP_MENU_REMOVE);
        int masterPasswordDialogResult = setMasterPasswordDialog.showDialog();
        if (masterPasswordDialogResult == SystemTrayModalDialog.OK_OPTION) {
            char[] newMasterPassword = setMasterPasswordDialog.getMasterPassword();
            PasswordManager.getPasswordManager().serializePasswordDatabasse(newMasterPassword);
            if (newMasterPassword.length == 0) {
                ChangeMasterPasswordActionListener.configuration.setEmptyMasterPassword(true);
            } else {
                ChangeMasterPasswordActionListener.configuration.setEmptyMasterPassword(false);
            }
            Serialize.writeObjectToFile(ChangeMasterPasswordActionListener.configuration.getConfigFile(),
                ChangeMasterPasswordActionListener.configuration);
        }
    }

}
