package com.github.abhishek.kafka.tutorial1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemoGroups {
    public static void main(String[] args) {


        String bootstrapServer = "127.0.0.1:9092";
        String groupId = "myGroup2";
        String topic = "topic_A";
        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());

        //create consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        //Deserialization is done when kafka sends bytes to consumers,consumers have to take these bytes & create a string from it !
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); //can have latest,none,earliest


        //create a consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        //subscribe consumer to our topic(s)
        consumer.subscribe(Collections.singleton(topic));//can include Arrays.asList for multiple topics


        //poll for new data
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String, String > record : records) {
                logger.info("Key: "+ record.key() +
                        "Value: " + record.value() +
                        "Offset: " + record.offset() +
                        "Partition: " + record.partition());
            }
        }
    }
}

//run the consumer demo group 2 times and consumer demo once and then run the producer demo to see the consumer group
// loadbalancing.