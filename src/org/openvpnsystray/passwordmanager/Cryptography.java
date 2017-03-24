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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * {@link Cryptography} bundles methods for encryption and decryption of string and streams.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: Cryptography.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class Cryptography {

    /**
     * The logger
     */
    private static final Logger LOGGER = Logger.getLogger("openvpnsystray"); //$NON-NLS-1$

    /**
     * The EMPTY_STRING
     */
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /**
     * The AES
     */
    private static final String AES = "AES"; //$NON-NLS-1$

    /**
     * The AES_ECB_PKCS5_PADDING
     */
    private static final String AES_ECB_PKCS5_PADDING = "AES/ECB/PKCS5Padding"; //$NON-NLS-1$

    /**
     * Encrypts the given string using the given key. Returns an encrypted string.
     * 
     * @param decryptedString the decrypted input stream
     * @param key the key
     * @return encrypted string
     */
    public static String encrypt(String decryptedString, char[] key) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedString.getBytes());
        return encrypt(byteArrayInputStream, key);
    }

    /**
     * Encrypts the given input stream using the given key. Returns an encrypted string.
     * 
     * @param decryptedStream the decrypted input stream
     * @param key the key
     * @return encrypted string
     */
    public static String encrypt(InputStream decryptedStream, char[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SecureCharArray.convertCharArrayToByteArray(key), AES);
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            CipherInputStream cipherInputStream = new CipherInputStream(decryptedStream, cipher);
            return cipherInputStreamToString(cipherInputStream);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "the selected cryptographic algorithm is not available", e); //$NON-NLS-1$
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "the selected padding mechanism is not available", e); //$NON-NLS-1$
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "the given key is invalid", e); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Returns an cipher output stream which <code>write</code> methods encrypts the output.
     * 
     * @param decryptedStream the decrypted output stream
     * @param key the key
     * @return cipher output stream which <code>write</code> methods encrypts the output
     */
    public static CipherOutputStream encrypt(OutputStream decryptedStream, char[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SecureCharArray.convertCharArrayToByteArray(key), AES);
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return new CipherOutputStream(decryptedStream, cipher);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "the selected cryptographic algorithm is not available", e); //$NON-NLS-1$
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "the selected padding mechanism is not available", e); //$NON-NLS-1$
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "the given key is invalid", e); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Decrypts the given string using the given key. Returns an decrypted string.
     * 
     * @param encryptedString the encrypted string
     * @param key the key
     * @return decrypted string
     */
    public static String decrypt(String encryptedString, char[] key) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(encryptedString.getBytes());
        return cipherInputStreamToString(decrypt(byteArrayInputStream, key));
    }

    /**
     * Returns an cipher input stream which <code>read</code> methods decrypts the input.
     * 
     * @param encryptedStream the encrypted input stream
     * @param key the key
     * @return cipher input stream which <code>read</code> methods decrypts the input
     */
    public static CipherInputStream decrypt(InputStream encryptedStream, char[] key) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(SecureCharArray.convertCharArrayToByteArray(key), AES);
            Cipher cipher = Cipher.getInstance(AES_ECB_PKCS5_PADDING);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new CipherInputStream(encryptedStream, cipher);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.log(Level.SEVERE, "the selected cryptographic algorithm is not available", e); //$NON-NLS-1$
        } catch (NoSuchPaddingException e) {
            LOGGER.log(Level.SEVERE, "the selected padding mechanism is not available", e); //$NON-NLS-1$
        } catch (InvalidKeyException e) {
            LOGGER.log(Level.SEVERE, "the given key is invalid", e); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Returns a string from an input stream.
     * 
     * @param cipherInputStream the encrypted input stream
     * @return a string containing the data out of input stream
     */
    private static String cipherInputStreamToString(CipherInputStream cipherInputStream) {
        try {
            int bytes;
            byte[] buffer = new byte[8192];
            String str = EMPTY_STRING;
            while ((bytes = cipherInputStream.read(buffer)) > 0) {
                str = str + new String(buffer, 0, bytes);
                System.out.print(str);
            }
            if (str.equals(EMPTY_STRING)) {
                return null;
            }
            return str;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO error during encrypting or decrypting", e); //$NON-NLS-1$
        }
        return null;
    }
}
