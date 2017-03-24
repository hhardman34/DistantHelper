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

import java.io.File;
import java.util.logging.Logger;

import org.openvpnsystray.utils.Serialize;

/**
 * {@link ConfigurationProvider}
 * 
 * @author Stefan Franke
 * @date 08.02.2008
 * @version ($Id: ConfigurationProvider.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class ConfigurationProvider {

    /**
     * The logger
     */
    private static Logger logger = LoggerProvider.getLogger();

    /**
     * The configuration object
     */
    private static Configuration configuration = null;

    /**
     * Loads an OpenVPN SysTray configuration file.
     * 
     * @param configFile the configuration file to load
     */
    public static void loadConfigurationFromFile(File configFile) {
        ConfigurationProvider.configuration = (Configuration) Serialize.readObjectFromXMLFile(configFile);
    }

    /**
     * Saves the OpenVPN SysTray configuration object.
     * 
     * @param configFile the configuration object
     */
    public static void saveConfigToFile(File configFile) {
        if (ConfigurationProvider.configuration != null) {
            Serialize.writeObjectToXMLFile(configFile, ConfigurationProvider.configuration);
        } else {
            logger.throwing(ConfigurationProvider.class.getName(),
                LoggerProvider.getCurrentMethodName(),
                new RuntimeException("There is none configuration file loaded that could be saved.")); //$NON-NLS-1$
        }
    }

    /**
     * Returns the configuration object or <code>null</code>, if no configuration file has been successfully loaded
     * using {@link ConfigurationProvider#loadConfigurationFromFile(File)}.
     * 
     * @return the configuration
     */
    public static Configuration getConfiguration() {
        return ConfigurationProvider.configuration;
    }
}
