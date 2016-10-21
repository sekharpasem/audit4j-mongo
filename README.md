# audit4j-mongo


#audit4j conf file

# To Activate this configuration, remove or comment Spring configuration. 
# see @org.audt4j.demo.spring.config.AuditConfig for more details.

!Configuration
handlers: 
- !com.expenner.audit4j.mongo.handler.MongoAuditHandler
  host: localhost
  port: 27017
  collection: audit_logs
  dbName: audit_logs
- !org.audit4j.core.handler.ConsoleAuditHandler {}
layout: !org.audit4j.core.layout.SimpleLayout {}
metaData: !com.spring.sample.config.AuditMetaData {}
