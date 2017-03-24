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
package org.openvpnsystray.gui.userpassdialog;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;

/**
 * {@link SaveAuthListener} implements the action that should be done when the user clicks on the save authentication
 * label.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SaveAuthListener.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SaveAuthListener implements MouseListener {
	
	/**
	 * The save authentication checkbox
	 */
	private JCheckBox checkbox;

	/**
	 * Creates a new {@link SaveAuthListener}.
	 * 
	 * @param checkbox the save authentication checkbox
	 */
	public SaveAuthListener(JCheckBox checkbox) {
		this.checkbox = checkbox;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent mouseEvent) {
		JLabel label = (JLabel) mouseEvent.getSource();
		if (label.isEnabled()) {
			if (this.checkbox.isSelected()) {
				this.checkbox.setSelected(false);
			} else {
				this.checkbox.setSelected(true);
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(@SuppressWarnings("unused")MouseEvent mouseEvent) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(@SuppressWarnings("unused")MouseEvent mouseEvent) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(@SuppressWarnings("unused")MouseEvent mouseEvent) {
		// nothing to do
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(@SuppressWarnings("unused")MouseEvent mouseEvent) {
		// nothing to do
	}

}
