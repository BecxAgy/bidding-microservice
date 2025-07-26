package com.becxagy.book.api.infra.out.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.becxagy.book.api.adapters.out.queue.QueuePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class AmazonSQSQueue implements QueuePort {

    private final AmazonSQSAsync amazonSQSAsync;
    private final String queueUrl;

    public AmazonSQSQueue(AmazonSQSAsync amazonSQSAsync,
                          @Value("${aws.sqs.queue-url}") String queueUrl) {
        this.amazonSQSAsync = amazonSQSAsync;
        this.queueUrl = queueUrl;
    }

    @Override
    @Async
    public void publish(Long biddingId, String fileUrl, String model) {
        String messageBody = String.format("""
            {
              "id": "%s",
              "filename": "%s",
              "model": "%s"
            }
            """, biddingId, fileUrl);

        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody);

        amazonSQSAsync.sendMessageAsync(request);
    }
}
