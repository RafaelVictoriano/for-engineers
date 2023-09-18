package com.br.testscontainers.forengineers.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@DynamoDBTable(tableName = "propostas-v2")
public class Proposta {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private Double valor;

    @DynamoDBAttribute
    private String data;
}
