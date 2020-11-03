package com.github.abhishek.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallbacks {
    public static void main(String[] args) {

        //logger from slj
        Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallbacks.class);

        //making var of bootstrap server address
        String bootstrapServers = "127.0.0.1:9092";

        //create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);


        // looping this section
        for (int i=0; i<10; i++) {
            //create a producer record
            ProducerRecord<String, String> record = new ProducerRecord<String, String>("topic_A", "Kafka Developer" + Integer.toString(i));

            // send data using callbacks
            producer.send(record, new Callback() {
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    //executes when a record is successfully sent or when exception occurs
                    if (e == null) {
                        //record was successfully sent
                        logger.info("Recieved new metadata. \n" +
                                "Topic:  " + recordMetadata.topic() + "\n" +
                                "Partition:  " + recordMetadata.partition() + "\n" +
                                "Offset:  " + recordMetadata.offset() + "\n" +
                                "Timestamp:  " + recordMetadata.timestamp());
                    } else {
                        logger.error("Error while producing", e);
                    }
                }
            });
        }

        //flush data
        producer.flush();

        //close the producer
        producer.close();
    }
}
