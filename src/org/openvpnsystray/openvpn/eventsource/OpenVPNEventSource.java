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
package org.openvpnsystray.openvpn.eventsource;

import java.util.Observable;

import javax.swing.event.EventListenerList;

/**
 * {@link OpenVPNEventSource} setting up the event listener interfaces. 
 * 
 * @author Stefan Franke
 * @date 07.02.2008
 * @version ($Id: OpenVPNEventSource.java 6 2008-04-05 11:21:53Z frankesn $)
 */
public class OpenVPNEventSource extends Observable {

	/**
	 * The sequence complete listener list
	 */
	private EventListenerList sequenceCompleteListenerList = new EventListenerList();

	/**
	 * The authentication user pass listener list
	 */
	private EventListenerList authUserPassListenerList = new EventListenerList();

	/**
	 * The authentication pass listener list
	 */
	private EventListenerList authPassListenerList = new EventListenerList();

	/**
	 * The terminate listener list
	 */
	private EventListenerList terminateListenerList = new EventListenerList();

	/**
	 * Adds the specified sequence complete listener to receive sequence complete events.
	 *
	 * @param listener the listener to add
	 */
	public void addSequenceCompleteListener(ISequenceCompleteListener listener) {
		this.sequenceCompleteListenerList.add(ISequenceCompleteListener.class, listener);
	}

	/**
	 * Removes the specified sequence complete listener.
	 *
	 * @param listener the listener to remove
	 */
	public void removeSequenceCompleteListener(ISequenceCompleteListener listener) {
	    this.sequenceCompleteListenerList.remove(ISequenceCompleteListener.class, listener);
	}

	/**
	 * Sends a sequence complete event to all registered listeners.
	 *
	 * @param event 
	 */
	protected synchronized void notifySequenceComplete(SequenceCompleteEvent event) {
		for (ISequenceCompleteListener listener : this.sequenceCompleteListenerList
				.getListeners(ISequenceCompleteListener.class)) {
			listener.actionPerformed(event);
		}
	}

	/**
	 * Adds the specified authentication username password listener to receive authentication username password events.
	 *
	 * @param listener the listener to add
	 */
	public void addAuthUserPassListener(IAuthUserPassListener listener) {
	    this.authUserPassListenerList.add(IAuthUserPassListener.class, listener);
	}

	/**
	 * Removes the specified authentication username password listener.
	 *
	 * @param listener the listener to remove
	 */
	public void removeAuthUserPassListener(IAuthUserPassListener listener) {
	    this.authUserPassListenerList.remove(IAuthUserPassListener.class, listener);
	}

	/**
	 * Sends an authentication username password event to all registered listeners.
	 *
	 * @param event 
	 */
	protected synchronized void notifyAuthUserPass(AuthUserPassEvent event) {
		for (IAuthUserPassListener listener : this.authUserPassListenerList
				.getListeners(IAuthUserPassListener.class)) {
			listener.actionPerformed(event);
		}
	}

	/**
	 * Adds the specified authentication password listener to receive authentication password events.
	 *
	 * @param listener the listener to add
	 */
	public void addAuthPassListener(IAuthPassListener listener) {
	    this.authPassListenerList.add(IAuthPassListener.class, listener);
	}

	/**
	 * Removes the specified authentication password listener.
	 *
	 * @param listener the listener to remove
	 */
	public void removeAuthPassListener(IAuthPassListener listener) {
	    this.authPassListenerList.remove(IAuthPassListener.class, listener);
	}

	/**
	 * Sends an authentication password event to all registered listeners.
	 *
	 * @param event 
	 */
	protected synchronized void notifyAuthPass(AuthPassEvent event) {
		for (IAuthPassListener listener : this.authPassListenerList
				.getListeners(IAuthPassListener.class)) {
			listener.actionPerformed(event);
		}
	}

	/**
	 * Adds the specified terminate listener to receive terminate events.
	 *
	 * @param listener the listener to add
	 */
	public void addTerminateListener(ITerminateListener listener) {
	    this.terminateListenerList.add(ITerminateListener.class, listener);
	}

	/**
	 * Removes the specified terminate listener.
	 *
	 * @param listener the listener to remove
	 */
	public void removeTerminateListener(ITerminateListener listener) {
	    this.terminateListenerList.remove(ITerminateListener.class, listener);
	}

	/**
	 * Sends a terminate event to all registered listeners.
	 *
	 * @param event 
	 */
	protected synchronized void notifyTerminate(TerminateEvent event) {
		for (ITerminateListener listener : this.terminateListenerList
				.getListeners(ITerminateListener.class)) {
			listener.actionPerformed(event);
		}
	}
}
