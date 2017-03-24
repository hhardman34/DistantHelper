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
import org.openvpnsystray.gui.utils.IconProvider;
import org.openvpnsystray.openvpn.eventsource.ISequenceCompleteListener;
import org.openvpnsystray.openvpn.eventsource.SequenceCompleteEvent;

/**
 * {@link SequenceCompleteListener} implements the action that should be done when OpenVPN link is engaged.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SequenceCompleteListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SequenceCompleteListener implements ISequenceCompleteListener {

    /**
     * The system tray icon
     */
    private SystemTrayIcon systemTrayIcon;

    /**
     * Creates a new {@link SequenceCompleteListener}.
     * 
     * @param systemTrayIcon the system tray icon
     */
    public SequenceCompleteListener(SystemTrayIcon systemTrayIcon) {
        this.systemTrayIcon = systemTrayIcon;
    }

    @Override
    public void actionPerformed(@SuppressWarnings("unused")SequenceCompleteEvent iSCompleteEvent) {
        this.systemTrayIcon.setTrayIcon(IconProvider.CONNECTED_ICON);
    }

}
