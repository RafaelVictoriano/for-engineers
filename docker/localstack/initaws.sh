#!/bin/bash

# -- > Create DynamoDb Table
echo Creating DynamoDb \'propostas\' table ...
aws --endpoint=http://localhost:4566 dynamodb create-table \
  --table-name propostas-v2 \
  --attribute-definitions AttributeName=id,AttributeType=S \
  --key-schema AttributeName=id,KeyType=HASH \
  --provisioned-throughput ReadCapacityUnits=10,WriteCapacityUnits=5

# --> List DynamoDb Tables
echo Listing tables ...
aws --endpoint=http://localhost:4566 dynamodb list-tables


echo Creating queue
aws --endpoint=http://localhost:4566 sqs create-queue --queue-name proposta-queue
