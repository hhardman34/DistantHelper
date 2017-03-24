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

package org.openvpnsystray;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.JOptionPane;

import org.openvpnsystray.gui.modellistener.AuthPassListener;
import org.openvpnsystray.gui.modellistener.AuthUserPassListener;
import org.openvpnsystray.gui.modellistener.SequenceCompleteListener;
import org.openvpnsystray.gui.modellistener.TerminateListener;
import org.openvpnsystray.gui.setmasterpassworddialog.SetMasterPasswordDialog;
import org.openvpnsystray.gui.systray.SystemTrayIcon;
import org.openvpnsystray.gui.systray.SystemTrayModalDialog;
import org.openvpnsystray.gui.userpassdialog.UserPassDialog;
import org.openvpnsystray.gui.utils.IconProvider;
import org.openvpnsystray.gui.utils.LocalizationProvider;
import org.openvpnsystray.openvpn.OpenVPNConfig;
import org.openvpnsystray.openvpn.OpenVPNConnection;
import org.openvpnsystray.openvpn.OpenVPNUtils;
import org.openvpnsystray.passwordmanager.PasswordManager;
import org.openvpnsystray.utils.OSUtils;
import org.openvpnsystray.utils.Serialize;

/**
 * OpenVPN SysTray is a smart program enabling you to control OpenVPN from a self explaining graphical user interface.
 * It adds an icon to the system tray or notification area from which you can start/stop your VPN tunnels. <br>
 * <br>
 * In addition, a password manager is supplied. Whenever you have to give your authentication information OpenVPN
 * SysTray can do that for you. Your passwords are kept save in an AES encrypted password database which is accessible by
 * a master password. <br>
 * <br>
 * This class contains the main entry point to OpenVPN SysTray.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: Main.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class Main {

    /**
     * Configuration file name
     */
    private static final String CONFIG_FILE = "config.xml"; //$NON-NLS-1$

    /**
     * Log file name
     */
    private static final String LOG_FILE = "openvpnsystray.log"; //$NON-NLS-1$

    /**
     * Unix program name
     */
    private static final String UNIX_PROGRAM_NAME = "resources.openvpnsystray"; //$NON-NLS-1$

    /**
     * The logger
     */
    private static Logger logger = null;

    /**
     * The configuration object
     */
    private static Configuration configuration = null;

    static {
        // initializes the logger provider
        LoggerProvider.loadLogger(UNIX_PROGRAM_NAME);
        Main.logger = LoggerProvider.getLogger();
        Main.logger.setUseParentHandlers(false);
        try {
            FileHandler fileHandler = new FileHandler(LOG_FILE);
            fileHandler.setFormatter(new SimpleFormatter());
            Main.logger.addHandler(fileHandler);
        } catch (SecurityException e) {
            Main.logger.log(Level.SEVERE,
                "A security manager exists and the caller does not have logging permission", e); //$NON-NLS-1$
        } catch (IOException e) {
            Main.logger.log(Level.SEVERE, "There are IO problems opening the log file", e); //$NON-NLS-1$
        }
        if (OSUtils.isSuperUser()) {
            File configFile = new File(CONFIG_FILE);
            
        //    writes a standard configuration file
            File openVPNExecutable = new File("vpn/openvpn.exe"); //$NON-NLS-1$
            File openVPNConfigDirectory = new File("config"); //$NON-NLS-1$
            File passwordDatabase = new File("pwd.jsl"); //$NON-NLS-1$
            Locale locale = Locale.US;

            Configuration newConfiguration = new Configuration(openVPNExecutable, openVPNConfigDirectory, passwordDatabase,
                false, locale, configFile);
            Serialize.writeObjectToXMLFile(configFile, newConfiguration);

            // initializes the configuration provider
            ConfigurationProvider.loadConfigurationFromFile(configFile);
            Main.configuration = ConfigurationProvider.getConfiguration();
            if (Main.configuration == null) {
                Main.logger.log(Level.SEVERE, "Configuration file \"config.xml\" could not be found or is corrupted"); //$NON-NLS-1$
                JOptionPane.showConfirmDialog(
                        JOptionPane.getRootFrame(),
                        "Configuration fileopenvpn.jar \"config.xml\" could not be found or is corrupted.", //$NON-NLS-1$
                        "Configuration file", //$NON-NLS-1$
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            
            // initializes the localization provider
            String localizationResourceBaseName = UNIX_PROGRAM_NAME;
            LocalizationProvider.loadLocalizationResources(localizationResourceBaseName, Main.configuration.getLocale());
            Locale.setDefault(LocalizationProvider.getLocale());
        } else {
            Main.logger.log(Level.SEVERE, "You do not have the permission to open a VPN tunnel. Start as root or administrator."); //$NON-NLS-1$
            JOptionPane.showConfirmDialog(
                    JOptionPane.getRootFrame(),
                    "You do not have the permission to open a VPN tunnel. Start as root or administrator.", //$NON-NLS-1$
                    "Access denied", //$NON-NLS-1$
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

//    public static void main(String[] args) { //{ obrien83 : to delete
    public static void opvpnstart() {
        if (!Main.configuration.isEmptyMasterPassword() && Main.configuration.getPasswordDatabase().exists()) {
            UserPassDialog userPassDialog = new UserPassDialog(false, false);
            int userPassDialogResult = userPassDialog.showDialog();
            if (userPassDialogResult == SystemTrayModalDialog.OK_OPTION) {
                initPasswordManager(userPassDialog.getPassword());
            } else {
                System.exit(0);
            }
        } else {
            char[] masterPassword = {};
            initPasswordManager(masterPassword);
        }
    }

    /**
     * Initializes the password manager.
     * 
     * @param masterPassword the master password
     */
    private static void initPasswordManager(char[] masterPassword) {
        PasswordManager passwordManager = PasswordManager.getPasswordManager();
        passwordManager.setMasterPassword(masterPassword);
        passwordManager.setPasswordDatabaseLocation(configuration.getPasswordDatabase());
        if (!configuration.getPasswordDatabase().exists()) {
            SetMasterPasswordDialog setMasterPasswordDialog = new SetMasterPasswordDialog();
            int masterPasswordDialogResult = setMasterPasswordDialog.showDialog();
            if (masterPasswordDialogResult == SystemTrayModalDialog.OK_OPTION) {
                char[] newMasterPassword = setMasterPasswordDialog.getMasterPassword();
                passwordManager.setMasterPassword(newMasterPassword);
                passwordManager.setPersistentMode(true);
                passwordManager.serializePasswordDatabasse();
                if (newMasterPassword.length == 0) {
                    configuration.setEmptyMasterPassword(true);
                } else {
                    configuration.setEmptyMasterPassword(false);
                }
                Serialize.writeObjectToXMLFile(configuration.getConfigFile(), configuration);
            } else {
                JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                    LocalizationProvider.getOptionPaneCancelingMasterPasswordDialogMessage(),
                    LocalizationProvider.getOptionPaneCancelingMasterPasswordDialogTitle(),
                    JOptionPane.OK_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
                PasswordManager.getPasswordManager().setPersistentMode(false);
            }
        }
        if (passwordManager.loadPasswordDatabase()) {
            initApplication(true);
        } else {
            Main.logger.log(Level.INFO, "The given master password was wrong"); //$NON-NLS-1$
            int confirmDialogResult = JOptionPane.showConfirmDialog(JOptionPane.getRootFrame(),
                LocalizationProvider.getOptionPaneMasterPasswordWrongMessage(),
                LocalizationProvider.getOptionPaneMasterPasswordWrongTitle(),
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (confirmDialogResult == JOptionPane.OK_OPTION) {
                initApplication(false);
            } else {
                System.exit(0);
            }
        }
    }

    /**
     * Initialize the graphical user interface
     * 
     * @param persistentMode
     */
    private static void initApplication(boolean persistentMode) {
        IconProvider.loadTrayIcons();
        File openVPNConfigDirectory = configuration.getOpenVPNConfigDirectory();
        List<OpenVPNConfig> openVPNConfigs = OpenVPNUtils.searchOpenVPNConfigFiles(openVPNConfigDirectory,
            configuration.getOpenVPNExecutable());
        SystemTrayIcon systemTrayIcon = new SystemTrayIcon(openVPNConfigs, persistentMode);
        for (OpenVPNConfig openVPNConfig : openVPNConfigs) {
            OpenVPNConnection openVPNConnection = openVPNConfig.getOpenVPNConnection();
            openVPNConnection.addSequenceCompleteListener(new SequenceCompleteListener(systemTrayIcon));
            openVPNConnection.addAuthUserPassListener(new AuthUserPassListener(systemTrayIcon));
            openVPNConnection.addAuthPassListener(new AuthPassListener(systemTrayIcon));
            openVPNConnection.addTerminateListener(new TerminateListener(systemTrayIcon));
        }
    }
}
