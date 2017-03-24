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

import java.util.EventObject;

/**
 * {@link SequenceCompleteEvent} is sent when an OpenVPN link is engaged.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SequenceCompleteEvent.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SequenceCompleteEvent extends EventObject {

    /**
     * The serialVersionUID
     */
    private static final long serialVersionUID = -8922271918065605275L;

    /**
     * Creates a new {@link SequenceCompleteEvent}.
     * 
     * @param source the source
     */
    public SequenceCompleteEvent(Object source) {
        super(source);
    }
}
