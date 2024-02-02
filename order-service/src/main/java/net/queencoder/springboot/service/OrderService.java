package net.queencoder.springboot.service;

import java.util.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import net.queencoder.springboot.dto.InventoryResponse;
import net.queencoder.springboot.dto.OrderLineItemsDto;
import net.queencoder.springboot.dto.OrderRequest;
import net.queencoder.springboot.model.Order;
import net.queencoder.springboot.model.OrderLineItems;
import net.queencoder.springboot.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	private final OrderRepository orderRepository;
	
	private final WebClient.Builder webClientBuilder;
	
	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes = order.getOrderLineItemsList().stream()
		.map(OrderLineItems::getSkiCode)
		.toList();
		
		//Call Inventory service , and place an order if product exist in stock using Webclient
		//Info https://www.techgeeknext.com/spring-boot/webflux/spring-boot-webclient-example
		InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
		    .uri("http://inventory-service/api/inventory", 
		    		uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
			.retrieve()
			.bodyToMono(InventoryResponse[].class)
			.block();
		
		boolean allProductsInStock = Arrays.stream(inventoryResponseArray)
		.allMatch(InventoryResponse::isInStock);
		
		if(allProductsInStock) {
			orderRepository.save(order);

			return "Order placed successfully";
		}else {
			throw new IllegalArgumentException("Product is not in stock. Please try again later.");
		}
		
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkiCode(orderLineItemsDto.getSkiCode());
		
		return orderLineItems;
	}
}
