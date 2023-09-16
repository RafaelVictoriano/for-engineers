package com.br.testscontainers.forengineers.service.impl;

import com.br.testscontainers.forengineers.service.PublishMessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PublishMessageSqsServiceImpl implements PublishMessageService {

    @Autowired
    private QueueMessagingTemplate messagingTemplate;

    @Override
    public void execute(final String queueName, final Object message) {
        log.info("Publishing message:{}", message);
        messagingTemplate.convertAndSend(message);
    }
}
