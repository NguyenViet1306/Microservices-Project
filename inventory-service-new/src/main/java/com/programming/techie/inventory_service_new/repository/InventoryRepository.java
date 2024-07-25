package com.programming.techie.inventory_service_new.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.programming.techie.inventory_service_new.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findBySkuCodeIn(List<String> skuCode);

}
