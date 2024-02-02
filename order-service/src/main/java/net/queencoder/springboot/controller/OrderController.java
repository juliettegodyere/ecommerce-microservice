package net.queencoder.springboot.controller;

import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.*;
import net.queencoder.springboot.dto.OrderRequest;
import net.queencoder.springboot.service.OrderService;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	
	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@CircuitBreaker(name="inventory", fallbackMethod = "fallbackMethod")
	@TimeLimiter(name="inventory")
	@Retry(name="inventory")
	public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
		return CompletableFuture.supplyAsync(()-> orderService.placeOrder(orderRequest));
	}

	public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException){
		return CompletableFuture.supplyAsync(()-> "Oops! Something went wrong, please order after sometime");
	}
	
}
