package net.queencoder.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.queencoder.springboot.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
}
