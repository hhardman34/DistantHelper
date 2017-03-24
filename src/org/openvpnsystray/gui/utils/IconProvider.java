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
package org.openvpnsystray.gui.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.image4j.codec.ico.ICODecoder;

/**
 * {@link IconProvider} loads and provides tray icons.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: IconProvider.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class IconProvider {

    /**
     * The logger
     */
    private static final Logger LOGGER = Logger.getLogger("openvpnsystray"); //$NON-NLS-1$

    /**
     * The path to OpenVPN SysTray icon
     */
    private static final String OPENVPN_SYSTRAY_ICO = "images/openvpn-gui.ico"; //$NON-NLS-1$

    /**
     * The path to connected icon
     */
    private static final String CONNECTED_ICO = "images/connected.ico"; //$NON-NLS-1$

    /**
     * The path to connecting icon
     */
    private static final String CONNECTING_ICO = "images/connecting.ico"; //$NON-NLS-1$

    /**
     * The path to disconnected icon
     */
    private static final String DISCONNECTED_ICO = "images/disconnected.ico"; //$NON-NLS-1$
	
	/**
	 * The path to reconnecting icon
	 */
	private static final String RECONNECTING_ICO = "images/reconnecting.ico"; //$NON-NLS-1$

	/**
	 * The number of OpenVPN SysTray icon
	 */
	public static final int OPENVPN_SYSTRAY_ICON = 0;
	
	/**
	 * The number of connected icon
	 */
	public static final int CONNECTED_ICON = 1;
	
	/**
	 * The number of connecting icon
	 */
	public static final int CONNECTING_ICON = 2;
	
	/**
	 * The number of disconnected icon
	 */
	public static final int DISCONNECT_ICON = 3;
	
	/**
	 * The number of reconnecting icon
	 */
	public static final int RECONNECTING_ICON = 4;
	
	/**
	 * The OpenVPN SysTray image
	 */
	public static Image OPENVPN_SYSTRAY_IMAGE;
	
	/**
	 * The connected image
	 */
	public static Image CONNECTED_IMAGE;
	
	/**
	 * The connecting image
	 */
	public static Image CONNECTING_IMAGE;
	
	/**
	 * The disconnected image
	 */
	public static Image DISCONNECTED_IMAGE;
	
	/**
	 * The reconnecting image
	 */
	public static Image RECONNECTING_IMAGE;
	
	/**
	 * Loads the specified icons.
	 */
	public static void loadTrayIcons() {
		try {
			File openvpnIconFile = new File(OPENVPN_SYSTRAY_ICO);
			File connectedIconFile = new File(CONNECTED_ICO);
			File connectingIconFile = new File(CONNECTING_ICO);
			File disconnectedIconFile = new File(DISCONNECTED_ICO);
			File reconnectingIconFile = new File(RECONNECTING_ICO);
			List<BufferedImage> bufferedImages = ICODecoder.read(openvpnIconFile);
			OPENVPN_SYSTRAY_IMAGE = bufferedImages.get(0);
			bufferedImages = ICODecoder.read(connectedIconFile);
			CONNECTED_IMAGE = bufferedImages.get(0);
			bufferedImages = ICODecoder.read(connectingIconFile);
			CONNECTING_IMAGE = bufferedImages.get(0);
			bufferedImages = ICODecoder.read(disconnectedIconFile);
			DISCONNECTED_IMAGE = bufferedImages.get(0);
			bufferedImages = ICODecoder.read(reconnectingIconFile);
			RECONNECTING_IMAGE = bufferedImages.get(0);
		} catch (IOException e) {
		    LOGGER.log(Level.SEVERE, "IO error during icon loading", e); //$NON-NLS-1$
		}
	}
}
