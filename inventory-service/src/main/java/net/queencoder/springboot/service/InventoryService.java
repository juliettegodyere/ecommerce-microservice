package net.queencoder.springboot.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.queencoder.springboot.dto.InventoryResponse;
import net.queencoder.springboot.repository.InventoryRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
	
	private final InventoryRepository inventoryRepository;
	
	@Transactional(readOnly = true)
	@SneakyThrows
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		//Testing Resiliense4J. Remove after testing
		log.info("Wait Started");
		Thread.sleep(10);
		log.info("Wait Ended");
		return inventoryRepository.findBySkuCodeIn(skuCode).stream()
				.map(inventory -> 
					InventoryResponse.builder()
					.skuCode(inventory.getSkuCode())
					.isInStock(inventory.getQuantity() > 0 )
					.build()
				).toList();
	}
}
