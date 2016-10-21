# audit4j-mongo


#audit4j conf file

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
