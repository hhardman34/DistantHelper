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

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * {@link LocalizationProvider} loads and provides localized resources.
 * <br><br>
 * All getter methods return <code>null</code>,
 * if the localization resources are not loaded successfully using
 * {@link LocalizationProvider#loadLocalizationResources(String)} or
 * {@link LocalizationProvider#loadLocalizationResources(String, Locale)}.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: LocalizationProvider.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class LocalizationProvider {

    /**
     * The resourceBundle
     */
    private static ResourceBundle resourceBundle;

    /**
     * The locale
     */
    private static Locale locale;

    /**
     * The applicationUnixProgramName
     */
    private static String applicationUnixProgramName;

    /**
     * The applicationProgramName
     */
    private static String applicationProgramName;

    /**
     * The buttonsOK
     */
    private static String buttonsOK;

    /**
     * The buttonsCancel
     */
    private static String buttonsCancel;

    /**
     * The logFrameTitle
     */
    private static String logFrameTitle;

    /**
     * The openVPNPopupMenuConnect
     */
    private static String openVPNPopupMenuConnect;

    /**
     * The openVPNPopupMenuDisconnect
     */
    private static String openVPNPopupMenuDisconnect;

    /**
     * The openVPNPopupMenuShowLog
     */
    private static String openVPNPopupMenuShowLog;

    /**
     * The optionPaneAllowEmptyMasterPasswordTitle
     */
    private static String optionPaneAllowEmptyMasterPasswordTitle;

    /**
     * The optionPaneAllowEmptyMasterPasswordMessage
     */
    private static String optionPaneAllowEmptyMasterPasswordMessage;

    /**
     * The optionPaneMasterPasswordWrongTitle
     */
    private static String optionPaneMasterPasswordWrongTitle;

    /**
     * The optionPaneMasterPasswordWrongMessage
     */
    private static String optionPaneMasterPasswordWrongMessage;

    /**
     * The optionPaneCancelingMasterPasswordDialogTitle
     */
    private static String optionPaneCancelingMasterPasswordDialogTitle;

    /**
     * The optionPaneCancelingMasterPasswordDialogMessage
     */
    private static String optionPaneCancelingMasterPasswordDialogMessage;
    
    /**
     * The optionPaneSystemTrayNotSupportedTitle
     */
    private static String optionPaneSystemTrayNotSupportedTitle;
    
    /**
     * The optionPaneSystemTrayNotSupportedMessage
     */
    private static String optionPaneSystemTrayNotSupportedMessage;

    /**
     * The setMasterPasswordDialogDialogTitle
     */
    private static String setMasterPasswordDialogDialogTitle;

    /**
     * The setMasterPasswordDialogPassword
     */
    private static String setMasterPasswordDialogPassword;

    /**
     * The setMasterPasswordDialogReEnter
     */
    private static String setMasterPasswordDialogReEnter;

    /**
     * The setMasterPasswordDialogAllowEmptyMasterPasswordQuestion
     */
    private static String setMasterPasswordDialogAllowEmptyMasterPasswordQuestion;

    /**
     * The systemTrayPopupMenuChangeMasterPassword
     */
    private static String systemTrayPopupMenuChangeMasterPassword;

    /**
     * The systemTrayPopupMenuExit
     */
    private static String systemTrayPopupMenuExit;

    /**
     * The userPassDialogDialogTitle
     */
    private static String userPassDialogDialogTitle;

    /**
     * The userPassDialogUserName
     */
    private static String userPassDialogUserName;

    /**
     * The userPassDialogPassword
     */
    private static String userPassDialogPassword;

    /**
     * The userPassDialogSaveUserPassQuestion
     */
    private static String userPassDialogSaveUserPassQuestion;

    /**
     * Loads loacalization resources using JVM default locale.
     * 
     * @param baseName the resource bundle base name
     */
    public static void loadLocalizationResources(String baseName) {
        loadLocalizationResources(baseName, Locale.getDefault());
    }

    /**
     * Loads loacalization resources using JVM spezified locale.
     * 
     * @param baseName the resource bundle base name
     * @param l the locale
     */
    public static void loadLocalizationResources(String baseName, Locale l) {
        LocalizationProvider.locale = l;
        LocalizationProvider.resourceBundle = ResourceBundle.getBundle(baseName, l);
        LocalizationProvider.applicationUnixProgramName = LocalizationProvider.resourceBundle
                .getString("application/unixProgramName"); //$NON-NLS-1$
        LocalizationProvider.applicationProgramName = LocalizationProvider.resourceBundle
                .getString("application/programName"); //$NON-NLS-1$
        LocalizationProvider.buttonsOK = LocalizationProvider.resourceBundle.getString("buttons/ok"); //$NON-NLS-1$
        LocalizationProvider.buttonsCancel = LocalizationProvider.resourceBundle.getString("buttons/cancel"); //$NON-NLS-1$
        LocalizationProvider.logFrameTitle = LocalizationProvider.resourceBundle.getString("logFrame/Title"); //$NON-NLS-1$
        LocalizationProvider.openVPNPopupMenuConnect = LocalizationProvider.resourceBundle
                .getString("openVPNPopupMenu/connect"); //$NON-NLS-1$
        LocalizationProvider.openVPNPopupMenuDisconnect = LocalizationProvider.resourceBundle
                .getString("openVPNPopupMenu/disconnect"); //$NON-NLS-1$
        LocalizationProvider.openVPNPopupMenuShowLog = LocalizationProvider.resourceBundle
                .getString("openVPNPopupMenu/ShowLog"); //$NON-NLS-1$
        LocalizationProvider.optionPaneAllowEmptyMasterPasswordTitle = LocalizationProvider.resourceBundle
                .getString("optionPane/allowEmptyMasterPassword/title"); //$NON-NLS-1$
        LocalizationProvider.optionPaneAllowEmptyMasterPasswordMessage = LocalizationProvider.resourceBundle
                .getString("optionPane/allowEmptyMasterPassword/message"); //$NON-NLS-1$
        LocalizationProvider.optionPaneMasterPasswordWrongTitle = LocalizationProvider.resourceBundle
                .getString("optionPane/masterPasswordWrong/title"); //$NON-NLS-1$
        LocalizationProvider.optionPaneMasterPasswordWrongMessage = LocalizationProvider.resourceBundle
                .getString("optionPane/masterPasswordWrong/message"); //$NON-NLS-1$
        LocalizationProvider.optionPaneCancelingMasterPasswordDialogTitle = LocalizationProvider.resourceBundle
                .getString("optionPane/cancelingMasterPasswordDialog/title"); //$NON-NLS-1$
        LocalizationProvider.optionPaneCancelingMasterPasswordDialogMessage = LocalizationProvider.resourceBundle
                .getString("optionPane/cancelingMasterPasswordDialog/message"); //$NON-NLS-1$
        LocalizationProvider.optionPaneSystemTrayNotSupportedTitle = LocalizationProvider.resourceBundle
                .getString("optionPane/systemTrayNotSupported/title"); //$NON-NLS-1$
        LocalizationProvider.optionPaneSystemTrayNotSupportedMessage = LocalizationProvider.resourceBundle
                .getString("optionPane/systemTrayNotSupported/message"); //$NON-NLS-1$
        LocalizationProvider.setMasterPasswordDialogDialogTitle = LocalizationProvider.resourceBundle
                .getString("setMasterPasswordDialog/dialogTitle"); //$NON-NLS-1$
        LocalizationProvider.setMasterPasswordDialogPassword = LocalizationProvider.resourceBundle
                .getString("setMasterPasswordDialog/password"); //$NON-NLS-1$
        LocalizationProvider.setMasterPasswordDialogReEnter = LocalizationProvider.resourceBundle
                .getString("setMasterPasswordDialog/reEnter"); //$NON-NLS-1$
        LocalizationProvider.setMasterPasswordDialogAllowEmptyMasterPasswordQuestion = LocalizationProvider.resourceBundle
                .getString("setMasterPasswordDialog/allowEmptyMasterPasswordQuestion"); //$NON-NLS-1$
        LocalizationProvider.systemTrayPopupMenuChangeMasterPassword = LocalizationProvider.resourceBundle
                .getString("systemTrayPopupMenu/changeMasterPassword"); //$NON-NLS-1$
        LocalizationProvider.systemTrayPopupMenuExit = LocalizationProvider.resourceBundle
                .getString("systemTrayPopupMenu/exit"); //$NON-NLS-1$
        LocalizationProvider.userPassDialogDialogTitle = LocalizationProvider.resourceBundle
                .getString("userPassDialog/dialogTitle"); //$NON-NLS-1$
        LocalizationProvider.userPassDialogUserName = LocalizationProvider.resourceBundle
                .getString("userPassDialog/userName"); //$NON-NLS-1$
        LocalizationProvider.userPassDialogPassword = LocalizationProvider.resourceBundle
                .getString("userPassDialog/password"); //$NON-NLS-1$
        LocalizationProvider.userPassDialogSaveUserPassQuestion = LocalizationProvider.resourceBundle
                .getString("userPassDialog/saveUserPassQuestion"); //$NON-NLS-1$
    }

    /**
     * Returns the locale.
     * 
     * @return the locale
     */
    public static Locale getLocale() {
        return LocalizationProvider.locale;
    }

    /**
     * Returns the applicationUnixProgramName.
     * 
     * @return the applicationUnixProgramName
     */
    public static String getApplicationUnixProgramName() {
        return LocalizationProvider.applicationUnixProgramName;
    }

    /**
     * Returns the applicationProgramName.
     * 
     * @return the applicationProgramName
     */
    public static String getApplicationProgramName() {
        return LocalizationProvider.applicationProgramName;
    }

    /**
     * Returns the buttonsOK.
     * 
     * @return the buttonsOK
     */
    public static String getButtonsOK() {
        return LocalizationProvider.buttonsOK;
    }

    /**
     * Returns the buttonsCancel.
     * 
     * @return the buttonsCancel
     */
    public static String getButtonsCancel() {
        return LocalizationProvider.buttonsCancel;
    }

    /**
     * Returns the logFrameTitle.
     * 
     * @return the logFrameTitle
     */
    public static String getLogFrameTitle() {
        return LocalizationProvider.logFrameTitle;
    }

    /**
     * Returns the openVPNPopupMenuConnect.
     * 
     * @return the openVPNPopupMenuConnect
     */
    public static String getOpenVPNPopupMenuConnect() {
        return LocalizationProvider.openVPNPopupMenuConnect;
    }

    /**
     * Returns the openVPNPopupMenuDisconnect.
     * 
     * @return the openVPNPopupMenuDisconnect
     */
    public static String getOpenVPNPopupMenuDisconnect() {
        return LocalizationProvider.openVPNPopupMenuDisconnect;
    }

    /**
     * Returns the openVPNPopupMenuShowLog.
     * 
     * @return the openVPNPopupMenuShowLog
     */
    public static String getOpenVPNPopupMenuShowLog() {
        return LocalizationProvider.openVPNPopupMenuShowLog;
    }

    /**
     * Returns the optionPaneAllowEmptyMasterPasswordTitle.
     * 
     * @return the optionPaneAllowEmptyMasterPasswordTitle
     */
    public static String getOptionPaneAllowEmptyMasterPasswordTitle() {
        return LocalizationProvider.optionPaneAllowEmptyMasterPasswordTitle;
    }

    /**
     * Returns the optionPaneAllowEmptyMasterPasswordMessage.
     * 
     * @return the optionPaneAllowEmptyMasterPasswordMessage
     */
    public static String getOptionPaneAllowEmptyMasterPasswordMessage() {
        return LocalizationProvider.optionPaneAllowEmptyMasterPasswordMessage;
    }

    /**
     * Returns the optionPaneMasterPasswordWrongTitle.
     * 
     * @return the optionPaneMasterPasswordWrongTitle
     */
    public static String getOptionPaneMasterPasswordWrongTitle() {
        return LocalizationProvider.optionPaneMasterPasswordWrongTitle;
    }

    /**
     * Returns the optionPaneMasterPasswordWrongMessage.
     * 
     * @return the optionPaneMasterPasswordWrongMessage
     */
    public static String getOptionPaneMasterPasswordWrongMessage() {
        return LocalizationProvider.optionPaneMasterPasswordWrongMessage;
    }

    /**
     * Returns the optionPaneCancelingMasterPasswordDialogTitle.
     * 
     * @return the optionPaneCancelingMasterPasswordDialogTitle
     */
    public static String getOptionPaneCancelingMasterPasswordDialogTitle() {
        return LocalizationProvider.optionPaneCancelingMasterPasswordDialogTitle;
    }

    /**
     * Returns the optionPaneCancelingMasterPasswordDialogMessage.
     * 
     * @return the optionPaneCancelingMasterPasswordDialogMessage
     */
    public static String getOptionPaneCancelingMasterPasswordDialogMessage() {
        return LocalizationProvider.optionPaneCancelingMasterPasswordDialogMessage;
    }
    
    /**
     * Returns the optionPaneSystemTrayNotSupportedTitle.
     *
     * @return optionPaneSystemTrayNotSupportedTitle
     */
    public static String getOptionPaneSystemTrayNotSupportedTitle() {
        return LocalizationProvider.optionPaneSystemTrayNotSupportedTitle;
    }
    
    /**
     * Returns the optionPaneSystemTrayNotSupportedMessage.
     *
     * @return optionPaneSystemTrayNotSupportedMessage
     */
    public static String getOptionPaneSystemTrayNotSupportedMessage() {
        return LocalizationProvider.optionPaneSystemTrayNotSupportedMessage;
    }

    /**
     * Returns the setMasterPasswordDialogDialogTitle.
     * 
     * @return the setMasterPasswordDialogDialogTitle
     */
    public static String getSetMasterPasswordDialogDialogTitle() {
        return LocalizationProvider.setMasterPasswordDialogDialogTitle;
    }

    /**
     * Returns the setMasterPasswordDialogPassword.
     * 
     * @return the setMasterPasswordDialogPassword
     */
    public static String getSetMasterPasswordDialogPassword() {
        return LocalizationProvider.setMasterPasswordDialogPassword;
    }

    /**
     * Returns the setMasterPasswordDialogReEnter.
     * 
     * @return the setMasterPasswordDialogReEnter
     */
    public static String getSetMasterPasswordDialogReEnter() {
        return LocalizationProvider.setMasterPasswordDialogReEnter;
    }

    /**
     * Returns the setMasterPasswordDialogAllowEmptyMasterPasswordQuestion.
     * 
     * @return the setMasterPasswordDialogAllowEmptyMasterPasswordQuestion
     */
    public static String getSetMasterPasswordDialogAllowEmptyMasterPasswordQuestion() {
        return LocalizationProvider.setMasterPasswordDialogAllowEmptyMasterPasswordQuestion;
    }

    /**
     * Returns the systemTrayPopupMenuChangeMasterPassword.
     * 
     * @return the systemTrayPopupMenuChangeMasterPassword
     */
    public static String getSystemTrayPopupMenuChangeMasterPassword() {
        return LocalizationProvider.systemTrayPopupMenuChangeMasterPassword;
    }

    /**
     * Returns the systemTrayPopupMenuExit.
     * 
     * @return the systemTrayPopupMenuExit
     */
    public static String getSystemTrayPopupMenuExit() {
        return LocalizationProvider.systemTrayPopupMenuExit;
    }

    /**
     * Returns the userPassDialogDialogTitle.
     * 
     * @return the userPassDialogDialogTitle
     */
    public static String getUserPassDialogDialogTitle() {
        return LocalizationProvider.userPassDialogDialogTitle;
    }

    /**
     * Returns the userPassDialogUserName.
     * 
     * @return the userPassDialogUserName
     */
    public static String getUserPassDialogUserName() {
        return LocalizationProvider.userPassDialogUserName;
    }

    /**
     * Returns the userPassDialogPassword.
     * 
     * @return the userPassDialogPassword
     */
    public static String getUserPassDialogPassword() {
        return LocalizationProvider.userPassDialogPassword;
    }

    /**
     * Returns the userPassDialogSaveUserPassQuestion.
     * 
     * @return the userPassDialogSaveUserPassQuestion
     */
    public static String getUserPassDialogSaveUserPassQuestion() {
        return LocalizationProvider.userPassDialogSaveUserPassQuestion;
    }
}
