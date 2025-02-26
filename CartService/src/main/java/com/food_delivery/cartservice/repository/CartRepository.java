package com.food_delivery.cartservice.repository;

import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import com.food_delivery.cartservice.entity.Cart;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class CartRepository {

    private final DynamoDbTable<Cart> cartTable;
    private final DynamoDbClient dynamoDbClient;

    public CartRepository(DynamoDbEnhancedClient enhancedClient, DynamoDbClient dynamoDbClient) {
        this.cartTable = enhancedClient.table("Cart", TableSchema.fromBean(Cart.class));
        this.dynamoDbClient = dynamoDbClient;
    }

    public void save(Cart item) {
        cartTable.putItem(item);
    }

    public Cart findById(String itemId) {
        return cartTable.getItem(r -> r.key(k -> k.partitionValue(itemId)));
    }

    public void deleteById(String itemId) {
        cartTable.deleteItem(r -> r.key(k -> k.partitionValue(itemId)));
    }

    // New method to find cart items by customerName using Scan
    public List<Cart> findByCustomerName(String customerName) {
        ScanRequest scanRequest = ScanRequest.builder()
                .tableName("Cart")
                .filterExpression("customerName = :customerName")
                .expressionAttributeValues(Map.of(":customerName", AttributeValue.builder().s(customerName).build()))
                .build();

        ScanResponse scanResponse = dynamoDbClient.scan(scanRequest);
       
                
        return scanResponse.items().stream()
                .map(item -> {
                    Cart cart = new Cart();
                    cart.setCartId(item.get("cartId").s());
                    cart.setCustomerName(item.get("customerName").s());
                    cart.setItemName(item.get("itemName").s());
                    return cart;
                })
                .collect(Collectors.toList());        
    }
}

