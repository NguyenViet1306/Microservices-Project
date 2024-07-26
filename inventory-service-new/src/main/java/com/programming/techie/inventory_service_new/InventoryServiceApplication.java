package com.programming.techie.inventory_service_new;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // thêm annotation này để kích hoạt project có thể được kết nối sử dụng với máy chủ eureka
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

//	@Bean
//	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
//		return args -> {
//			Inventory inv = new Inventory();
//			inv.setSkuCode("iphone_13");
//			inv.setQuantity(100);
//
//			Inventory inv_1 = new Inventory();
//			inv_1.setSkuCode("iphone_13_red");
//			inv_1.setQuantity(0);
//
//			inventoryRepository.save(inv);
//			inventoryRepository.save(inv_1);
//		};
//	}
}
