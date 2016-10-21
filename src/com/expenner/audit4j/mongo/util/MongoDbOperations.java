package com.expenner.audit4j.mongo.util;

import java.net.UnknownHostException;

import org.audit4j.core.exception.HandlerException;

import com.expenner.audit4j.mongo.handler.MongoAuditHandler;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

/**
 * 
 * @author Shekar
 *
 */
public class MongoDbOperations {
	/**
	 * host
	 */
	private String host;
	/**
	 * port
	 */
	private int port = 27017;
	/**
	 * collection name
	 */
	private String collection = "audit_logs";
	/**
	 * dbName
	 */
	private String dbName;
	/**
	 * dbCollection
	 */
	private DBCollection dbCollection;

	private static MongoDbOperations instance = null;
	private MongoClient mongoClient = null;
	private DB db = null;

	private MongoDbOperations() {

	}

	/**
	 * gets host
	 * 
	 * @return
	 */
	public String getHost() {
		return host;
	}

	/**
	 * gets port
	 * 
	 * @return
	 */
	public int getPort() {
		return port;
	}

	/**
	 * gets host
	 * 
	 * @param host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * sets port
	 * 
	 * @param port
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * gets collection
	 * 
	 * @return
	 */
	public String getCollection() {
		return collection;
	}

	/**
	 * gets dbName
	 * 
	 * @return
	 */
	public String getDbName() {
		return dbName;
	}

	/***
	 * sets collection
	 * 
	 * @param collection
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/***
	 * sets dbName
	 * 
	 * @param dbName
	 */
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	/***
	 * gets dbCollection
	 * 
	 * @return
	 */
	public DBCollection getDbCollection() {
		return dbCollection;
	}

	/**
	 * gets singleton instance of MongoDbOperations
	 * 
	 * @return
	 */
	public static MongoDbOperations getInstance() {
		synchronized (MongoDbOperations.class) {
			if (instance == null) {
				instance = new MongoDbOperations();
			}
		}
		return instance;
	}

	/**
	 * init
	 * 
	 * @throws HandlerException
	 */
	public void init() throws HandlerException {
		try {
			mongoClient = new MongoClient(getHost(), getPort());
			db = mongoClient.getDB(getDbName());
			dbCollection = db.getCollection(getCollection());
		} catch (UnknownHostException e) {
			throw new HandlerException("UnknownHostException occured while writing the event", MongoAuditHandler.class,
					e);
		}

	}

	/**
	 * stops mongo connection
	 */
	public void stop() {
		this.mongoClient = null;
		this.dbCollection = null;

	}
}
