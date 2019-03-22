package org.apache.ode.bpel.runtime.channels;

/**
 * Channel for data notifications.
 * 
 * @author hahnml
 */
public interface DataNotification {

	/**
	 * Data notification was received
	 * 
	 * @param variableName
	 *            the name of the variable for which the notification was sent
	 */
	void onNotification(String variableName);

	/**
	 * Notification was canceled.
	 * 
	 * @param variableName
	 *            the name of the variable for which the notification was canceled
	 * */
	void onCancel(String variableName);

}
