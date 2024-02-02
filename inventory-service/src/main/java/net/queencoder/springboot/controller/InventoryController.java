package net.queencoder.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import net.queencoder.springboot.service.InventoryService;
import net.queencoder.springboot.dto.InventoryResponse;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
	
	private final InventoryService inventoryService;
	
	//http//localhost:8082/api/inventory?sku-code=iphone-13&sku-code=iphne13-red instead of http//localhost:8082/api/inevtory/iphone-13 
//	@GetMapping("/{sku-code}")
//	@ResponseStatus(HttpStatus.OK) 
//	public boolean isInStock(@PathVariable("sku-code") String skuCode) {
//		return inventoryService.isInStock(skuCode);
//	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK) 
	public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode) {
		return inventoryService.isInStock(skuCode);
	}
}
