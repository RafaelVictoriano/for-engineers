package com.br.testscontainers.forengineers.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.br.testscontainers.forengineers.model.Proposta;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PropostaRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public void save(Proposta student) {
        dynamoDBMapper.save(student);
    }

    public Optional<Proposta> findById(String id) {
       return Optional.ofNullable(dynamoDBMapper.load(Proposta.class, id));
    }

    public List<Proposta> findAll() {
       return dynamoDBMapper.scan(Proposta.class, new DynamoDBScanExpression());
    }
}