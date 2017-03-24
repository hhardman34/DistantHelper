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

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;

import org.openvpnsystray.gui.logframe.LogFrame;
import org.openvpnsystray.gui.utils.IconProvider;
import org.openvpnsystray.gui.utils.LocalizationProvider;
import org.openvpnsystray.openvpn.OpenVPNConfig;
import org.openvpnsystray.openvpn.OpenVPNConnection;

/**
 * {@link OpenVPNPopupMenu} creates a popup menu for a OpenVPN configuration object containing three menu item, namely
 * 'Connect', 'Disconnect' and 'Show Log'.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: OpenVPNPopupMenu.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class OpenVPNPopupMenu extends PopupMenu {

    /**
     * UID for serialization purpose
     */
    private static final long serialVersionUID = 4197693241963506464L;

    /**
     * The OpenVPN configuration object
     */
    private OpenVPNConfig openVPNConfig;

    /**
     * Creates a new {@link OpenVPNPopupMenu}.
     * 
     * @param openVPNConfig the OpenVPN configuration object
     * @param systemTrayIcon the system tray icon
     * @throws HeadlessException if GraphicsEnvironment.isHeadless() returns true
     */
    public OpenVPNPopupMenu(OpenVPNConfig openVPNConfig,
                            SystemTrayIcon systemTrayIcon) throws HeadlessException {
        super(openVPNConfig.getName());
        this.openVPNConfig = openVPNConfig;

        // configures the connect menu item
        MenuItem connectMenuItem = new MenuItem(LocalizationProvider.getOpenVPNPopupMenuConnect());
        ConnectActionListener connectActionListener = new ConnectActionListener(systemTrayIcon);
        connectMenuItem.addActionListener(connectActionListener);
        connectMenuItem.setEnabled(true);
        this.add(connectMenuItem);

        // configures the disconnect menu item
        MenuItem disconnectMenuItem = new MenuItem(LocalizationProvider.getOpenVPNPopupMenuDisconnect());
        DisconnectActionListener disconnectActionListener = new DisconnectActionListener(systemTrayIcon);
        disconnectMenuItem.addActionListener(disconnectActionListener);
        disconnectMenuItem.setEnabled(false);
        this.add(disconnectMenuItem);

        this.addSeparator();

        // create log frame
        OpenVPNConnection openVPNConnection = this.openVPNConfig.getOpenVPNConnection();
        String logFrameTitle = LocalizationProvider.getLogFrameTitle() + this.openVPNConfig.getName();
        String logFrameOKButtonText = LocalizationProvider.getButtonsOK();
        Image logFrameIconImage = IconProvider.OPENVPN_SYSTRAY_IMAGE;
        LogFrame logFrame = new LogFrame(logFrameTitle, logFrameOKButtonText, logFrameIconImage, openVPNConnection);

        // configures the log menu item
        MenuItem logMenuItem = new MenuItem(LocalizationProvider.getOpenVPNPopupMenuShowLog());
        ShowLogActionListener logActionListener = new ShowLogActionListener(logFrame);
        logMenuItem.addActionListener(logActionListener);
        logMenuItem.setEnabled(true);
        this.add(logMenuItem);
    }

    /**
     * Returns the OpenVPN configuration object.
     * 
     * @return the openVPN configuration object
     */
    public OpenVPNConfig getOpenVPNConfig() {
        return this.openVPNConfig;
    }

}
