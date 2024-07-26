package com.progaming.techie.order_service_new.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.progaming.techie.order_service_new.dto.InventoryResponse;
import com.progaming.techie.order_service_new.dto.OrderLineItemsDto;
import com.progaming.techie.order_service_new.dto.OrderRequest;
import com.progaming.techie.order_service_new.entity.Order;
import com.progaming.techie.order_service_new.entity.OrderLineItems;
import com.progaming.techie.order_service_new.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;

	public void placeOrder(OrderRequest orderRequest) throws IllegalAccessException {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		List<OrderLineItems> orderLineItems = orderRequest.getOrderlineItemsDtoList().stream().map(this::mapToDto)
				.toList();
		order.setOrderlineIstemList(orderLineItems);

		List<String> skuCodes = order.getOrderlineIstemList().stream().map(OrderLineItems::getSkuCode).toList();
		// call api inventory xem còn hàng trong kho không
		// sử dụng pt của WebClient gọi đến api kiểm tra, sử dụng uriBuilder để truyền
		// giá trị
		InventoryResponse[] inventoryResponsArray = webClientBuilder.build().get()
				.uri("http://inventory-service-new/api/inventory",
						uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
				.retrieve().bodyToMono(InventoryResponse[].class).block();

		boolean allProductsInStock = Arrays.stream(inventoryResponsArray).allMatch(InventoryResponse::isInStock);

		if (allProductsInStock) {
			orderRepository.save(order);
		} else {
			throw new IllegalAccessException("Product is not in stock, please try again later!");
		}
	}

	private OrderLineItems mapToDto(OrderLineItemsDto dto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(dto.getPrice());
		orderLineItems.setQuantity(dto.getQuantity());
		orderLineItems.setSkuCode(dto.getSkuCode());
		return orderLineItems;
	}
}
