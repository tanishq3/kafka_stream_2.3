package com.batra.kstream;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KstreamSample {
	private static final Logger LOGGER=LoggerFactory.getLogger(KstreamSample.class);
	private static KafkaStreams streams;

	public static Properties getProperties() {
		Properties props = new Properties();
		props.put(StreamsConfig.APPLICATION_ID_CONFIG, "KstreamSample");
		props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Long().getClass());
		props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		props.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
				CustomDeserializationExceptionHandler.class);
		return props;
	}

	public static void main(final String[] args) throws Exception {

		StreamsBuilder builder = new StreamsBuilder();
		KStream<Long, String> textLines = builder.stream("inbound-topic");
		KStream<Long, String> wordCounts = textLines.transform(() -> new KStreamTransformer());

		wordCounts.to("outbound-topic", Produced.with(Serdes.Long(), Serdes.String()));

		streams = new KafkaStreams(builder.build(), getProperties());
		streams.start();
	}
}
