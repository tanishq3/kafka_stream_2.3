# kafka_stream_2.3
Kafka Stream 2.3 Implementations with Issues


#To run kafka locally:
Download kafka_2.12-2.3.0.zip which has windows package as well.
Open cmd on and start zookeper:
1. zookeeper-server-start.bat ../../config/zookeeper.properties
Run Kafka Server:
2. kafka-server-start.bat ../../config/server.properties
3. Create two topic: "indound_topic" and "outbound_topic"
Commands
kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 5 --topic inbound-topic

kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 5 --topic outbound-topic

ERROR:
Error found in Stream Deserialization Exception Handler.
1. Processor context object is not thread safe , under Deserialization exception handler as the instance must be created per partition bases.
To view more details kindly refer:https://stackoverflow.com/questions/59384462/kafka-stream-2-3-deserializationexceptionhandler
2. Last commited poision pill message are not commited.To view in detail, kindly refer https://issues.apache.org/jira/browse/KAFKA-6502
