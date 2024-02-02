package net.queencoder.springboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.queencoder.springboot.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long>{
	Optional<Inventory> findBySkuCode(String skuCode);
	
	List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
