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
package org.openvpnsystray.openvpn;

import java.io.File;

/**
 * {@link OpenVPNConfig}
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id$)
 */
public class OpenVPNConfig {
	
	/**
	 * The empty string
	 */
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$

	/**
	 * The OpenVPN configuration file
	 */
	private File configFile;
	
	/**
	 * The OpenVPN file name without suffix
	 */
	private String name;
	
	/**
	 * The OpenVPN working directory
	 */
	private File workingDirectory;
	
	/**
	 * The openVPNConnection
	 */
	private OpenVPNConnection openVPNConnection;

	/**
	 * Creates a new {@link OpenVPNConfig}.
	 * 
	 * @param configFile the OpenVPN configuration file
	 * @param openVPNExecutable the OpenVPN executable
	 */
	public OpenVPNConfig(File configFile, File openVPNExecutable) {
	    super();
		this.configFile = configFile;
		this.name = configFile.getName().replaceFirst(OpenVPNUtils.OPENVPN_FILE_EXTENSION, EMPTY_STRING);
		this.workingDirectory = configFile.getParentFile();
		this.openVPNConnection = new OpenVPNConnection(this, openVPNExecutable);
	}
	
	/**
	 * Connects to an OpenVPN server using this configuration object.
	 */
	public void connect() {
		if (!this.openVPNConnection.isConnected()) {
		    this.openVPNConnection.connect();
			return;
		}
	}
	
	/**
	 * Disconnects an open OpenVPN link, if a link using this configuration object is engaged.
	 */
	public void disconnect() {
		this.openVPNConnection.disconnect();
	}

    /**
     * Returns the OpenVPN configuration file.
     *
     * @return the OpenVPN configuration file
     */
    public File getConfigFile() {
        return this.configFile;
    }

    /**
     * Returns the OpenVPN file name without suffix.
     *
     * @return the OpenVPN file name without suffix
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the OpenVPN connection.
     *
     * @return the OpenVPN connection
     */
    public OpenVPNConnection getOpenVPNConnection() {
        return this.openVPNConnection;
    }

    /**
     * Returns the OpenVPN working directory.
     *
     * @return the OpenVPN working directory
     */
    public File getWorkingDirectory() {
        return this.workingDirectory;
    }
}
