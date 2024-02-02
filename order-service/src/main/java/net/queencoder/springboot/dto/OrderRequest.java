package net.queencoder.springboot.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
	private List<OrderLineItemsDto> orderLineItemsDtoList;
}
