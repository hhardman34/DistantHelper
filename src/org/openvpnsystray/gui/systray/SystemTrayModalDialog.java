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

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.Window;

import javax.swing.JDialog;

/**
 * {@link SystemTrayModalDialog} extends the standard {@link JDialog} by the capability of getting dialogs which can be
 * considered as modal to a system tray icon. <br>
 * <br>
 * This implementation offers two modality types. The first one removes the tray icon from the system tray while the
 * dialog is shown and another one removes the popup menu from the tray icon.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SystemTrayModalDialog.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public abstract class SystemTrayModalDialog extends JDialog {

    /**
     * The serialVersionUID
     */
    private static final long serialVersionUID = -2460415590985159970L;

    /**
     * This modality type does nothing to the system tray icon.
     */
    public static final int MODELESS = 0;

    /**
     * This modality type removes the tray icon from the system tray while the dialog is shown.
     */
    public static final int SYSTEM_TRAY_ICON_REMOVE = 1;

    /**
     * This modality type removes the popup menu from the tray icon while the dialog is shown.
     */
    public static final int POPUP_MENU_REMOVE = 2;

    /**
     * The dialog has been closed by OK.
     */
    public static final int OK_OPTION = 0;

    /**
     * The dialog has been closed by CANCEL.
     */
    public static final int CANCEL_OPTION = 1;

    /**
     * The exitValue
     */
    private int exitValue;

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param systemTray the system tray
     * @param trayIcon the tray icon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(SystemTray systemTray, TrayIcon trayIcon, int systemTrayModalityType) {
        super();
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Dialog owner,
                                 boolean modal,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, modal);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     * @param gc the graphics configuration
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Dialog owner,
                                 String title,
                                 boolean modal,
                                 GraphicsConfiguration gc,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title, modal, gc);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Dialog owner,
                                 String title,
                                 boolean modal,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title, modal);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Dialog owner,
                                 String title,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Dialog owner, SystemTray systemTray, TrayIcon trayIcon, int systemTrayModalityType) {
        super(owner);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Frame owner,
                                 boolean modal,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, modal);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     * @param gc the graphics configuration
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Frame owner,
                                 String title,
                                 boolean modal,
                                 GraphicsConfiguration gc,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title, modal, gc);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Frame owner,
                                 String title,
                                 boolean modal,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title, modal);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Frame owner,
                                 String title,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Frame owner, SystemTray systemTray, TrayIcon trayIcon, int systemTrayModalityType) {
        super(owner);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param modalityType the modality type
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Window owner,
                                 ModalityType modalityType,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, modalityType);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modalityType the modality type
     * @param gc the graphics configuration
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Window owner,
                                 String title,
                                 ModalityType modalityType,
                                 GraphicsConfiguration gc,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title, modalityType, gc);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modalityType the modality type
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Window owner,
                                 String title,
                                 ModalityType modalityType,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title, modalityType);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Window owner,
                                 String title,
                                 SystemTray systemTray,
                                 TrayIcon trayIcon,
                                 int systemTrayModalityType) {
        super(owner, title);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    public SystemTrayModalDialog(Window owner, SystemTray systemTray, TrayIcon trayIcon, int systemTrayModalityType) {
        super(owner);
        init(systemTray, trayIcon, systemTrayModalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     */
    public SystemTrayModalDialog() {
        super();
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     */
    public SystemTrayModalDialog(Dialog owner, boolean modal) {
        super(owner, modal);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     * @param gc the graphics configuration
     */
    public SystemTrayModalDialog(Dialog owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     */
    public SystemTrayModalDialog(Dialog owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     */
    public SystemTrayModalDialog(Dialog owner, String title) {
        super(owner, title);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     */
    public SystemTrayModalDialog(Dialog owner) {
        super(owner);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     */
    public SystemTrayModalDialog(Frame owner, boolean modal) {
        super(owner, modal);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     * @param gc the graphics configuration
     */
    public SystemTrayModalDialog(Frame owner, String title, boolean modal, GraphicsConfiguration gc) {
        super(owner, title, modal, gc);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modal if <code>true</code>, a modal dialog is returned; if <code>false</code>, a non-modal dialog is
     *            returned
     */
    public SystemTrayModalDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     */
    public SystemTrayModalDialog(Frame owner, String title) {
        super(owner, title);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     */
    public SystemTrayModalDialog(Frame owner) {
        super(owner);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param modalityType the modality type
     */
    public SystemTrayModalDialog(Window owner, ModalityType modalityType) {
        super(owner, modalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modalityType the modality type
     * @param gc the graphics configuration
     */
    public SystemTrayModalDialog(Window owner, String title, ModalityType modalityType, GraphicsConfiguration gc) {
        super(owner, title, modalityType, gc);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     * @param modalityType the modality type
     */
    public SystemTrayModalDialog(Window owner, String title, ModalityType modalityType) {
        super(owner, title, modalityType);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     * @param title the dialog title
     */
    public SystemTrayModalDialog(Window owner, String title) {
        super(owner, title);
    }

    /**
     * Creates a new {@link SystemTrayModalDialog}.
     * 
     * @param owner the {@link Window} from which the dialog is displayed
     */
    public SystemTrayModalDialog(Window owner) {
        super(owner);
    }

    /**
     * Initialize the dialog.
     * 
     * @param systemTray the system tray
     * @param trayIcon the tray icon
     * @param systemTrayModalityType the system tray modality type
     */
    private void init(SystemTray systemTray, TrayIcon trayIcon, int systemTrayModalityType) {
        if (this.getModalityType() == ModalityType.MODELESS) {
            this.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
        }
        this.addWindowListener(new SystemTrayModalDialogWindowListener(systemTray, trayIcon, systemTrayModalityType));
    }

    /**
     * Returns the exit value.
     * 
     * @return the exit value
     */
    public int getExitValue() {
        return this.exitValue;
    }

    /**
     * Sets the exit value.
     * 
     * @param exitValue the exit value to set
     */
    public void setExitValue(int exitValue) {
        this.exitValue = exitValue;
    }

    /**
     * Makes the dialog visible and interrupts the control flow until the dialog has been closed. Returns the exit
     * value.
     * 
     * @return the exit value
     */
    public abstract int showDialog();
}
