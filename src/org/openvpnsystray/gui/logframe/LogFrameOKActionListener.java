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
package org.openvpnsystray.gui.logframe;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * {@link LogFrameOKActionListener} implements the actions that should be done when log frame's 'OK' button has being
 * clicked.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: LogFrameOKActionListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class LogFrameOKActionListener implements ActionListener {

    /**
     * The log frame
     */
    private LogFrame logFrame;

    /**
     * Creates a new {@link LogFrameOKActionListener}.
     * 
     * @param logFrame the log frame
     */
    public LogFrameOKActionListener(LogFrame logFrame) {
        this.logFrame = logFrame;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(@SuppressWarnings("unused")ActionEvent actionEvent) {
        this.logFrame.setVisible(false);
    }

}
