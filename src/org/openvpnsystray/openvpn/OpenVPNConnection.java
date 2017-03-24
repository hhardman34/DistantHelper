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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openvpnsystray.LoggerProvider;
import org.openvpnsystray.openvpn.eventsource.AuthPassEvent;
import org.openvpnsystray.openvpn.eventsource.AuthUserPassEvent;
import org.openvpnsystray.openvpn.eventsource.OpenVPNEventSource;
import org.openvpnsystray.openvpn.eventsource.SequenceCompleteEvent;
import org.openvpnsystray.openvpn.eventsource.TerminateEvent;
import org.openvpnsystray.passwordmanager.SecureCharArray;

/**
 * {@link OpenVPNConnection}
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id$)
 */
public class OpenVPNConnection extends OpenVPNEventSource {

    /**
     * The logger
     */
    static Logger logger = LoggerProvider.getLogger();

    /**
     * The AUTH_USER_PASS
     */
    private static final String AUTH_USER_PASS = "auth-user-pass"; //$NON-NLS-1$

    /**
     * The CONNECT
     */
    private static final String CONNECT = "connect"; //$NON-NLS-1$

    /**
     * The ENTER_AUTH_PASSWORD
     */
    private static final String ENTER_AUTH_PASSWORD = "Enter Auth Password:"; //$NON-NLS-1$

    /**
     * The ENTER_AUTH_USERNAME
     */
    private static final String ENTER_AUTH_USERNAME = "Enter Auth Username:"; //$NON-NLS-1$

    /**
     * The INITIALIZATION_SEQUENCE_COMPLETED
     */
    private static final String INITIALIZATION_SEQUENCE_COMPLETED = "Initialization Sequence Completed"; //$NON-NLS-1$

    /**
     * The OPENVPN_CONFIG_PARAMETER
     */
    private static final String OPENVPN_CONFIG_PARAMETER = " --config "; //$NON-NLS-1$

    /**
     * The authUserPass
     */
    private boolean authUserPass = false;

    /**
     * The connected
     */
    private boolean connected = false;

    /**
     * The connectedOpenVPNConfig
     */
    private OpenVPNConfig connectedOpenVPNConfig = null;

    /**
     * The errorStreamThread
     */
    private Thread errorStreamThread;

    /**
     * The exitSubProcessThread
     */
    private Thread exitSubProcessThread;

    /**
     * The inputStreamThread
     */
    private Thread inputStreamThread;

    /**
     * The openVPNConfig
     */
    private OpenVPNConfig openVPNConfig;

    /**
     * The openVPNExecutable
     */
    private File openVPNExecutable;

    /**
     * The openvpnProcess
     */
    Process openVPNProcess = null;

    /**
     * Creates a new {@link OpenVPNConnection}.
     * 
     * @param openVPNConfig the OpenVPN configuration object
     * @param openVPNExecutable the OpenVPN executable
     */
    public OpenVPNConnection(OpenVPNConfig openVPNConfig, File openVPNExecutable) {
        this.openVPNConfig = openVPNConfig;
        this.openVPNExecutable = openVPNExecutable;
        this.authUserPass = isAuthUserPass(openVPNConfig.getConfigFile());
    }

    /**
     * Returns the OpenVPN  Config.
     *
     * @return the OpenVPN Config
     */
    public OpenVPNConfig getOpenVPNConfig() {
        return this.openVPNConfig;
    }

    /**
     * Checks whether a OpenVPN link is engaged using the associated OpenVPN configuration object. Returns <code>true</code> if this is the case.
     * 
     * @return <code>true</code> if a OpenVPN link is engaged using the associated OpenVPN configuration object
     */
    public boolean isConnected() {
        return this.connected;
    }

    /**
     * Checks whether OpenVPN tries to connect using the associated OpenVPN configuration object. Returns <code>true</code> if this is the case.
     * 
     * @return <code>true</code> if OpenVPN tries to connect using the associated OpenVPN configuration object
     */
    public boolean isConnecting() {
        return !this.connected && this.connectedOpenVPNConfig != null;
    }

    /**
     * Passes the given password to OpenVPN, if an link is engaged.
     * <br><br>
     * Should be used as response of {@link AuthPassEvent}.
     * 
     * @param password the password
     */
    public void setPassword(char[] password) {
        this.sendMessageToSubProcess(password);
    }

    /**
     * Passes the given username to OpenVPN, if an link is engaged.
     * <br><br>
     * Should be used as response of {@link AuthUserPassEvent} followed by {@link OpenVPNConnection#setPassword(char[])}.
     * 
     * @param userName the username
     */
    public void setUserName(String userName) {
        this.sendMessageToSubProcess(userName.toCharArray());
    }

    /**
     * Checks whether the 'auth-user-pass' option is configured in the associated OpenVPN configuration file. Returns
     * <code>true</code> if this is the case.
     * 
     * @param file the OpenVPN configuration file
     * @return <code>true</code> if the 'auth-user-pass' option is configured in the associated OpenVPN configuration file
     */
    private boolean isAuthUserPass(File file) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String bufferLine;
            while (null != (bufferLine = bufferedReader.readLine())) {
                if (bufferLine.contains(AUTH_USER_PASS)) {
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e) {
            OpenVPNConnection.logger.log(Level.SEVERE, "OpenVPN configuration file could not be found", e); //$NON-NLS-1$
        } catch (IOException e) {
            OpenVPNConnection.logger.log(Level.SEVERE, "IO error while reading OpenVPN configuration file", e); //$NON-NLS-1$
        }
        return false;
    }

    /**
     * Sends a message to the subprocess.
     * 
     * @param password
     */
    private void sendMessageToSubProcess(char[] password) {
        try {
            this.openVPNProcess.getOutputStream().write(SecureCharArray.convertCharArrayToByteArray(password));
            this.openVPNProcess.getOutputStream().write(13);
            this.openVPNProcess.getOutputStream().flush();
        } catch (IOException e) {
            OpenVPNConnection.logger.log(Level.SEVERE, "IO error while passing messages to OpenVPN", e); //$NON-NLS-1$
        }
    }

    /**
     * Connects to an OpenVPN server using the associated configuration object.
     */
    public void connect() {
        if (this.openVPNExecutable == null) {
            OpenVPNConnection.logger.throwing(OpenVPNConnection.class.getName(), CONNECT, new RuntimeException(
                "OpenVPN executable is not set")); //$NON-NLS-1$
        }
        try {
            String command = this.openVPNExecutable.getAbsolutePath() + OPENVPN_CONFIG_PARAMETER
                    + this.openVPNConfig.getConfigFile().getAbsolutePath();
            StringTokenizer st = new StringTokenizer(command);
            String[] cmdarray = new String[st.countTokens()];
            for (int i = 0; st.hasMoreTokens(); i++) {
                cmdarray[i] = st.nextToken();
            }
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.directory(this.openVPNConfig.getWorkingDirectory());
            processBuilder.command(cmdarray);
            this.openVPNProcess = processBuilder.start();

        } catch (IOException e) {
            OpenVPNConnection.logger.log(Level.SEVERE, "IO error during OpenVPN startup", e); //$NON-NLS-1$
        }

        this.inputStreamThread = new Thread() {

            @Override
            public void run() {
                super.run();
                int bytes;
                byte[] buffer = new byte[8192];
                BufferedInputStream bufferedInputStream = (BufferedInputStream) OpenVPNConnection.this.openVPNProcess
                        .getInputStream();
                try {
                    while ((bytes = bufferedInputStream.read(buffer)) > 0) {
                        String str = new String(buffer, 0, bytes);
                        OpenVPNConnection.this.parseInputStream(str);
                    }
                } catch (IOException e) {
                    OpenVPNConnection.logger.log(Level.SEVERE, "IO error during OpenVPN log capturing", e); //$NON-NLS-1$
                }
            }
        };
        this.errorStreamThread = new Thread() {

            @Override
            public void run() {
                super.run();
                int bytes;
                byte[] buffer = new byte[8192];
                InputStream inputStream = OpenVPNConnection.this.openVPNProcess.getErrorStream();
                try {
                    while ((bytes = inputStream.read(buffer)) > 0) {
                        String str = new String(buffer, 0, bytes);
                        OpenVPNConnection.this.parseErrorStream(str);
                    }
                } catch (IOException e) {
                    OpenVPNConnection.logger.log(Level.SEVERE, "IO error during OpenVPN error capturing", e); //$NON-NLS-1$
                }
            }
        };
        this.exitSubProcessThread = new Thread() {

            @Override
            public void run() {
                super.run();
                try {
                    OpenVPNConnection.this.openVPNProcess.waitFor();
                    OpenVPNConnection.this.parseExitCode(OpenVPNConnection.this.openVPNProcess.exitValue());
                } catch (InterruptedException e) {
                    OpenVPNConnection.logger.log(Level.SEVERE, "Thread waiting for termination of OpenVPN is interrupted", e); //$NON-NLS-1$
                }
            }
        };
        this.inputStreamThread.start();
        this.errorStreamThread.start();
        this.exitSubProcessThread.start();
    }

    /**
     * Disconnects an open OpenVPN link, if a link using the associated configuration object is engaged.
     */
    public void disconnect() {
        if (this.openVPNProcess != null) {
            this.openVPNProcess.destroy();
        }
    }

    /**
     * Parses the output stream of the subprocess.
     * 
     * @param str the string returned from the error stream
     */
    void parseErrorStream(String str) {
        if (str.contains(ENTER_AUTH_USERNAME)) {
            notifyAuthUserPass(new AuthUserPassEvent(this));
        }
        if (str.contains(ENTER_AUTH_PASSWORD) && !this.authUserPass) {
            notifyAuthPass(new AuthPassEvent(this));
        }
    }

    /**
     * Parses for exit value of the subprocess.
     * 
     * @param exitValue the exit value returned from the subprocess
     */
    void parseExitCode(int exitValue) {
        notifyTerminate(new TerminateEvent(this));
        this.connected = false;
        this.connectedOpenVPNConfig = null;
    }

    /**
     * Parses the input stream of the subprocess.
     * 
     * @param str the string returned from the input stream
     */
    void parseInputStream(String str) {
        if (str.contains(INITIALIZATION_SEQUENCE_COMPLETED)) {
            notifySequenceComplete(new SequenceCompleteEvent(this));
            this.connected = true;
        }
        setChanged();
        notifyObservers(str);
    }

}
