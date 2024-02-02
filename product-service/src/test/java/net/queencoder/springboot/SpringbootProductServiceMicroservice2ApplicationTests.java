package net.queencoder.springboot;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.queencoder.springboot.dto.ProductRequest;
import net.queencoder.springboot.dto.ProductResponse;
import net.queencoder.springboot.repository.ProductRepository;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class SpringbootProductServiceApplicationTests extends AbsractContainerBaseTest{
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private ProductRepository productRepository;
    
	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productRequestString = objectMapper.writeValueAsString(productRequest);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, productRepository.findAll().size());
	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
		.name("iPhone 13")
		.description("Iphone 13")
		.price(1200)
		.build();
		
	}
	// Do the integration test for the GET API Endpoint
	// @Test
	// List<ProductResponse> shouldFetchAllProducts(){
	// 	mockMvc.perform(MockMvcRequestBuilders.get(null, null))
	// }

}
