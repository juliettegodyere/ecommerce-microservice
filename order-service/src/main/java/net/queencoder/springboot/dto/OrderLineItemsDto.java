package net.queencoder.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
private Long id;
	
	private String skiCode;
	private double price;
	private Integer quantity;
}
