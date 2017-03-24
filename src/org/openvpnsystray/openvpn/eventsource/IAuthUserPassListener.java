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
package org.openvpnsystray.openvpn.eventsource;

import java.util.EventListener;

/**
 * {@link IAuthUserPassListener} is a listener interface for receiving authentication username password events. The
 * class that is interested in processing an authentication username password event implements this interface, and the
 * object created with that class is registered with a component, using the component's
 * <code>addAuthUserPassListener</code> method. When the authentication username password event occurs, that object's
 * actionPerformed method is invoked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: IAuthUserPassListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public interface IAuthUserPassListener extends EventListener {

    /**
     * Invokes when an authentication username password event occurs.
     * 
     * @param authUserPassEvent the authentication username password event
     */
    public void actionPerformed(AuthUserPassEvent authUserPassEvent);
}
