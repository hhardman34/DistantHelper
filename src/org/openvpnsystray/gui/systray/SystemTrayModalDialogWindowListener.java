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
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * {@link SystemTrayModalDialogWindowListener} implements the modal behavior of the system tray modal dialog.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SystemTrayModalDialogWindowListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SystemTrayModalDialogWindowListener implements WindowListener {
	
	/**
	 * An empty popup menu
	 */
	private static final PopupMenu POPUP_MENU = new PopupMenu();

	/**
	 * The system tray
	 */
	private SystemTray systemTray;

	/**
	 * The tray icon
	 */
	private TrayIcon trayIcon;
    
    /**
     * The system tray modality type
     */
    private int systemTrayModalityType;

	/**
	 * The popup menu
	 */
	private PopupMenu popupMenu;

	/**
	 * Creates a new {@link SystemTrayModalDialogWindowListener}.
	 * 
	 * @param systemTray the system tray
	 * @param trayIcon the tray icon
	 * @param systemTrayModalityType the system tray modality type
	 */
	public SystemTrayModalDialogWindowListener(SystemTray systemTray,
			TrayIcon trayIcon, int systemTrayModalityType) {
		this.systemTray = systemTray;
		this.trayIcon = trayIcon;
		this.systemTrayModalityType = systemTrayModalityType;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(@SuppressWarnings("unused")WindowEvent windowEvent) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(@SuppressWarnings("unused")WindowEvent windowEvent) {
		switch (this.systemTrayModalityType) {
        case SystemTrayModalDialog.MODELESS:
            break;
		case SystemTrayModalDialog.SYSTEM_TRAY_ICON_REMOVE:
			try {
					this.systemTray.add(this.trayIcon);
				} catch (AWTException e) {
					e.printStackTrace();
				}
			break;
		case SystemTrayModalDialog.POPUP_MENU_REMOVE:
			this.trayIcon.setPopupMenu(this.popupMenu);
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(@SuppressWarnings("unused")WindowEvent windowEvent) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(@SuppressWarnings("unused")WindowEvent windowEvent) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(@SuppressWarnings("unused")WindowEvent windowEvent) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(@SuppressWarnings("unused")WindowEvent windowEvent) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(@SuppressWarnings("unused")WindowEvent windowEvent) {
		switch (this.systemTrayModalityType) {
		case SystemTrayModalDialog.MODELESS:
		    break;
		case SystemTrayModalDialog.SYSTEM_TRAY_ICON_REMOVE:
			this.systemTray.remove(this.trayIcon);
			break;
		case SystemTrayModalDialog.POPUP_MENU_REMOVE:
			this.popupMenu = this.trayIcon.getPopupMenu();
			this.trayIcon.setPopupMenu(POPUP_MENU);
			break;
		default:
			break;
		}
	}

}
