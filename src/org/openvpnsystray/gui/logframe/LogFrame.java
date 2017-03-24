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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import org.openvpnsystray.gui.utils.Utils;

/**
 * Instances of {@link LogFrame} display a simple frame containing a text area for the log messages and an 'OK' button
 * to close the window. <br>
 * <br>
 * The log message are gathered from an {@link Observable} object which need to be given to the constructor. When new
 * log message are thrown by the {@link Observable} object using <code>Observable.setChanged()</code> and
 * {@link Observable#notifyObservers(Object)} the {@link LogFrame} will display them.
 * 
 * @see java.util.Observable
 * @see java.util.Observer
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: LogFrame.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class LogFrame extends JFrame implements Observer {

    /**
     * UID for serialization purpose
     */
    private static final long serialVersionUID = 6804877256650698163L;

    /**
     * Width of the frame
     */
    private static final int FRAME_WIDTH = 600;

    /**
     * Height of the frame
     */
    private static final int FRAME_HEIGHT = 500;

    /**
     * Text area containing the log messages
     */
    private JTextArea textArea;

    /**
     * The object that spawns log messages
     */
    private Observable logMessageProvider;

    /**
     * Creates a new {@link LogFrame}.
     * 
     * @param title the frame title
     * @param okButtonText the 'OK' buttton text
     * @param observalbe the object that spawns log messages
     */
    public LogFrame(String title, String okButtonText, Observable observalbe) {
        this(title, okButtonText, null, observalbe);
    }

    /**
     * Creates a new {@link LogFrame}.
     * 
     * @param title the frame title
     * @param okButtonText the 'OK' buttton text
     * @param iconImage the frame's icon
     * @param observalbe the object that spawns log messages
     */
    public LogFrame(String title, String okButtonText, Image iconImage, Observable observalbe) {
        super(title);
        this.logMessageProvider = observalbe;

        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        Utils.setFrameToMiddleOfTheScreen(this);

        BorderLayout borderLayout = new BorderLayout();

        JPanel panel = new JPanel();
        // panel.setBackground(new Color(212,208,200));

        JPanel centerPanel = new JPanel();
        this.textArea = new JTextArea();
        this.textArea.setAutoscrolls(true);
        this.textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(this.textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
            ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Dimension preferredSize = new Dimension(FRAME_WIDTH - 20, FRAME_HEIGHT - 70);
        scrollPane.setPreferredSize(preferredSize);
        scrollPane.setAutoscrolls(true);
        centerPanel.add(scrollPane);

        JPanel southPanel = new JPanel();
        JButton button = new JButton(okButtonText);
        button.addActionListener(new LogFrameOKActionListener(this));
        southPanel.add(button);

        panel.setLayout(borderLayout);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);
        this.add(panel);

        this.logMessageProvider.addObserver(this);
        this.setIconImage(iconImage);
        this.addWindowListener(new LogFrameWindowListener());
        this.setResizable(false);
        this.setVisible(false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable observable, Object object) {
        if (observable == this.logMessageProvider) {
            this.textArea.append((String) object);
            this.textArea.setCaretPosition(this.textArea.getText().length());
        }
    }
}
