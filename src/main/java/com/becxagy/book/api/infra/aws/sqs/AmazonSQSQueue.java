package com.becxagy.book.api.infra.aws.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.becxagy.book.api.adapters.out.queue.QueuePort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

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
    public void publish(MultipartFile file) {
        String messageBody = String.format("""
            {
              "id": "%s",
              "filename": "%s"
            }
            """, UUID.randomUUID(), file.getOriginalFilename());

        SendMessageRequest request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(messageBody);

        amazonSQSAsync.sendMessageAsync(request);
    }
}
