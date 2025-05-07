package com.akeir.webstore.infra.config;


import com.akeir.webstore.mngr.model.Order;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

//TODO: FINISH IMPLEMENTATION
public class KafkaProducerConfig {
//@Configuration
//public class KafkaProducerConfig {
//
//    @Value(value = "${spring.kafka.bootstrap-servers}")
//    private String bootstrapAddress;
//
//    @Bean
//    ProducerFactory<String, Order> orderProducerFactory() {
//            Map<String, Object> props = new HashMap<>();
//            props.put(JsonSerializer.ADD_TYPE_INFO_HEADERS, false);
//            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
//            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
//            return new DefaultKafkaProducerFactory<>(props);
//    }
//
//    @Bean
//    KafkaTemplate<String, Order> orderKafkaTemplate() {
//            return new KafkaTemplate<>(orderProducerFactory());
//    }
}
