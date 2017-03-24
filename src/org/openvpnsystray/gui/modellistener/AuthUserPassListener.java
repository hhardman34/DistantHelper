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
package org.openvpnsystray.gui.modellistener;

import org.openvpnsystray.gui.systray.SystemTrayIcon;
import org.openvpnsystray.gui.systray.SystemTrayModalDialog;
import org.openvpnsystray.gui.userpassdialog.UserPassDialog;
import org.openvpnsystray.openvpn.OpenVPNConfig;
import org.openvpnsystray.openvpn.OpenVPNConnection;
import org.openvpnsystray.openvpn.eventsource.AuthUserPassEvent;
import org.openvpnsystray.openvpn.eventsource.IAuthUserPassListener;
import org.openvpnsystray.passwordmanager.PasswordEntry;
import org.openvpnsystray.passwordmanager.PasswordManager;

/**
 * {@link AuthUserPassListener} implements the action that should be done when OpenVPN asks for authentication
 * information. <br>
 * <br>
 * First, this implementation asks the password manager for username and password. If the password manager can't supply
 * the required authentication information a dialog is shown which asks the user for username and password.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: AuthUserPassListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class AuthUserPassListener implements IAuthUserPassListener {

    /**
     * The system tray icon
     */
    private SystemTrayIcon systemTrayIcon;

    /**
     * Creates a new {@link AuthUserPassListener}.
     * 
     * @param systemTrayIcon The system tray icon
     */
    public AuthUserPassListener(SystemTrayIcon systemTrayIcon) {
        this.systemTrayIcon = systemTrayIcon;
    }

    @Override
    public void actionPerformed(AuthUserPassEvent authUserPassEvent) {
        PasswordEntry passwordEntry = null;
        OpenVPNConnection openVPNConnection = (OpenVPNConnection) authUserPassEvent.getSource();
        PasswordManager passwordManager = PasswordManager.getPasswordManager();
        OpenVPNConfig openVPNConfig = openVPNConnection.getOpenVPNConfig();
        String description = openVPNConfig.getName();
        if (null != (passwordEntry = passwordManager.getPasswordEntry(description))) {
            openVPNConnection.setUserName(passwordEntry.getUserName());
            openVPNConnection.setPassword(passwordEntry.getPassword());
            return;
        }
        UserPassDialog userPassDialog = new UserPassDialog(true, true, this.systemTrayIcon
                .getSystemTray(), this.systemTrayIcon.getTrayIcon(), SystemTrayModalDialog.POPUP_MENU_REMOVE);
        int userPassDialogResult = userPassDialog.showDialog();
        if (userPassDialogResult == SystemTrayModalDialog.OK_OPTION) {
            String userName = userPassDialog.getUserName();
            char[] password = userPassDialog.getPassword();
            Boolean saveAuth = userPassDialog.getSaveAuth();
            if (userName == null) {
                openVPNConnection.setPassword(password);
            } else {
                openVPNConnection.setUserName(userName);
                openVPNConnection.setPassword(password);
            }
            if (saveAuth != null && saveAuth.booleanValue()) {
                passwordManager.addPasswordEntry(userName, password, description);
            }
        } else {
            openVPNConfig.disconnect();
        }
    }

}