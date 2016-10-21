# audit4j-mongo

Import this project as a depenedency in your project to write events to MongoDB<br>
\<dependency\><br>
			\<groupId\>audit4j-mongodb\</groupId\><br>
			\<artifactId\>audit4j-mongodb\</artifactId\><br>
			\<version\>0.0.1-SNAPSHOT\</version\><br>
		\</dependency\><br>
#audit4j conf file using yaml

!Configuration<br>
handlers: <br>
<p>- !com.expenner.audit4j.mongo.handler.MongoAuditHandler<br>
  host: localhost<br>
  port: 27017<br>
  collection: audit_logs<br>
  dbName: audit_logs<br>
- !org.audit4j.core.handler.ConsoleAuditHandler {}<br>
layout: !org.audit4j.core.layout.SimpleLayout {}<br>
metaData: !com.spring.sample.config.AuditMetaData {}</p>
