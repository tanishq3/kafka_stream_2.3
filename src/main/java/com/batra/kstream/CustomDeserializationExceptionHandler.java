package com.batra.kstream;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.errors.DeserializationExceptionHandler;
import org.apache.kafka.streams.processor.ProcessorContext;

public class CustomDeserializationExceptionHandler implements DeserializationExceptionHandler {

	@Override
	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub

	}

	@Override
	public DeserializationHandlerResponse handle(ProcessorContext context, ConsumerRecord<byte[], byte[]> record,
			Exception exception) {
		try {
			// TODO Can through error
			System.out.println("Context offset :" + context.offset() + ", Partition :" + context.partition()
					+ ".Exception :" + exception.getMessage());
		} catch (Exception exception1) {
			System.out.println(exception1.getMessage());
		}
		System.out.println("From Record object. offset :" + record.offset() + ", Partition :" + record.partition()
				+ ".Exception :" + exception.getMessage());

		return DeserializationHandlerResponse.CONTINUE;
	}

}
