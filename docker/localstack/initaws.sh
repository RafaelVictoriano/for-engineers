#!/bin/bash

# -- > Create DynamoDb Table
echo Creating  DynamoDb \'ItemInfo\' table ...
aws --endpoint=http://localhost:4566 create-table --cli-input-json '{"TableName":"ItemInfo", "KeySchema":[{"AttributeName":"id","KeyType":"HASH"}], "AttributeDefinitions":[{"AttributeName":"id","AttributeType":"S"}],"BillingMode":"PAY_PER_REQUEST"}'

# --> List DynamoDb Tables
echo Listing tables ...
aws --endpoint=http://localhost:4566 dynamodb list-tables
