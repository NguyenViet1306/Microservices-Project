package com.programming.techie.inventory_service_new.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.programming.techie.inventory_service_new.dto.InventoryResponse;
import com.programming.techie.inventory_service_new.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;

	// sử dụng annotation '@Transactional(readOnly = true)' của springframework để
	// chỉ đọc khi kết quả là đúng
	@Transactional(readOnly = true)
	public List<InventoryResponse> isInStock(List<String> skuCode) {
		return inventoryRepository.findBySkuCodeIn(skuCode).stream()
				.map(i -> 
						InventoryResponse.builder()
						.skuCode(i.getSkuCode())
						.isInStock(i.getQuantity() > 0)
						.build())
						.toList();
	}
}
