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
package org.openvpnsystray.passwordmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;

/**
 * The {@link PasswordManager} stores and retrieve passwords in a password database. This database can be read and
 * written by Java serialization technology. The stored files are AES encrypted at any time.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: PasswordManager.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class PasswordManager {

    /**
     * The logger
     */
    private static final Logger LOGGER = Logger.getLogger("openvpnsystray"); //$NON-NLS-1$

    /**
     * The name of {@link PasswordManager#loadPasswordDatabase()} method
     */
    private static final String LOAD_PASSWORD_DATABASSE = "loadPasswordDatabasse"; //$NON-NLS-1$

    /**
     * The name of {@link PasswordManager#serializePasswordDatabasse()} method
     */
    private static final String SERIALIZE_PASSWORD_DATABASSE = "serializePasswordDatabasse"; //$NON-NLS-1$

    /**
     * The empty string
     */
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /**
     * The key
     */
    private static final char[] KEY = { '8', 'o', 'V', '7', 'w', '5', 'z', 'F', '9', 'p', 'I', 't', 'G', 'y', '1', 'z' };

    /**
     * The password manager
     */
    private static PasswordManager passwordManager = new PasswordManager();

    /**
     * The passwords
     */
    private PasswordDatabase passwords;

    /**
     * The master password
     */
    private char[] masterPassword = null;

    /**
     * The password database location
     */
    private File passwordDatabaseLocation = null;

    /**
     * Is persistent mode set
     */
    private boolean persistentMode = false;

    /**
     * Creates a new {@link PasswordManager}.
     */
    private PasswordManager() {
        this.passwords = new PasswordDatabase();
    }

    /**
     * Returns the password manger instance.
     * 
     * @return the password manger instance
     */
    public static PasswordManager getPasswordManager() {
        return passwordManager;
    }

    /**
     * Adds an entry to the password database
     * 
     * @param userName the username
     * @param password the password
     * @param description the description
     * @return the {@link UUID} of the entry
     */
    public UUID addPasswordEntry(String userName, char[] password, String description) {
        if (password == null) {
            return null;
        }
        PasswordEntry passwordEntry = new PasswordEntry(userName, password, description);
        UUID id = UUID.randomUUID();
        this.passwords.put(id, passwordEntry);
        return id;
    }

    /**
     * Returns the first entry with the given description or <code>null</code>, if no entry could be found.
     * 
     * @param description the description
     * @return the first entry with the given description or <code>null</code>, if no entry could be found
     */
    public PasswordEntry getPasswordEntry(String description) {
        if (description != null && !description.equals(EMPTY_STRING)) {
            Collection<PasswordEntry> values = this.passwords.values();
            for (PasswordEntry passwordEntry : values) {
                if (passwordEntry.getDescription().equals(description)) {
                    return passwordEntry;
                }
            }
        }
        return null;
    }

    /**
     * Returns the entry with the given id or <code>null</code>, if no entry could be found.
     * 
     * @param id the id of the entry which is returned when adding the entry
     * @return the entry with the given id or <code>null</code>, if no entry could be found
     */
    public PasswordEntry getPasswordEntry(UUID id) {
        if (this.passwords.containsKey(id)) {
            return this.passwords.get(id);
        }
        return null;
    }

    /**
     * Returns the persistent mode.
     * 
     * @return the persistent mode
     */
    public boolean isPersistentMode() {
        return this.persistentMode;
    }

    /**
     * Sets the master password.
     * 
     * @param masterPassword the master password to set
     */
    public void setMasterPassword(char[] masterPassword) {
        this.masterPassword = masterPassword;
    }

    /**
     * Sets the password database location.
     * 
     * @param passwordDatabaseLocation the password database location to set
     */
    public void setPasswordDatabaseLocation(File passwordDatabaseLocation) {
        this.passwordDatabaseLocation = passwordDatabaseLocation;
    }

    /**
     * Sets the persistent mode.
     * 
     * @param persistentMode decides whether persistent mode is set
     */
    public void setPersistentMode(boolean persistentMode) {
        this.persistentMode = persistentMode;
    }

    /**
     * Serializes the password database. This method uses the set master password and password database location. If a
     * password database file exits at the given location this method will check if you are allowed to overwrite the
     * file by opening it using specified master password and password database location. <br>
     * <br>
     * If you would like to change the master password of an existing file you have to use the
     * {@link PasswordManager#serializePasswordDatabasse(char[])} method.
     */
    public void serializePasswordDatabasse() {
        if (!this.persistentMode) {
            LOGGER.throwing(PasswordManager.class.getName(), SERIALIZE_PASSWORD_DATABASSE, new RuntimeException(
                "a temporary password database is used")); //$NON-NLS-1$
        }
        if (this.masterPassword == null || this.passwordDatabaseLocation == null) {
            LOGGER.throwing(PasswordManager.class.getName(), SERIALIZE_PASSWORD_DATABASSE, new RuntimeException(
                "master password and/or password database location is not set")); //$NON-NLS-1$
        }
        if (this.masterPassword.length > 16) {
            LOGGER.throwing(PasswordManager.class.getName(), SERIALIZE_PASSWORD_DATABASSE, new RuntimeException(
                "master password length is limited to 16 characters")); //$NON-NLS-1$
        }
        try {
            char[] key16 = SecureCharArray.overwriteCharArrayByCharArray(KEY, this.masterPassword);
            Object object = this.passwords;
            if (this.passwordDatabaseLocation.exists()) {
                FileInputStream fileInputStream = new FileInputStream(this.passwordDatabaseLocation);
                CipherInputStream cipherInputStream = Cryptography.decrypt(fileInputStream, key16);
                ObjectInputStream objectInputStream = new ObjectInputStream(cipherInputStream);
                object = objectInputStream.readObject();
            }
            if (object != null) {
                FileOutputStream fileOutputStream = new FileOutputStream(this.passwordDatabaseLocation);
                CipherOutputStream cipherOutputStream = Cryptography.encrypt(fileOutputStream, key16);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(cipherOutputStream);
                objectOutputStream.writeObject(this.passwords);
                objectOutputStream.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO error during serialization of password database", e); //$NON-NLS-1$
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Password manager is not in persistent mode", e); //$NON-NLS-1$
        }
    }

    /**
     * Serializes the password database using a new master password.
     * 
     * @param newMasterPassword the new master password
     */
    public void serializePasswordDatabasse(char[] newMasterPassword) {
        if (!this.persistentMode) {
            LOGGER.throwing(PasswordManager.class.getName(), SERIALIZE_PASSWORD_DATABASSE, new RuntimeException(
                "a temporary password database is used")); //$NON-NLS-1$
        }
        if (this.masterPassword == null || this.passwordDatabaseLocation == null) {
            LOGGER.throwing(PasswordManager.class.getName(), SERIALIZE_PASSWORD_DATABASSE, new RuntimeException(
                "master password and/or password database location is not set")); //$NON-NLS-1$
        }
        if (this.masterPassword.length > 16) {
            LOGGER.throwing(PasswordManager.class.getName(), SERIALIZE_PASSWORD_DATABASSE, new RuntimeException(
                "master password length is limited to 16 characters")); //$NON-NLS-1$
        }
        try {
            Object object = this.passwords;
            if (this.passwordDatabaseLocation.exists()) {
                char[] key16 = SecureCharArray.overwriteCharArrayByCharArray(KEY, this.masterPassword);
                FileInputStream fileInputStream = new FileInputStream(this.passwordDatabaseLocation);
                CipherInputStream cipherInputStream = Cryptography.decrypt(fileInputStream, key16);
                ObjectInputStream objectInputStream = new ObjectInputStream(cipherInputStream);
                object = objectInputStream.readObject();
            }
            if (object != null) {
                this.setMasterPassword(newMasterPassword);
                char[] key16 = SecureCharArray.overwriteCharArrayByCharArray(KEY, this.masterPassword);
                FileOutputStream fileOutputStream = new FileOutputStream(this.passwordDatabaseLocation);
                CipherOutputStream cipherOutputStream = Cryptography.encrypt(fileOutputStream, key16);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(cipherOutputStream);
                objectOutputStream.writeObject(this.passwords);
                objectOutputStream.close();
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO error during serialization of password database", e); //$NON-NLS-1$
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Password manager is not in persistent mode", e); //$NON-NLS-1$
        }
    }

    /**
     * Load a password database. This method uses the specified master password and password database location. You have
     * to set these values before using this method (see {@link PasswordManager#setMasterPassword(char[])} and
     * {@link PasswordManager#setPasswordDatabaseLocation(File)}).
     * 
     * @return true if the password database has been loaded successfully
     */
    public boolean loadPasswordDatabase() {
        if (this.masterPassword == null || this.passwordDatabaseLocation == null) {
            LOGGER.throwing(PasswordManager.class.getName(), LOAD_PASSWORD_DATABASSE, new RuntimeException(
                "master password and/or password database location is not set")); //$NON-NLS-1$
        }
        try {
            if (this.passwordDatabaseLocation.exists()) {
                char[] key16 = SecureCharArray.overwriteCharArrayByCharArray(KEY, this.masterPassword);
                FileInputStream fileInputStream = new FileInputStream(this.passwordDatabaseLocation);
                CipherInputStream cipherInputStream = Cryptography.decrypt(fileInputStream, key16);
                ObjectInputStream objectInputStream = new ObjectInputStream(cipherInputStream);
                this.passwords = (PasswordDatabase) objectInputStream.readObject();
                objectInputStream.close();
            }
            this.persistentMode = true;
            return true;
        } catch (FileNotFoundException e) {
            LOGGER.log(Level.SEVERE, "The given password database file does not exist", e); //$NON-NLS-1$
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO error during load of password database", e); //$NON-NLS-1$
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "The given password database file does not contain a password database", e); //$NON-NLS-1$
        }
        return false;
    }
}
