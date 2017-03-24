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
package org.openvpnsystray;

import java.util.logging.Logger;

/**
 * {@link LoggerProvider}
 * 
 * @author Stefan Franke
 * @date 08.02.2008
 * @version ($Id: LoggerProvider.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class LoggerProvider {

    /**
     * The logger
     */
    private static Logger logger = null;
    
    /**
     * The throwable object
     */
    private static Throwable throwable = new Throwable();

    /**
     * Loads a logger into the system's log manager.
     * 
     * @param name
     */
    public static void loadLogger(String name) {
        loadLogger(name, null);
    }

    /**
     * Loads an internationalized logger into the system's log manager.
     * 
     * @param name the name of the logger
     * @param resourceBundleName the resource bundle name
     */
    public static void loadLogger(String name, String resourceBundleName) {
        LoggerProvider.logger = java.util.logging.Logger.getLogger(name, resourceBundleName);
    }

    /**
     * Returns the logger object or <code>null</code>, if no logger has been successfully loaded using
     * {@link LoggerProvider#loadLogger(String)} or {@link LoggerProvider#loadLogger(String, String)}.
     * 
     * @return the logger
     */
    public static Logger getLogger() {
        return LoggerProvider.logger;
    }
    
    /**
     * Returns the name of the current executed method. 
     *
     * @return the name of the current executed method.
     */
    public static String getCurrentMethodName() {
        return LoggerProvider.throwable.getStackTrace()[1].getMethodName();
    }
}
