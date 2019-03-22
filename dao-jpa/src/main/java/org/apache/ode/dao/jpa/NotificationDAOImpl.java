package org.apache.ode.dao.jpa;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.ode.bpel.dao.NotificationDAO;
import org.apache.ode.bpel.dao.ProcessInstanceDAO;

@Entity
@Table(name = "ODE_TRADE_NOTIFICATION")
@NamedQueries({ @NamedQuery(name = NotificationDAOImpl.DELETE_NOTIFICATIONS_BY_INSTANCE, query = "delete from NotificationDAOImpl as r where r._instanceId in(:instanceIds)") })
public class NotificationDAOImpl implements NotificationDAO {

	public static final String DELETE_NOTIFICATIONS_BY_INSTANCE = "DELETE_NOTIFICATIONS_BY_INSTANCE";

	@Id
	@Column(name = "NOTIFICATION_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SuppressWarnings("unused")
	private Long _id;

	@Basic
	@Column(name = "DATA_CONTAINER_NAME")
	private String _dataContainerName;

	@Basic
	@Column(name = "NOTIFICATION_REF")
	private String _notificationID;

	@Basic
	@Column(name = "TRADE_NOTIFICATION_REF")
	private String _tradeNotificationID;

	@Basic
	@Column(name = "NOTIFICATION_CHANNEL")
	private String _notificationChannelID;

	@SuppressWarnings("unused")
	@Basic
	@Column(name = "PROCESS_INSTANCE_ID", insertable = false, updatable = false, nullable = true)
	private int _instanceId;
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST })
	@Column(name = "PROCESS_INSTANCE_ID")
	private ProcessInstanceDAOImpl _processInst;

	public NotificationDAOImpl() {
	}

	public NotificationDAOImpl(String dataContainerName, String notificationID,
			String tradeNotificationID, String notificationChannelID,
			ProcessInstanceDAOImpl processInstance) {
		_dataContainerName = dataContainerName;
		_notificationID = notificationID;
		_notificationChannelID = notificationChannelID;
		_tradeNotificationID = tradeNotificationID;
		_processInst = processInstance;
	}

	public String getDataContainerName() {
		return _dataContainerName;
	}

	public String getNotificationID() {
		return _notificationID;
	}

	public String getTraDENotificationID() {
		return _tradeNotificationID;
	}

	public String getNotificationChannelID() {
		return _notificationChannelID;
	}

	public ProcessInstanceDAO getProcessInstance() {
		return _processInst;
	}
}
