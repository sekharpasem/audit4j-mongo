package com.expenner.audit4j.mongo.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.audit4j.core.dto.Field;

import com.expenner.audit4j.mongo.model.MongoEvent;
import com.expenner.audit4j.mongo.util.MongoDbOperations;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;

/**
 * 
 * @author Shekar
 *
 */
public class MongoDbDaoImpl implements MongoDbDao {

	public boolean writeEvent(MongoEvent event) {
		Long uuid;
		Date timestamp;
		Map<String, String> elements = new HashMap<String, String>();
		if (event.getUuid() == null) {
			uuid = UUID.randomUUID().getMostSignificantBits();
			event.setUuid(uuid);
		}

		if (event.getTimestamp() == null) {
			timestamp = new Date();
			event.setTimestamp(timestamp);
		}

		event.setTimestampInMillis(event.getTimestamp().getTime());

		for (Field element : event.getFields()) {
			elements.put(element.getName(), element.getValue());
		}
		event.setElements(elements);

		DBCollection coll = MongoDbOperations.getInstance().getDbCollection();
		BasicDBObject doc = new BasicDBObject("action", event.getAction()).append("actor", event.getActor())
				.append("timestamp", event.getTimestamp()).append("timestampInMillis", event.getTimestampInMillis())
				.append("meta", event.getMeta()).append("uuid", event.getUuid()).append("tag", event.getTag())
				.append("origin", event.getOrigin()).append("elements", elements);

		coll.insert(doc);
		return true;
	}

}
