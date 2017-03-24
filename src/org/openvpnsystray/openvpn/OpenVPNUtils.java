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
import java.io.FileFilter;
import java.util.LinkedList;
import java.util.List;

/**
 * {@link OpenVPNUtils} is an utility class containing methods concerning OpenVPN.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id$)
 */
public class OpenVPNUtils {

	/**
	 * The OpenVPN configuration file extension
	 */
	public static final String OPENVPN_FILE_EXTENSION = ".ovpn"; //$NON-NLS-1$
	
	/**
	 * Returns the list of OpenVPN configuration files in the given directory.
	 *
	 * @param openVPNConfigDirectory the OpenVPN configuration file directory
	 * @param openVPNExecutable the OpenVPN executable
	 * @return list of OpenVPN configuration files in the given directory
	 */
	public static List<OpenVPNConfig> searchOpenVPNConfigFiles(File openVPNConfigDirectory, File openVPNExecutable) {
		List<OpenVPNConfig> openVPNConfigs = new LinkedList<OpenVPNConfig>();
		FileFilter fileFilter = new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.getName().toLowerCase().endsWith(OPENVPN_FILE_EXTENSION);
			}
			
		};
		File files[] = openVPNConfigDirectory.listFiles(fileFilter);
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			openVPNConfigs.add(new OpenVPNConfig(file, openVPNExecutable));
		}
		return openVPNConfigs;
	}
}
