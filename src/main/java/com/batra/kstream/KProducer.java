package com.batra.kstream;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KProducer {
	private static KafkaProducer<Long, String> kProducer;

	public static Properties getProducerProperties() {
		Properties properties = new Properties();

		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.LongSerializer");
//		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return properties;
	}

	public static void main(String[] args) {
		produceCorrectMessage();
//		producePoisionPillMessage();
	}

	public static void produceCorrectMessage() {
		kProducer = new KafkaProducer<>(getProducerProperties());

		try {
			ProducerRecord<Long, String> record = new ProducerRecord<Long, String>("inbound-topic", 123l,
					"This is sample message");
			RecordMetadata recordMetadata = kProducer.send(record).get();
			System.out.println("Record produced at partition :" + recordMetadata.partition() + ", Offset: "
					+ recordMetadata.offset());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void producePoisionPillMessage() {
		KafkaProducer<String, String> kProducer = new KafkaProducer<>(getProducerProperties());

		try {
			ProducerRecord<String, String> record = new ProducerRecord<String, String>("inbound-topic", "123",
					"This is sample message");
			RecordMetadata recordMetadata = kProducer.send(record).get();
			System.out.println("Record produced at partition :" + recordMetadata.partition() + ", Offset: "
					+ recordMetadata.offset());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
