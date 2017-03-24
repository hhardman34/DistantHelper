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
 package org.openvpnsystray.utils;

import java.io.File;
import java.util.Map;

/**
 * {@link OSUtils} is an utility class containing methods concerning operating system functions.
 * 
 * @author Stefan Franke
 * @date 04.04.2008
 * @version ($Id: OSUtils.java 6 2008-04-05 11:21:53Z frankesn $)
 */

public class OSUtils {
    
    /**
     * Returns true if the user is superuser.
     *
     * @return true if the user is superuser
     */
    public static boolean isSuperUser() {
        if (System.getProperty("os.name").startsWith("Win")) {
            System.out.print("Windows");
        } else if (System.getProperty("os.name").startsWith("Lin")) {
            System.out.print("Linux");
            System.out.print(System.getProperty("os.username"));
            Map<String, String> env = System.getenv();
            for (String envName : env.keySet()) {
                System.out.format("%s=%s%n", envName, env.get(envName));
            }
        } else {
            System.out.print("unknown operation system");
        }
        return true;
    }
}