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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.openvpnsystray.openvpn.OpenVPNConfig;
import org.openvpnsystray.passwordmanager.PasswordManager;

/**
 * {@link ExitActionListener} implements the action that should be done when the 'Exit' menu item has being clicked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: ExitActionListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class ExitActionListener implements ActionListener{
    
    /**
     * The list of OpenVPN configuration objects
     */
    private List<OpenVPNConfig> openVPNConfigs;

    /**
     * Creates a new {@link ExitActionListener}.
     * 
     * @param openVPNConfigs the list of OpenVPN configuration objects
     */
    public ExitActionListener(List<OpenVPNConfig> openVPNConfigs) {
        this.openVPNConfigs = openVPNConfigs;
    }

	@Override
	public void actionPerformed(@SuppressWarnings("unused")ActionEvent actionEvent) {
	    for (OpenVPNConfig openVPNConfig : this.openVPNConfigs) {
	        openVPNConfig.disconnect();
        }
		PasswordManager.getPasswordManager().serializePasswordDatabasse();
		System.exit(0);
	}

}