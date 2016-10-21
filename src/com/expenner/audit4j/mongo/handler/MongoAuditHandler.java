package com.expenner.audit4j.mongo.handler;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.audit4j.core.dto.AuditEvent;
import org.audit4j.core.exception.HandlerException;
import org.audit4j.core.exception.InitializationException;
import org.audit4j.core.handler.Handler;

import com.expenner.audit4j.mongo.dao.MongoDbDao;
import com.expenner.audit4j.mongo.dao.MongoDbDaoImpl;
import com.expenner.audit4j.mongo.model.MongoEvent;
import com.expenner.audit4j.mongo.util.MongoDbOperations;

/**
 * 
 * @author Shekar
 *
 */
public class MongoAuditHandler extends Handler implements Serializable {

	public MongoAuditHandler() {
	}

	/**
	 * mongoDbDao
	 */
	private MongoDbDao mongoDbDao;
	/**
	 * mongoOpr
	 */
	private MongoDbOperations mongoOpr = null;
	/***
	 * the mongo host address
	 */
	private String host;
	/***
	 * the mongo port
	 */
	private int port;
	/***
	 * the collection name to save audit logs
	 */
	private String collection;
	/***
	 * the db name to save logs
	 */
	private String dbName;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/***
	 * Initializes the mongo connection
	 */
	public void init() throws InitializationException {
		mongoOpr = MongoDbOperations.getInstance();
		mongoOpr.setHost(getHost());
		mongoOpr.setPort(getPort());
		mongoOpr.setDbName(getDbName());
		mongoOpr.setCollection(getCollection());
		try {
			mongoOpr.init();
		} catch (HandlerException e) {
			throw new InitializationException("Connection to db failed.", e);

		}
		mongoDbDao = new MongoDbDaoImpl();
	}

	/**
	 * stops mongo connection
	 */
	public void stop() {
		mongoOpr.stop();

	}

	@Override
	public void handle() throws HandlerException {
		AuditEvent auditEvent = getAuditEvent();
		MongoEvent event;
		try {
			event = MongoEvent.getMongoEvent(auditEvent);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new HandlerException(e.getMessage(), MongoAuditHandler.class, e);
		}
		mongoDbDao.writeEvent(event);
	}

	/**
	 * gets the mongo host address
	 * 
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * gets the mongo port
	 * 
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * gets the collection name of audit logs
	 * 
	 * @return
	 */
	public String getCollection() {
		return collection;
	}

	/**
	 * gets the db name of audit logs
	 * 
	 * @return
	 */
	public String getDbName() {
		return dbName;
	}

	/**
	 * sets the host of mongodb
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * sets the port of mongodb
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * sets the collection name in mongodb
	 * 
	 * @param collection
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/**
	 * sets the db name
	 * 
	 * @param dbName
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

}
