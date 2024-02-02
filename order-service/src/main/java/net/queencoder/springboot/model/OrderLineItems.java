package net.queencoder.springboot.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_order_line_items")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String skiCode;
	private double price;
	private Integer quantity;
}
