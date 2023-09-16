package com.br.testscontainers.forengineers.service;

public interface PublishMessageService {

    void execute(final String queueName, Object message);
}
