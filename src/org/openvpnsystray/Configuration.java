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

import java.beans.ConstructorProperties;
import java.io.File;
import java.io.Serializable;
import java.util.Locale;

/**
 * This class represents the configuration file of OpenVPN SysTray.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: Configuration.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class Configuration implements Serializable {

    /**
     * The serialVersionUID
     */
    private static final long serialVersionUID = 4549837895711722815L;

    /**
     * Is empty master password allowed
     */
    private boolean emptyMasterPassword;

    /**
     * The OpenVPN configuration file directory
     */
    private File openVPNConfigDirectory;

    /**
     * The OpenVPN executable
     */
    private File openVPNExecutable;

    /**
     * The password database file
     */
    private File passwordDatabase;

    /**
     * The locale
     */
    private Locale locale;

    /**
     * The configuration file
     */
    private transient File configFile;

    /**
     * Creates a new {@link Configuration}.
     * 
     * @param openVPNExecutable the OpenVPN executable
     * @param openVPNConfigDirectory the OpenVPN configuration file directory
     * @param passwordDatabase the password database file
     * @param emptyMasterPassword true if empty master password is allowed
     * @param locale the locale
     * @param configFile the configuration file
     */
    @ConstructorProperties( { "openVPNExecutable", "openVPNConfigDirectory", "passwordDatabase", "emptyMasterPassword",
            "locale", "configFile" })
    public Configuration(File openVPNExecutable,
                         File openVPNConfigDirectory,
                         File passwordDatabase,
                         boolean emptyMasterPassword,
                         Locale locale,
                         File configFile) {
        this.openVPNExecutable = openVPNExecutable;
        this.openVPNConfigDirectory = openVPNConfigDirectory;
        this.passwordDatabase = passwordDatabase;
        this.emptyMasterPassword = emptyMasterPassword;
        this.locale = locale;
        this.configFile = configFile;
    }

    /**
     * Returns the OpenVPN configuration file directory.
     * 
     * @return the OpenVPN configuration file directory
     */
    public File getOpenVPNConfigDirectory() {
        return this.openVPNConfigDirectory;
    }

    /**
     * Returns the OpenVPN executable.
     * 
     * @return the OpenVPN executable
     */
    public File getOpenVPNExecutable() {
        return this.openVPNExecutable;
    }

    /**
     * Returns the password database.
     * 
     * @return the password database
     */
    public File getPasswordDatabase() {
        return this.passwordDatabase;
    }

    /**
     * Returns the locale.
     * 
     * @return the locale
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Returns the configuration file.
     * 
     * @return the configuration file
     */
    public File getConfigFile() {
        return this.configFile;
    }

    /**
     * Returns true if empty master password is allowed.
     * 
     * @return true if empty master password is allowed
     */
    public boolean isEmptyMasterPassword() {
        return this.emptyMasterPassword;
    }

    /**
     * Sets whether empty master password is allowed.
     * 
     * @param emptyMasterPassword true if empty master password is allowed
     */
    public void setEmptyMasterPassword(boolean emptyMasterPassword) {
        this.emptyMasterPassword = emptyMasterPassword;
    }

    /**
     * Sets the OpenVPN configuration file directory.
     * 
     * @param openVPNConfigDirectory the OpenVPN configuration file directory to set
     */
    public void setOpenVPNConfigDirectory(File openVPNConfigDirectory) {
        this.openVPNConfigDirectory = openVPNConfigDirectory;
    }

    /**
     * Sets the OpenVPN executable.
     * 
     * @param openVPNExecutable the OpenVPN executable to set
     */
    public void setOpenVPNExecutable(File openVPNExecutable) {
        this.openVPNExecutable = openVPNExecutable;
    }

    /**
     * Sets the password database.
     * 
     * @param passwordDatabase the password database to set
     */
    public void setPasswordDatabase(File passwordDatabase) {
        this.passwordDatabase = passwordDatabase;
    }

    /**
     * Sets the locale.
     * 
     * @param locale the locale to set
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Sets the configuration file.
     * 
     * @param configFile the configuration file to set
     */
    public void setConfigFile(File configFile) {
        this.configFile = configFile;
    }

}
