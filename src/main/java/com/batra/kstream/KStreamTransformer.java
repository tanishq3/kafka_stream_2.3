package com.batra.kstream;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Transformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KStreamTransformer implements Transformer<Long, String, KeyValue<Long, String>> {
	private static final Logger LOGGER = LoggerFactory.getLogger(KStreamTransformer.class);
	private ProcessorContext processorContext;

	public void init(ProcessorContext context) {
		this.processorContext = context;
	}

	public KeyValue<Long, String> transform(Long key, String value) {
		LOGGER.info("Received key :{}, Value :{}, Partition:{}, Offset :{}", key, value,processorContext.partition(),processorContext.offset());
		System.out.println("Received key :"+key+", Value :"+value+", Partition:"+processorContext.partition()+", Offset :"+processorContext.offset());
		return KeyValue.pair(key, value);
	}

	public void close() {
		LOGGER.info("Closed");

	}

}
