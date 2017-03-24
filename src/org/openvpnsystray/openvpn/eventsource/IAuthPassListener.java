/******************************************************************************
 * Copyright (c) 2007 Stefan Franke                                           *
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
package org.openvpnsystray.openvpn.eventsource;

import java.util.EventListener;

/**
 * {@link IAuthPassListener} is a listener interface for receiving authentication password events. The class that is
 * interested in processing an authentication password event implements this interface, and the object created with that
 * class is registered with a component, using the component's <code>addAuthPassListener</code> method. When the
 * authentication password event occurs, that object's actionPerformed method is invoked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: IAuthPassListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public interface IAuthPassListener extends EventListener {

    /**
     * Invokes when an authentication password event occurs.
     * 
     * @param authPassEvent the authentication password event
     */
    public void actionPerformed(AuthPassEvent authPassEvent);
}
