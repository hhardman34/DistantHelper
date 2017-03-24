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

import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.openvpnsystray.gui.utils.IconProvider;

/**
 * {@link ConnectActionListener} implements the action that should be done when a 'Connect' menu item has being clicked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: ConnectActionListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class ConnectActionListener implements ActionListener {

    /**
     * The system tray icon
     */
    private SystemTrayIcon systemTrayIcon;

    /**
     * Creates a new {@link ConnectActionListener}.
     * 
     * @param systemTrayIcon the system tray icon
     */
    public ConnectActionListener(SystemTrayIcon systemTrayIcon) {
        this.systemTrayIcon = systemTrayIcon;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        MenuItem menuItem = (MenuItem) actionEvent.getSource();
        // disables the connect-button
        menuItem.setEnabled(false);
        OpenVPNPopupMenu openVPNPopupMenu = (OpenVPNPopupMenu) menuItem.getParent();
        // enables the disconnect-button
        openVPNPopupMenu.getItem(1).setEnabled(true);
        // starts connection using selected configuration file
        openVPNPopupMenu.getOpenVPNConfig().connect();
        // change tray icon to connecting
        this.systemTrayIcon.setTrayIcon(IconProvider.CONNECTING_ICON);
        // disables connect-button for all configuration files
        this.systemTrayIcon.setAllConnectMenuItems(false);
    }
}
