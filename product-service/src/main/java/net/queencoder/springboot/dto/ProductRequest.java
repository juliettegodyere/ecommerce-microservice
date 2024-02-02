package net.queencoder.springboot.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	private String name;
	private String description;
	private double price;
}
