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

/**
 * {@link PasswordEntry} is one entry in the password database. An entry containing several information, namely
 * username, password and description.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: PasswordEntry.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class PasswordEntry implements Serializable {

    /**
     * UID for serialization purpose
     */
    private static final long serialVersionUID = 8882179298696277988L;

    /**
     * The username
     */
    private String userName;

    /**
     * The password
     */
    private char[] password;

    /**
     * The description
     */
    private String description;

    /**
     * Creates a new {@link PasswordEntry}.
     * 
     * @param userName the username
     * @param password the password
     * @param description the description
     */
    public PasswordEntry(String userName, char[] password, String description) {
        this.userName = userName;
        this.password = password;
        this.description = description;
    }

    /**
     * Returns the username.
     * 
     * @return the username
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Returns the password.
     * 
     * @return the password
     */
    public char[] getPassword() {
        return this.password;
    }

    /**
     * Returns the description.
     * 
     * @return the description
     */
    public String getDescription() {
        return this.description;
    }
}
