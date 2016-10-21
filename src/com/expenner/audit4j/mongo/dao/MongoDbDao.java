package com.expenner.audit4j.mongo.dao;

import com.expenner.audit4j.mongo.model.MongoEvent;

/**
 * 
 * @author Shekar
 *
 */
public interface MongoDbDao {
	/***
	 * writes event to db
	 * 
	 * @param event
	 * @return
	 */
	boolean writeEvent(final MongoEvent event);
}
