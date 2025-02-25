package com.food_delivery.cartservice.config;

import java.net.URI;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {
	
	private static final String ACCESS_KEY = "";
	
	private static final String SECRET_KEY = "";
	
	/*
    @Bean
    DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000")) // Use local DynamoDB
                .region(Region.US_EAST_1) // Dummy region, required
                .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create(
                        "fakeMyKeyId", "fakeSecretAccessKey"))) // Dummy credentials for local testing
                .overrideConfiguration(ClientOverrideConfiguration.builder().build())
                .build();
    }
    */
    
    @Bean
    DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.builder()
                .region(Region.AP_SOUTH_1) // Change to your actual AWS region
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(ACCESS_KEY, SECRET_KEY)
                ))
                .build();
    }
    
    @Bean
    DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient) //Use the configured DynamoDbClient
                .build();
    }
}

