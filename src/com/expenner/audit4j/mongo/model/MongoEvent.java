package com.expenner.audit4j.mongo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.audit4j.core.dto.AuditEvent;

/**
 * 
 * @author Shekar
 *
 */
public class MongoEvent extends AuditEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * elements
	 */
	private Map<String, String> elements;
	/**
	 * timestampInMillis
	 */
	private long timestampInMillis;

	/**
	 * gets timestamp in millis
	 * 
	 * @return
	 */
	public long getTimestampInMillis() {
		return timestampInMillis;
	}

	/**
	 * set timestampInMillis
	 * 
	 * @param timestampInMillis
	 */
	public void setTimestampInMillis(long timestampInMillis) {
		this.timestampInMillis = timestampInMillis;
	}

	/**
	 * gets elements(fields)
	 * 
	 * @return
	 */
	public Map<String, String> getElements() {
		return elements;
	}

	/**
	 * sets elements(fields)
	 * 
	 * @param elements
	 */
	public void setElements(Map<String, String> elements) {
		this.elements = elements;
	}

	/**
	 * gets mongo event
	 * 
	 * @param auditEvent
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static MongoEvent getMongoEvent(final AuditEvent auditEvent)
			throws IllegalAccessException, InvocationTargetException {
		MongoEvent event = new MongoEvent();
		BeanUtils.copyProperties(event, auditEvent);
		return event;
	}

	@Override
	public String toString() {
		return "MongoEvent [elements=" + elements + ", timestampInMillis=" + timestampInMillis + "]";
	}

}
