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
 * {@link ISequenceCompleteListener} is a listener interface for receiving sequence complete events. The class that is
 * interested in processing a sequence complete event implements this interface, and the object created with that class
 * is registered with a component, using the component's <code>addSequenceCompleteListener</code> method. When the
 * sequence complete event occurs, that object's actionPerformed method is invoked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: ISequenceCompleteListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public interface ISequenceCompleteListener extends EventListener {

    /**
     * Invokes when a sequence complete event occurs.
     * 
     * @param iSCompleteEvent the authentication password event
     */
    public void actionPerformed(SequenceCompleteEvent iSCompleteEvent);
}
