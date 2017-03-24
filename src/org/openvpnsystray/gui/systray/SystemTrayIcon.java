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

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import org.openvpnsystray.LoggerProvider;
import org.openvpnsystray.gui.utils.IconProvider;
import org.openvpnsystray.gui.utils.LocalizationProvider;
import org.openvpnsystray.openvpn.OpenVPNConfig;

/**
 * {@link SystemTrayIcon} is the main entry point of the graphical user interface of OpenVPN SysTray. It creates the
 * tray icon and associated popup menu, and place the icon to the system tray.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SystemTrayIcon.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SystemTrayIcon {

    /**
     * The logger
     */
    private static Logger logger = LoggerProvider.getLogger();

    /**
     * The count of OpenVPN configuration files
     */
    private int openVPNConfigCount;

    /**
     * The system tray
     */
    private SystemTray systemTray;

    /**
     * The tray icon
     */
    private TrayIcon trayIcon;

    /**
     * Creates a new {@link SystemTrayIcon}.
     * 
     * @param openVPNConfigs the list of OpenVPN configuration objects
     * @param persistentMode
     */
    public SystemTrayIcon(List<OpenVPNConfig> openVPNConfigs,
                          boolean persistentMode) {
        try {
            PopupMenu popupMainMenu = new PopupMenu();

            for (OpenVPNConfig openVPNConfig : openVPNConfigs) {
                OpenVPNPopupMenu openvpnMenu = new OpenVPNPopupMenu(openVPNConfig, this);
                popupMainMenu.add(openvpnMenu);
            }
            this.openVPNConfigCount = popupMainMenu.getItemCount();

            MenuItem changeMasterPasswordMenuItem = new MenuItem(LocalizationProvider
                    .getSystemTrayPopupMenuChangeMasterPassword());
            changeMasterPasswordMenuItem.setEnabled(persistentMode);

            MenuItem exitMenuItem = new MenuItem(LocalizationProvider.getSystemTrayPopupMenuExit());
            exitMenuItem.addActionListener(new ExitActionListener(openVPNConfigs));

            popupMainMenu.addSeparator();
            popupMainMenu.add(changeMasterPasswordMenuItem);
            popupMainMenu.addSeparator();
            popupMainMenu.add(exitMenuItem);

            this.trayIcon = new TrayIcon(IconProvider.OPENVPN_SYSTRAY_IMAGE, LocalizationProvider.getApplicationProgramName(),
                popupMainMenu);
            this.trayIcon.setImageAutoSize(true);
            this.systemTray = SystemTray.getSystemTray();
            if (SystemTray.isSupported()) {
                this.systemTray.add(this.trayIcon);
            } else {
                SystemTrayIcon.logger.log(Level.SEVERE, LocalizationProvider.getOptionPaneSystemTrayNotSupportedMessage()); //$NON-NLS-1$
                JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                        LocalizationProvider.getOptionPaneSystemTrayNotSupportedMessage(),
                        LocalizationProvider.getOptionPaneSystemTrayNotSupportedTitle(),
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            changeMasterPasswordMenuItem.addActionListener(new ChangeMasterPasswordActionListener(this.systemTray, this.trayIcon));
        } catch (AWTException awtExpection) {
            SystemTrayIcon.logger.log(Level.SEVERE, awtExpection.getMessage()); //$NON-NLS-1$
            JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                    awtExpection.getMessage(),
                    LocalizationProvider.getOptionPaneSystemTrayNotSupportedTitle(),
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    /**
     * Returns the system tray.
     * 
     * @return the system tray
     */
    public SystemTray getSystemTray() {
        return this.systemTray;
    }

    /**
     * Returns the tray icon.
     * 
     * @return the tray icon
     */
    public TrayIcon getTrayIcon() {
        return this.trayIcon;
    }

    /**
     * Sets enabled/disabled state to all 'Connect' menu items.
     * 
     * @param connect if <code>true</code>, enables the menu items; if <code>false</code>, disables them
     */
    public void setAllConnectMenuItems(boolean connect) {
        for (int i = 0; i < this.openVPNConfigCount; i++) {
            PopupMenu popupMenu = (PopupMenu) this.trayIcon.getPopupMenu().getItem(i);
            popupMenu.getItem(0).setEnabled(connect);
        }
    }

    /**
     * Sets enabled/disabled state to all 'Disconnect' menu items.
     * 
     * @param disconnect if <code>true</code>, enables the menu items; if <code>false</code>, disables them
     */
    public void setAllDisonnectMenuItems(boolean disconnect) {
        for (int i = 0; i < this.openVPNConfigCount; i++) {
            PopupMenu popupMenu = (PopupMenu) this.trayIcon.getPopupMenu().getItem(i);
            popupMenu.getItem(1).setEnabled(disconnect);
        }
    }

    /**
     * Sets a loaded icon as tray icon.
     * 
     * @see org.openvpnsystray.gui.utils.IconProvider
     * 
     * @param icon the icon number
     */
    public void setTrayIcon(int icon) {
        switch (icon) {
        case 0:
            this.trayIcon.setImage(IconProvider.OPENVPN_SYSTRAY_IMAGE);
            break;
        case 1:
            this.trayIcon.setImage(IconProvider.CONNECTED_IMAGE);
            break;
        case 2:
            this.trayIcon.setImage(IconProvider.CONNECTING_IMAGE);
            break;
        case 3:
            this.trayIcon.setImage(IconProvider.DISCONNECTED_IMAGE);
            break;
        case 4:
            this.trayIcon.setImage(IconProvider.RECONNECTING_IMAGE);
            break;
        default:
            SystemTrayIcon.logger.log(Level.WARNING, "wrong tray icon number"); //$NON-NLS-1$
            break;
        }
    }

}
