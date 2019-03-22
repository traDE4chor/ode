package org.apache.ode.bpel.memdao;

import org.apache.ode.bpel.dao.NotificationDAO;
import org.apache.ode.bpel.dao.ProcessInstanceDAO;

/**
 * @author hahnml
 *
 */
public class NotificationDaoImpl implements NotificationDAO {

	private ProcessInstanceDaoImpl instance;
	private String dataContainerName;
	private String notificationID;
	private String tradeNotificationID;
	private String notificationChannelID;

	public NotificationDaoImpl(String dataContainerName, String notificationID,
			String tradeNotificationID, String notificationChannelID,
			ProcessInstanceDaoImpl instance) {
		super();
		this.instance = instance;
		this.dataContainerName = dataContainerName;
		this.notificationID = notificationID;
		this.tradeNotificationID = tradeNotificationID;
		this.notificationChannelID = notificationChannelID;
	}

	public String getDataContainerName() {
		return dataContainerName;
	}

	public String getNotificationID() {
		return notificationID;
	}

	public String getTraDENotificationID() {
		return tradeNotificationID;
	}

	public String getNotificationChannelID() {
		return notificationChannelID;
	}

	public ProcessInstanceDAO getProcessInstance() {
		return instance;
	}
}
