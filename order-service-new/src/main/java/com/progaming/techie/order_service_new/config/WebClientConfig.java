package com.progaming.techie.order_service_new.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

//	// tạo Bean tại máy khách để kết nối đến 1 api ngoài prọject
//	@Bean
//	public WebClient webClient() {
//		return WebClient.builder().build();
//	}

	// tạo 1 ứng dụng thùng chứa webClient
	@Bean
	// thêm annotation cân bằng tải của springframework cloud. điều này làm nhiệm vụ
	// sẽ chỉ chọn các đường dẫn và lọc tuần tự các đường dẫn đấy để tìm đúng ra
	// đường dẫn
	// phù hợp giữa máy khách và hệ thống
	@LoadBalanced
	public WebClient.Builder webClient() {
		return WebClient.builder();
	}
}