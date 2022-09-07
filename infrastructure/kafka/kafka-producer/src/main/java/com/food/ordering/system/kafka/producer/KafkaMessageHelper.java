package com.food.ordering.system.kafka.producer;

import com.food.ordering.system.outbox.OutboxStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    public <T, U> ListenableFutureCallback<SendResult<String, T>>
    getKafkaCallback(String responseTopicName, T avroModel, U outboxMessage,
                     BiConsumer<U, OutboxStatus> outboxCallBack,
                     String orderId, String avroModelName) {

        return new ListenableFutureCallback<SendResult<String, T>>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Error while sending {} message {} and outbox type to topic {} ",
                        avroModelName, avroModel.toString(), outboxMessage.getClass().getName(),
                        responseTopicName, ex);
                outboxCallBack.accept(outboxMessage, OutboxStatus.FAILED);
            }

            @Override
            public void onSuccess(SendResult<String, T> result) {
                RecordMetadata metaData = result.getRecordMetadata();
                log.info("Received successful response from Kafka for order id: {}" +
                                " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                        orderId,
                        metaData.topic(),
                        metaData.partition(),
                        metaData.offset(),
                        metaData.timestamp());
                outboxCallBack.accept(outboxMessage, OutboxStatus.COMPLETED);
            }
        };

    }
}
