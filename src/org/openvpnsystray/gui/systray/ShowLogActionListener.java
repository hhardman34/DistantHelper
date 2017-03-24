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
package org.openvpnsystray.gui.systray;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.openvpnsystray.gui.logframe.LogFrame;

/**
 * {@link ShowLogActionListener} implements the action that should be done when a 'Show Log' menu item has being
 * clicked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: ShowLogActionListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class ShowLogActionListener implements ActionListener {

    /**
     * The log frame
     */
    private LogFrame logFrame;

    /**
     * Creates a new {@link ShowLogActionListener}.
     * 
     * @param logFrame the log frame
     */
    public ShowLogActionListener(LogFrame logFrame) {
        this.logFrame = logFrame;
    }

    @Override
    public void actionPerformed(@SuppressWarnings("unused")ActionEvent actionEvent) {
        this.logFrame.setVisible(true);
    }

}
