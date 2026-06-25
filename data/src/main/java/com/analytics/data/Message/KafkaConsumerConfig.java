package com.analytics.data.Message;

import com.analytics.data.dto.CarPostDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig
{
    @Value( "${spring.kafka.bootstrap-servers}" )/* Endereço do broker Kafka */
    private String bootstrapServer;

    @Bean
    public ConsumerFactory<String, CarPostDTO> consumerFactory()
    {
        JsonDeserializer<CarPostDTO> deserializer = new JsonDeserializer<>( CarPostDTO.class );

        Map<String, Object> configProps = new HashMap<>();

        configProps.put( ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer );
        configProps.put( ConsumerConfig.GROUP_ID_CONFIG, "analytics-post-group" );
        configProps.put( ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class );
        configProps.put( ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer );

        return new DefaultKafkaConsumerFactory<>(
                configProps,
                new StringDeserializer(),
                new JsonDeserializer<>( CarPostDTO.class )
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CarPostDTO> kafkaListenerContainerFactory()
    {
        ConcurrentKafkaListenerContainerFactory<String, CarPostDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory( consumerFactory() );

        return factory;
    }
}
