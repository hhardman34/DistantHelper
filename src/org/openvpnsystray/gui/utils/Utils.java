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

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.JTextField;

import sun.security.provider.SecureRandom;

/**
 * {@link Utils} provides some handy methods for graphical user interface components.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: Utils.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class Utils {

	/**
	 * The empty string
	 */
	private static final String EMPTY_STRING = ""; //$NON-NLS-1$

	/**
	 * Sets the given {@link Window} to the middle of the screen.
	 *
	 * @param window the window to be set
	 */
	public static void setFrameToMiddleOfTheScreen(Window window) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenMiddleX = screenSize.width / 2;
		int screenMiddleY = screenSize.height / 2;
		int x = screenMiddleX - (window.getWidth() / 2);
		int y = screenMiddleY - (window.getHeight() / 2);
		window.setBounds(x, y, window.getWidth(), window.getHeight());
	}
	
	/**
	 * Deletes text from {@link JTextField}s in a secure way.
	 *
	 * @param textField the text field
	 * @param length length of text in the text field
	 */
	public static void secureTextDeletionFromTextField(JTextField textField, int length) {
		byte[] bytes = new byte[length];
		new SecureRandom().engineNextBytes(bytes);
		String str = new String(bytes, 0, length);
		textField.setText(str);
		textField.setText(EMPTY_STRING);
	}
}
