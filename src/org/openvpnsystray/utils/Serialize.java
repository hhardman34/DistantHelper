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
package org.openvpnsystray.utils;

import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openvpnsystray.LoggerProvider;

/**
 * {@link Serialize} is an utility class containing methods to read and write serializable objects.
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: Serialize.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class Serialize {

    /**
     * The empty string
     */
    private static final String EMPTY_STRING = ""; //$NON-NLS-1$
    /**
     * The logger
     */
    private static final Logger logger = LoggerProvider.getLogger();

    /**
     * Reads a serialized object from the given file.
     *
     * @param file the file
     * @return a serialized object from the given file
     */
    public static Object readObjectFromFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error during serialization (reading)", e); //$NON-NLS-1$
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "The given file does not contain a serialized object", e); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Reads a serialized object from the given XML file.
     *
     * @param file the XML file
     * @return a serialized object from the given XML file
     */
    public static Object readObjectFromXMLFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XMLDecoder decoder = new XMLDecoder(fileInputStream);
            Object object = decoder.readObject();
            decoder.close();
            return object;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error during serialization (reading)", e); //$NON-NLS-1$
        }
        return null;
    }

    /**
     * Writes a serializable object to the given file.
     *
     * @param file         the file
     * @param serializable the serializable object
     */
    public static void writeObjectToFile(File file, Serializable serializable) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(serializable);
            objectOutputStream.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error during serialization (writing)", e); //$NON-NLS-1$
        }
    }

    /**
     * Writes a serializable object to the given XML file.
     *
     * @param file         the XML file
     * @param serializable the serializable object
     */
    public static void writeObjectToXMLFile(File file, Serializable serializable) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            XMLEncoder encoder = new XMLEncoder(fileOutputStream);

            // persistence delegate to enable XMLEncode to write java.io.File objects
            PersistenceDelegate filePersistenceDelegate = new DefaultPersistenceDelegate() {

                @Override
                protected Expression instantiate(Object oldInstance, @SuppressWarnings("unused") Encoder out) {
                    return new Expression(oldInstance, oldInstance.getClass(), "new", new Object[]{oldInstance.toString()}); //$NON-NLS-1$
                }
            };
            encoder.setPersistenceDelegate(File.class, filePersistenceDelegate);

            // persistence delegate to enable XMLEncode to write java.util.Locale objects
            encoder.setOwner(Serialize.class);
            PersistenceDelegate localePersistenceDelegate = new DefaultPersistenceDelegate() {

                @Override
                protected Expression instantiate(Object oldInstance, @SuppressWarnings("unused") Encoder out) {
                    Locale savedLocale = (Locale) oldInstance;
                    String fieldName = EMPTY_STRING;
                    try {
                        Field[] fields = savedLocale.getClass().getFields();
                        for (int i = 0; i < fields.length; i++) {
                            Field field = fields[i];
                            if (Modifier.isStatic(field.getModifiers())) {
                                Locale locale = (Locale) field.get(null);
                                if (savedLocale == locale) {
                                    fieldName = field.getName();
                                    break;
                                }
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    Expression expression = new Expression(Serialize.class, "loadLocale", new Object[]{fieldName}); //$NON-NLS-1$
                    return expression;
                }
            };
            encoder.setPersistenceDelegate(Locale.class, localePersistenceDelegate);

            // writes objects in the file
            encoder.writeObject(serializable);
            encoder.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IO error during serialization (writing)", e); //$NON-NLS-1$
        }
    }

    /**
     * Loads the stored {@link Locale} object from XML file.
     *
     * @param key the name of the member variable of locale in {@link Locale} class
     * @return the stored {@link Locale} object from XML file
     */
    public static Locale loadLocale(String key) {
        Locale locale = null;
        try {
            locale = (Locale) Locale.class.getField(key).get(null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
            return locale;
        }
    }

