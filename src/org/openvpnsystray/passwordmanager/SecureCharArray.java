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

/**
 * {@link SecureCharArray} is an utility class for character array handling.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: SecureCharArray.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class SecureCharArray {

    /**
     * Overwrites a character array with blanks.
     * 
     * @param a the character array
     */
    public static void clearCharArray(char[] a) {
        for (int i = 0; i < a.length; i++) {
            a[i] = ' ';
        }
    }

    /**
     * Converts a byte array to a new character array.
     * 
     * @param b the byte array
     * @return the character array
     */
    public static char[] convertByteArrayToCharArray(byte[] b) {
        char[] c = new char[b.length];
        for (int i = 0; i < b.length; i++) {
            c[i] = convertByteToChar(b[i]);
        }
        return c;
    }

    /**
     * Converts a byte to a character.
     * 
     * @param b the byte
     * @return the character
     */
    public static char convertByteToChar(byte b) {
        return (char) (b & 0xFF);
    }

    /**
     * Converts a character array to a new byte array.
     * 
     * @param c the character array
     * @return the byte array
     */
    public static byte[] convertCharArrayToByteArray(char[] c) {
        byte[] b = new byte[c.length];
        for (int i = 0; i < c.length; i++) {
            b[i] = convertCharToByte(c[i]);
        }
        return b;
    }

    /**
     * Converts a character to a byte.
     * 
     * @param c the character
     * @return the byte
     */
    public static byte convertCharToByte(char c) {
        return (byte) (c & 0xFF);
    }

    /**
     * Compares two character arrays. Returns true if they are equal.
     * 
     * @param a first character array
     * @param b second character array
     * @return true if they are equal
     */
    public static boolean compareCharArrays(char[] a, char[] b) {
        boolean returnBoolean = true;
        if (a.length == b.length) {
            for (int i = 0; i < a.length; i++) {
                if (a[i] != b[i]) {
                    returnBoolean = false;
                    break;
                }
            }
        }
        return returnBoolean;
    }

    /**
     * Overwrites one character array (<code>a</code>) by another one(<code>b</code>). The length of
     * <code>a</code> must be greater or equal the length of <code>b</code>. Returns a new character array that
     * contains <code>b</code> and the rest of <code>a</code>, if needed.
     * 
     * @param a first character array
     * @param b second character array
     * @return a new character array that contains <code>b</code> and the rest of <code>a</code>, if needed
     */
    public static char[] overwriteCharArrayByCharArray(char[] a, char[] b) {
        if (a.length < b.length) {
            // exception: length of array b is greater than length of array a
            return null;
        }
        char returnCharArray[] = new char[a.length];
        for (int i = 0; i < a.length; i++) {
            if (i < b.length) {
                returnCharArray[i] = b[i];
            } else {
                returnCharArray[i] = a[i];
            }
        }
        return returnCharArray;
    }
}
