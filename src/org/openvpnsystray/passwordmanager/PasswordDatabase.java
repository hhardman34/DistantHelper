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
package org.openvpnsystray.passwordmanager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

/**
 * {@link PasswordDatabase} is just a wrapper for serialization purpose.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: PasswordDatabase.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class PasswordDatabase extends HashMap<UUID, PasswordEntry> implements Serializable {

    /**
     * UID for serialization purpose
     */
    private static final long serialVersionUID = -4818109634808493428L;

}