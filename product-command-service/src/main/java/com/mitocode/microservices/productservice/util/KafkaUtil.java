package com.mitocode.microservices.productservice.util;

import com.mitocode.microservices.commonmodels.model.entity.GenericEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {
    private final KafkaTemplate<String, GenericEntity> kafkaTemplate;
    @Value("${kafka.mitocode.topic:mitocode}")
    private String topicName;

    public void sendMessage(GenericEntity obj) {
        kafkaTemplate.send(topicName, obj);
    }
}
